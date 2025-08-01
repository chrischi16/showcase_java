package ueb.rooms;

/**
 * Spezieller Raum, der das Ende des Spiels markiert.
 *
 * Dies ist ein Meta-Raum, der nicht wirklich Teil des Spiels ist.
 * Er existiert lediglich, weil der Autor keine Rückgabe von `null`-Werten mag.
 *
 * Diese Datei darf nicht verändert werden.
 *
 * @author ##### & Christoph
 */
public class QuitGame extends Room {
    public QuitGame() {
        super("Spiel Beenden", "Das Spiel ist vorüber.\n");
    }

    @Override
    public boolean isFinal() {
        return true;
    }
}
