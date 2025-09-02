package chess.controller.errors;

public class StillCheckedException extends Exception {
    public StillCheckedException (String message) {
        super(message);
    }
}