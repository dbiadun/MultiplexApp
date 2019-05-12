package dbiadun.MultiplexApp.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "screening")
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

    // Class used to give information about titles and screening times
    public static class ScreeningBasicData {
        private int screeningId;

        private String title;

        private LocalDateTime time;

        public ScreeningBasicData(int screeningId, String title, LocalDateTime time) {
            this.screeningId = screeningId;
            this.title = title;
            this.time = time;
        }

        public int getScreeningId() {
            return screeningId;
        }

        public void setScreeningId(int screeningId) {
            this.screeningId = screeningId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public LocalDateTime getTime() {
            return time;
        }

        public void setTime(LocalDateTime time) {
            this.time = time;
        }
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

    public static class ScreeningReservationData {
        private int screeningId;

        private int screeningRoomNumber;

        List<List<Integer>> availableSeats = new ArrayList<>();

        public ScreeningReservationData(int screeningId, int screeningRoomNumber) {
            this.screeningId = screeningId;
            this.screeningRoomNumber = screeningRoomNumber;
        }

        public int getScreeningId() {
            return screeningId;
        }

        public void setScreeningId(int screeningId) {
            this.screeningId = screeningId;
        }

        public int getScreeningRoomNumber() {
            return screeningRoomNumber;
        }

        public void setScreeningRoomNumber(int screeningRoomNumber) {
            this.screeningRoomNumber = screeningRoomNumber;
        }

        public List<List<Integer>> getAvailableSeats() {
            return availableSeats;
        }

        public void setAvailableSeats(List<List<Integer>> availableSeats) {
            this.availableSeats = availableSeats;
        }

        public void addSeat(int row, int column) {
            while (availableSeats.size() <= row) {
                availableSeats.add(new ArrayList<>());
            }
            availableSeats.get(row).add(column);
        }
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
