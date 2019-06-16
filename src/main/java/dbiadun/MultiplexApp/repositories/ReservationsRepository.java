package dbiadun.MultiplexApp.repositories;

import dbiadun.MultiplexApp.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservation, Integer> {
}
