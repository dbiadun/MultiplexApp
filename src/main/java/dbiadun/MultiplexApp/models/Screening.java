package dbiadun.MultiplexApp.models;

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

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "screening_id")
    private List<Ticket> reservedTickets = new ArrayList<>();

    @NotNull
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime time;

    public Screening() {
    }

    public Screening(ScreeningRoom screeningRoom, Movie movie, List<Ticket> reservedTickets, LocalDateTime time) {
        this.screeningRoom = screeningRoom;
        this.movie = movie;
        this.reservedTickets = reservedTickets;
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

    public List<Ticket> getReservedTickets() {
        return reservedTickets;
    }

    public void setReservedTickets(List<Ticket> reservedTickets) {
        this.reservedTickets = reservedTickets;
    }

    // Class used to give information about titles and screening times
    public static class ScreeningData {
        private int screeningId;

        private String title;

        private LocalDateTime time;

        public ScreeningData() {
        }

        public ScreeningData(int screeningId, String title, LocalDateTime time) {
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

    private ScreeningData getScreeningData() {
        return new ScreeningData(id, movie.getTitle(), time);
    }

    // Used to get titles and times of screenings from screenings list
    public static List<ScreeningData> getScreeningDataList(List<Screening> screenings) {
        List<ScreeningData> ret = new ArrayList<>();
        for (Screening s : screenings) {
            ret.add(s.getScreeningData());
        }
        return ret;
    }
}
