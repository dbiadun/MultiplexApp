package dbiadun.MultiplexApp.helpers;

import java.time.LocalDateTime;

// Class used to give information about titles and screening times
public class ScreeningBasicData {
    private int screeningId;

    private String title;

    private LocalDateTime time;

    public ScreeningBasicData(int screeningId, String title, LocalDateTime time) {
        this.screeningId = screeningId;
        this.title = title;
        this.time = time;
    }

    public int getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(int screeningId) {
        this.screeningId = screeningId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
