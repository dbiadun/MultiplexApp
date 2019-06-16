package dbiadun.MultiplexApp.repositories;

import dbiadun.MultiplexApp.models.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScreeningsRepository extends JpaRepository<Screening, Integer> {
    public List<Screening> findByTimeBetweenOrderByMovieTitleAscTimeAsc(LocalDateTime start, LocalDateTime end);
}
