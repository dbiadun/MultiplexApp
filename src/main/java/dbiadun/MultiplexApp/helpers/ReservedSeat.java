package dbiadun.MultiplexApp.helpers;

import dbiadun.MultiplexApp.TicketType;

import java.util.Comparator;

public class ReservedSeat {
    private int row;

    private int column;

    private TicketType ticketType;

    public ReservedSeat() {
    }

    public ReservedSeat(int row, int column, TicketType ticketType) {
        this.row = row;
        this.column = column;
        this.ticketType = ticketType;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ReservedSeat)) return false;
        ReservedSeat seat = (ReservedSeat) o;
        return seat.row == this.row && seat.column == this.column && seat.ticketType == this.ticketType;
    }

    public static class SeatsComparator implements Comparator<ReservedSeat> {
        @Override
        public int compare(ReservedSeat s1, ReservedSeat s2) {
            if (s1.row != s2.row) return s1.row - s2.row;
            return s1.column - s2.column;
        }
    }
}
