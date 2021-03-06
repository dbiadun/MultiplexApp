package dbiadun.MultiplexApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dbiadun.MultiplexApp.TicketType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Tickets")
public class Ticket {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JsonIgnore
    private Screening screening;

    @NotNull
    private int seatRow;

    @NotNull
    private int seatColumn;

    @ManyToOne
    @JsonIgnore
    private Reservation reservation;

    @NotNull
    private TicketType ticketType;

    public Ticket() {
    }

    public Ticket(Screening screening, int seatRow, int seatColumn, Reservation reservation, TicketType ticketType) {
        this.screening = screening;
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.reservation = reservation;
        this.ticketType = ticketType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatColumn() {
        return seatColumn;
    }

    public void setSeatColumn(int seatColumn) {
        this.seatColumn = seatColumn;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }
}
