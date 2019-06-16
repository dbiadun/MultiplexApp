package dbiadun.MultiplexApp.controllers;

import dbiadun.MultiplexApp.exceptions.ScreeningNotFoundException;
import dbiadun.MultiplexApp.helpers.ScreeningBasicData;
import dbiadun.MultiplexApp.helpers.ScreeningReservationData;
import dbiadun.MultiplexApp.models.Screening;
import dbiadun.MultiplexApp.repositories.ScreeningsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ScreeningsController {
    @Autowired
    private ScreeningsRepository screeningsRepository;

    // Get titles and screening times of movies available in chosen time interval
    @GetMapping("/screenings")
    public List<ScreeningBasicData> getAllScreenings(@RequestParam String startTime, @RequestParam String endTime) {
        List<Screening> screenings = screeningsRepository.
                findByTimeBetweenOrderByMovieTitleAscTimeAsc(LocalDateTime.parse(startTime), LocalDateTime.parse(endTime));
        return Screening.getScreeningDataList(screenings);
    }

    // Get information about a particular screening (screening room and available seats)
    @GetMapping("/screenings/{id}")
    public ScreeningReservationData getScreeningReservationData(@PathVariable int id)
            throws ScreeningNotFoundException {
        Screening screening = screeningsRepository.findById(id).orElseThrow(() -> new ScreeningNotFoundException(id));
        return screening.getScreeningReservationData();
    }
}
