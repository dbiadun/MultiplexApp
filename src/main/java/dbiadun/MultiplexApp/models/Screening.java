package dbiadun.MultiplexApp.models;

import dbiadun.MultiplexApp.helpers.ScreeningBasicData;
import dbiadun.MultiplexApp.helpers.ScreeningReservationData;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Screenings")
public class Screening {
    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @ManyToOne
    private ScreeningRoom screeningRoom;

    @NotNull
    @ManyToOne
    private Movie movie;

    @OneToMany(mappedBy = "screening", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("seatRow, seatColumn")
    private List<Ticket> tickets = new ArrayList<>();

    @NotNull
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime time;

    public Screening() {
    }

    public Screening(ScreeningRoom screeningRoom, Movie movie, List<Ticket> tickets, LocalDateTime time) {
        this.screeningRoom = screeningRoom;
        this.movie = movie;
        this.tickets = tickets;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ScreeningRoom getScreeningRoom() {
        return screeningRoom;
    }

    public void setScreeningRoom(ScreeningRoom screeningRoom) {
        this.screeningRoom = screeningRoom;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket) {
        getTickets().add(ticket);
        ticket.setScreening(this);
    }

    public void removeTicket(Ticket ticket) {
        getTickets().remove(ticket);
        ticket.setScreening(null);
    }

    private ScreeningBasicData getScreeningData() {
        return new ScreeningBasicData(id, movie.getTitle(), time);
    }

    // Used to get titles and times of screenings from screenings list
    public static List<ScreeningBasicData> getScreeningDataList(List<Screening> screenings) {
        List<ScreeningBasicData> ret = new ArrayList<>();
        for (Screening s : screenings) {
            ret.add(s.getScreeningData());
        }
        return ret;
    }

    public ScreeningReservationData getScreeningReservationData() {
        ScreeningReservationData data = new ScreeningReservationData(id, screeningRoom.getNumber());
        ArrayList<Integer> rowSizes = screeningRoom.getSeats();
        int currentTicket = 0;
        for (int row = 0; row < rowSizes.size(); row++) {
            for (int column = 0; column < rowSizes.get(row); column++) {
                if (currentTicket >= tickets.size() || tickets.get(currentTicket).getSeatRow() != row ||
                tickets.get(currentTicket).getSeatColumn() != column) {
                    data.addSeat(row, column);
                } else {
                    currentTicket++;
                }
            }
        }

        return data;
    }
}
