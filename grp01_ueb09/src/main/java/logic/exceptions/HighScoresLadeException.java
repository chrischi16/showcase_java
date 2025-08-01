package logic.exceptions;

/**
 * Fehler der auftritt, wenn versucht wird ein spielstand zu laden und ein fehler dabei aufgetreten ist
 * @author #####, Christoph
 */
public class HighScoresLadeException extends SpielStandException{
    private static final String message = "Highscores couldn't be loaded: ";

    public HighScoresLadeException(String message, Throwable cause) {
        super(message, cause);
    }

    public HighScoresLadeException(Throwable cause) {
        super(message, cause);
    }
}
