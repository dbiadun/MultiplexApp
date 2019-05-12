package dbiadun.MultiplexApp.exceptions;

public class ScreeningNotFoundException extends Exception {
    public ScreeningNotFoundException(int id) {
        super("Screening with id " + Integer.toString(id) + " not found.");
    }
}
