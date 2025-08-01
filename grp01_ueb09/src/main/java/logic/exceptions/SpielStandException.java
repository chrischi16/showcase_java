package logic.exceptions;

/**
 * Fehler der auftritt, wenn mit einem Spielstand etwas nicht stimmt
 * @author #####, Christoph
 */
public class SpielStandException extends Exception {
    public SpielStandException(String message, Throwable cause) {
        super(message, cause);
    }
}
