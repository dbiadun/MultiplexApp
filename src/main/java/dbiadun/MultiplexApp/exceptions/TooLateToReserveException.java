package dbiadun.MultiplexApp.exceptions;

public class TooLateToReserveException extends Exception {
    public TooLateToReserveException() {
        super("Too late to reserve");
    }
}
