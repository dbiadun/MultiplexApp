package dbiadun.MultiplexApp.models;

import dbiadun.MultiplexApp.TicketType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Tickets")
public class Ticket {
    @Id
    @GeneratedValue
    private int Id;

    @NotNull
    private int seatRow;

    @NotNull
    private int seatColumn;

    @NotNull
    @ManyToOne
    private Reservation reservation;

    @NotNull
    private TicketType ticketType;

    public Ticket() {
    }

    public Ticket(int seatRow, int seatColumn, Reservation reservation, TicketType ticketType) {
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.reservation = reservation;
        this.ticketType = ticketType;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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
