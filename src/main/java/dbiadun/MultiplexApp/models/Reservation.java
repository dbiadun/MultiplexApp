package dbiadun.MultiplexApp.models;

import dbiadun.MultiplexApp.TicketType;
import dbiadun.MultiplexApp.exceptions.InvalidReservationDataException;
import dbiadun.MultiplexApp.exceptions.TooLateToReserveException;
import dbiadun.MultiplexApp.helpers.ReservedSeat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Pattern;

@Entity
@Table(name = "Reservations")
public class Reservation {
    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank
    @NotNull
    @Size(max = 64)
    private String firstName;

    @NotBlank
    @NotNull
    @Size(max = 64)
    private String lastName;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    public Reservation() {}

    public Reservation(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket) {
        getTickets().add(ticket);
        ticket.setReservation(this);
    }

    public void removeTicket(Ticket ticket) {
        getTickets().remove(ticket);
        ticket.setReservation(null);
    }


    private void validateNames() throws InvalidReservationDataException {
        String firstNamePattern = "((([A-Z])|Ą|Ć|Ę|Ł|Ń|Ó|Ś|Ź|Ż)(([a-z])|ą|ć|ę|ł|ń|ó|ś|ź|ż){2,})";
        String lastNamePattern = firstNamePattern + "(-" + firstNamePattern + ")?";
        if (!Pattern.matches(firstNamePattern, firstName))
            throw new InvalidReservationDataException("Invalid firstName");
        if (!Pattern.matches(lastNamePattern, lastName))
            throw new InvalidReservationDataException("Invalid lastName");
    }

    private void validateSeats() throws InvalidReservationDataException {
        if (tickets.size() < 1)
            throw new InvalidReservationDataException("Invalid tickets number");

        TreeSet<ReservedSeat> reservedSeats = new TreeSet<>(new ReservedSeat.SeatsComparator());

        Screening screening = tickets.get(0).getScreening();

        List<Integer> rowSizes = screening.getScreeningRoom().getSeats();

        List<Ticket> earlierTickets = screening.getTickets();

        for (int i = 0; i < rowSizes.size(); i++) {
            if (rowSizes.get(i) > 1) {
                ReservedSeat s1 = new ReservedSeat(i, -1, TicketType.ADULT);
                ReservedSeat s2 = new ReservedSeat(i, rowSizes.get(i), TicketType.ADULT);
                reservedSeats.add(s1);
                reservedSeats.add(s2);
            }
        }

        // Adds ends of rows to check distance from the edge (there cannot be a single place left over in a row)
        for (Ticket ticket : earlierTickets) {
            reservedSeats.add(new ReservedSeat(ticket.getSeatRow(), ticket.getSeatColumn(), TicketType.ADULT));
        }

        // Checks if places weren't reserved already
        for (Ticket ticket : tickets) {
            if (!reservedSeats.add(new ReservedSeat(ticket.getSeatRow(), ticket.getSeatColumn(), TicketType.ADULT)))
                throw new InvalidReservationDataException("Seats already reserved");
        }

        // Checks distances between reserved places
        Iterator<ReservedSeat> it = reservedSeats.iterator();
        ReservedSeat prev = it.next();
        while (it.hasNext()) {
            ReservedSeat cur = it.next();
            if (prev.getRow() == cur.getRow() && cur.getColumn() - prev.getColumn() == 2)
                throw new InvalidReservationDataException("There cannot be a single place left over in a row " +
                        "between two already reserved places");
            prev = cur;
        }
    }

    private void validateTime() throws TooLateToReserveException {
        if (LocalDateTime.now().plusMinutes(15).isAfter(tickets.get(0).getScreening().getTime()))
            throw new TooLateToReserveException();
    }

    public void validate() throws InvalidReservationDataException, TooLateToReserveException {
        validateNames();
        validateSeats();
        validateTime();
    }
}
