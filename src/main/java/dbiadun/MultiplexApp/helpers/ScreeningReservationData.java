package dbiadun.MultiplexApp.helpers;

import java.util.ArrayList;
import java.util.List;

public class ScreeningReservationData {
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
