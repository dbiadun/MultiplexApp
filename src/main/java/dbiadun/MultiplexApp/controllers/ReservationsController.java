package dbiadun.MultiplexApp.controllers;

import dbiadun.MultiplexApp.exceptions.InvalidReservationDataException;
import dbiadun.MultiplexApp.exceptions.ScreeningNotFoundException;
import dbiadun.MultiplexApp.exceptions.TooLateToReserveException;
import dbiadun.MultiplexApp.helpers.ReservationInputData;
import dbiadun.MultiplexApp.helpers.ReservationOutputData;
import dbiadun.MultiplexApp.helpers.ReservedSeat;
import dbiadun.MultiplexApp.models.Reservation;
import dbiadun.MultiplexApp.models.Screening;
import dbiadun.MultiplexApp.models.Ticket;
import dbiadun.MultiplexApp.repositories.ReservationsRepository;
import dbiadun.MultiplexApp.repositories.ScreeningsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Semaphore;

@RestController
public class ReservationsController {
    @Autowired
    private ScreeningsRepository screeningsRepository;

    @Autowired
    private ReservationsRepository reservationsRepository;

    private static Semaphore sem = new Semaphore(1);

    // Add reservation
    @PostMapping("/reservations")
    public ReservationOutputData addReservation(@RequestBody ReservationInputData input)
            throws ScreeningNotFoundException {
        ReservationOutputData output = new ReservationOutputData();

        try {
            sem.acquire();
            try {
                Screening screening = screeningsRepository.findById(input.getScreeningId()).
                        orElseThrow(() -> new ScreeningNotFoundException(input.getScreeningId()));
                Reservation reservation = new Reservation(input.getFirstName(), input.getLastName());
                for (ReservedSeat seat : input.getReservedSeats()) {
                    Ticket ticket = new Ticket(screening, seat.getRow(), seat.getColumn(), reservation, seat.getTicketType());
                    reservation.addTicket(ticket);
                }
                reservation.validate();

                reservationsRepository.save(reservation);

                output.setReservationId(reservation.getId());
                output.setPrice(input.getPrice());
                output.setExpirationTime(screening.getTime().minusMinutes(15));
            } finally {
                sem.release();
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
        } catch (InvalidReservationDataException | TooLateToReserveException e) {
            System.err.println(e.getMessage());
        }

        return output;
    }
}
