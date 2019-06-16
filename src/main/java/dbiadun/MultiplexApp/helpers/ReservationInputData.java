package dbiadun.MultiplexApp.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class ReservationInputData {
    private int screeningId;

    private String firstName;

    private String lastName;

    private List<ReservedSeat> reservedSeats;

    public ReservationInputData() {
    }

    public ReservationInputData(String firstName, String lastName, List<ReservedSeat> reservedSeats) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.reservedSeats = reservedSeats;
    }

    public int getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(int screeningId) {
        this.screeningId = screeningId;
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

    public double getPrice() {
        try (InputStream stream = new FileInputStream("src/main/java/dbiadun/MultiplexApp/config.properties")) {
            Properties p = new Properties();
            p.load(stream);

            double price = 0;

            for (ReservedSeat seat : reservedSeats) {
                switch (seat.getTicketType()) {
                    case ADULT:
                        price += new Double(p.getProperty("prices.adult"));
                        break;
                    case STUDENT:
                        price += new Double(p.getProperty("prices.student"));
                        break;
                    case CHILD:
                        price += new Double(p.getProperty("prices.child"));
                        break;
                }
            }

            return price;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<ReservedSeat> getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(List<ReservedSeat> reservedSeats) {
        this.reservedSeats = reservedSeats;
    }
}
