package dbiadun.MultiplexApp.repositories;

import dbiadun.MultiplexApp.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Integer> {}
