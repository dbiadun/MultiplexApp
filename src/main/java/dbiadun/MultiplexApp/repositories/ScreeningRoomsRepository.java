package dbiadun.MultiplexApp.repositories;

import dbiadun.MultiplexApp.models.ScreeningRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreeningRoomsRepository extends JpaRepository<ScreeningRoom, Integer> {
}
