package logic.exceptions;

/**
 * Fehler der auftritt, wenn versucht wird ein spielstand zu speichern und ein fehler dabei aufgetreten ist
 * @author #####, Christoph
 */
public class HighScoresSpeichernException extends SpielStandException{
    private static final String message = "Highscores couldn't be saved: ";

    public HighScoresSpeichernException(String message, Throwable cause) {
        super(message, cause);
    }

    public HighScoresSpeichernException(Throwable cause) {
        super(message, cause);
    }
}
