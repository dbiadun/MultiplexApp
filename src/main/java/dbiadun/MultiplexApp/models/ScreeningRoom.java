package dbiadun.MultiplexApp.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ScreeningRooms")
public class ScreeningRoom {
    @Id
    private Integer number;

    @NotBlank
    @NotNull
    @Size(max = 128)
    private String seats;

    public ScreeningRoom() {
    }

    public ScreeningRoom(Integer number, List<Integer> seats) {
        this.number = number;
        this.seats = seats.toString();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    // I'm storing seats arrangement in string, because it's a constant property of screening room and
    // I don't need to access particular rows arrangement alone, so there was no point in creating another table
    public ArrayList<Integer> getSeats() {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        String[] seatsArray = seats.split("(, )|\\[|]");

        for (String seats : seatsArray) {
            if (!seats.contentEquals("")) {
                ret.add(new Integer(seats));
            }
        }
        return ret;
    }

    public void setSeats(ArrayList<Integer> seats) {
        this.seats = seats.toString();
    }
}
