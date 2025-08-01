package logic.exceptions;

/**
 * Fehler der auftritt, wenn versucht wird mit einem Dateipfad eine spielstand datei zu öffnen
 * @author #####, Christoph
 */
public class FilePathException extends SpielStandException{
    private static final String message = "File path was invalid and file couldn't be properly opened at" +
            "the path it was supposed to open";

    public FilePathException(String message, Throwable cause) {
        super(message, cause);
    }
    public FilePathException(Throwable cause) {
        super(message, cause);
    }
}
