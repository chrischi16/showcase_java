package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import logic.GUIConnector;
import logic.PairsLogic.Symbol;
import logic.Position;

/**
 * Mit Methoden dieser Klasse kann die Logik Veränderungen auf der Oberfläche erzeugen.
 * Die JavaFXGUI wird vom UserInterfaceController erzeugt und als Parameter an die Logik übergeben.
 * <p>
 * Weitere private oder protected Methoden dürfen hier zugefügt werden.
 *
 * @author #####, co
 */
public class JavaFXGUI implements GUIConnector {

    /**
     * Die Buttons auf dem Spielfeld untergebracht in einem Array.
     * Position im Array entspricht der Position auf der Oberfläche
     */
    private final Button[][] btnsField;

    @FXML
    private Label lblCurrPlayer;
    @FXML
    private Label lblWinner;
    @FXML
    private VBox vboxRightPane;
    @FXML
    private GridPane grdPnField;

    /**
     * Der Konstruktor. Erhält alle Komponenten der Oberfläche, die nach
     * Änderungen in der Logik aktualisiert werden müssen.
     *
     * @param btns die Buttons des Spielfelds (können in ihrem Text die Symbole der Karten anzeigen)
     *             <br>
     */
    public JavaFXGUI(Button[][] btns, Label lblCurrPlayer, Label lblWinner,
                     VBox vboxRightPane, GridPane grdPnField) {
        this.btnsField = btns;
        this.lblCurrPlayer = lblCurrPlayer;
        this.lblWinner = lblWinner;
        this.vboxRightPane = vboxRightPane;
        this.grdPnField = grdPnField;
    }

    /**
     * Zeigt ein Symbol an einer gegebenen Position im Feld.
     *
     * @param pos    die Position im Feld, in der eine Karte aufgedeckt wird
     * @param symbol das Symbol, das dort angezeigt werden soll
     */
    @Override
    public void showCard(Position pos, Symbol symbol) {
        // TODO - DONE: Die Darstellung der Symbole muss hier entschieden werden, nutzt einen switch oder ein anderes
        //       geeignetes Konstrukt.
        this.btnsField[pos.x()][pos.y()].setText(switch (symbol) {
            case BEE -> "\uD83D\uDC1D";
            case BAT -> "\uD83E\uDD87";
            case OWL -> "\uD83E\uDD89";
            case PIG -> "\uD83D\uDC16";
            case POO -> "\uD83D\uDCA9";
            case BUG -> "\uD83D\uDC1B";
            case CAKE -> "\uD83C\uDF82";
            case GHOST -> "\uD83D\uDC7B";
            case DOG -> "\uD83D\uDC15";
            case BEAR -> "\uD83D\uDC3B";
            case BOOK -> "\uD83D\uDCD6";
            case HONEY -> "\uD83C\uDF6F";
            default -> "";

        });
    }

    /**
     * Versteckt das Symbol an der gegebenen Position im Feld.
     *
     * @param pos Position, an der das Symbol versteckt werden soll
     */

    @Override
    public void hideCard(Position pos) {
        this.btnsField[pos.x()][pos.y()].setText("");
    }

    @Override
    public void setCurrentPlayer(String name) {
        this.lblCurrPlayer.setText(name);
    }

    @Override
    public void onGameEnd(String winnerName) {

        // Spiel deaktivieren;
        this.grdPnField.setDisable(true);

        // Gewinner anzeigen
        if (winnerName != null) {
            this.lblWinner.setText("Winner\nis\n" + winnerName);
        } else this.lblWinner.setText("Winner\nis\nno one");
        this.vboxRightPane.setDisable(true);
        this.lblWinner.setVisible(true);
    }

}
