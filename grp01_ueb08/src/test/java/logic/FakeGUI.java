package logic;

import logic.PairsLogic.Symbol;

/**
 * Fake GUI zum Testen der Logik des Spiels Memory. Alle Methoden tun nichts.
 * Um sicherzustellen, dass die Logik die korrekten GUI-Methoden aufruft, könnten
 * package private Attribute zugefügt und in den betreffenden Methoden gesetzt werden.
 * <p>
 * Sollte im Testordner untergebracht werden.
 *
 * @author #####
 */
public class FakeGUI implements GUIConnector {

    @Override
    public void showCard(Position pos, Symbol symbol) {
    }

    @Override
    public void hideCard(Position pos) {
    }

    @Override
    public void setCurrentPlayer(String name) {
    }

    @Override
    public void onGameEnd(String winnerName) {
    }


}