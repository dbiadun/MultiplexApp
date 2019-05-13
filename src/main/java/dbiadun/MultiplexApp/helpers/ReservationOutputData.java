package dbiadun.MultiplexApp.helpers;

import java.time.LocalDateTime;

public class ReservationOutputData {
    private int reservationId;

    private double price;

    private LocalDateTime expirationTime;

    public ReservationOutputData() {
    }

    public ReservationOutputData(int reservationId, double price, LocalDateTime expirationTime) {
        this.reservationId = reservationId;
        this.price = price;
        this.expirationTime = expirationTime;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }
}
