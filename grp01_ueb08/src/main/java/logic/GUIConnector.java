package logic;

import logic.PairsLogic.Symbol;
import logic.Position;

/**
 * Interface, welches die Logik von Memory nutzt, um der Oberfläche etwas mitzuteilen.
 *
 * @author #####, co
 */
public interface GUIConnector {

    /**
     * Zeigt ein Symbol an einer gegebenen Position im Feld.
     *
     * @param pos die Position im Feld, in der eine Karte aufgedeckt wird
     * @param symbol das Symbol, das dort angezeigt werden soll
     */
    void showCard(Position pos, Symbol symbol);

    /**
     * Versteckt das Symbol an der gegebenen Position im Feld.
     *
     * @param pos Position, an der das Symbol versteckt werden soll
     */
    void hideCard(Position pos);

    /**
     * Zeigt den Namen des aktuellen Spielers auf der Oberfläche an.
     *
     * @param name Name des aktuellen Spielers
     */
    void setCurrentPlayer(String name);

    /**
     * Wird aufgerufen, wenn ein Spieler gewonnen hat. Der Name des Siegers
     * muss dargestellt werden und es muss sichergestellt werden, dass der
     * Nutzer nicht weiterspielen kann (z.B. durch Deaktivieren der Komponenten).
     * Gibt es keinen Gewinnder, da beide Spieler die selbe Anzahl von Paaren
     * aufgedeckt haben, sollte der Gewinner als "no one" angezeigt werden.
     *
     * @param winnerName Name des Gewinners;
     *                   null, wenn keiner gewonnen hat, aber das Spiel zuende ist
     */
    void onGameEnd(String winnerName);

}
