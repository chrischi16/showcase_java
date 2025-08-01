package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import logic.PairsLogic;
import logic.Position;

/**
 * Main class for the user interface.
 *
 * @author #####, co
 */
public class UserInterfaceController {

    /**
     * Label that displays the welcome text. Should be deleted when creating an
     * actual project.
     */
    @FXML
    private Button btn00;
    @FXML
    private Button btn01;
    @FXML
    private Button btn10;
    @FXML
    private Button btn11;
    @FXML
    private Button btn20;
    @FXML
    private Button btn21;
    @FXML
    private Button btn30;
    @FXML
    private Button btn31;
    @FXML
    private Button btn02;
    @FXML
    private Button btn12;
    @FXML
    private Button btn22;
    @FXML
    private Button btn32;
    @FXML
    private Label lblCurrPlayer;
    @FXML
    private Label lblWinner;
    @FXML
    private GridPane grdPnField;
    @FXML
    private Button btnNewGame;
    @FXML
    private Button btnStartGame;
    @FXML
    private Label lblPlyrNm1;
    @FXML
    private TextField txtFdPlrNm1;
    @FXML
    private Label lblPlyrNm2;
    @FXML
    private TextField txtFdPlrNm2;
    @FXML
    private VBox vboxRightPane;

    private PairsLogic game;
    private JavaFXGUI gui;

    // TODO: - DONE Wenn die letzte Karte aufgedeckt wird muss direkt der Gewinner angezeigt werden, ohne das nochmals
    //       geklickt werden muss.
    // TODO: - DONE Es sollen immer beide Karten aufgedeckt werden. Wurde kein Paar gefunden werden die beiden Karten erst
    //       mit dem Aufdecken der ersten Karte im nächsten Zug wieder verdeckt.

    /**
     * This is where you need to add code that should happen during
     * initialization and then change the java doc comment.
     */
    @FXML
    public void initialize() {
        btnNewGame.setDisable(false);
        btnStartGame.setVisible(false);
        lblWinner.setVisible(false);
        toggleVisability(false);
        Button[][] btnsGamePlay = new Button[][]{
                {btn00, btn01, btn02},
                {btn10, btn11, btn12},
                {btn20, btn21, btn22},
                {btn30, btn31, btn32}
        };
        this.gui = new JavaFXGUI(btnsGamePlay, lblCurrPlayer, lblWinner, vboxRightPane, grdPnField);
        this.game = new PairsLogic(txtFdPlrNm1.getText(), txtFdPlrNm2.getText(), grdPnField.getColumnCount(), grdPnField.getRowCount(), gui);
    }

    /**
     * EventHandler für die Karten-Buttons
     *
     * @param actionEvent
     */
    @FXML
    private void handleBtnCardsOnClick(ActionEvent actionEvent) {
        Button button = ((Button) actionEvent.getSource());

        String posString = button.getId().replaceFirst("btn","");
        int posI = Integer.parseInt(posString);

        Position pos = new Position(posI/10, posI%10);
        game.playerTurn(pos);
    }

    /**
     * EventHandler für den Button "New Game" | Startet ein neues Spiel
     *
     * @param actionEvent
     */
    @FXML
    private void handleBtnNewGameOnClick(ActionEvent actionEvent) {
        btnStartGame.setVisible(true);
        lblWinner.setVisible(false);
        grdPnField.setDisable(true);
        vboxRightPane.setDisable(true);
        toggleVisability(true);
        gui.setCurrentPlayer("");
    }

    /**
     * EventHandler für den Button "StartGame" | Startet das Spiel
     *
     * @param actionEvent
     */
    @FXML
    private void handleBtnStartGameOnClick(ActionEvent actionEvent) {

        if (!(txtFdPlrNm1.getText().isBlank() || txtFdPlrNm2.getText().isBlank())) {
            btnNewGame.setDisable(false);
            btnStartGame.setVisible(false);
            vboxRightPane.setDisable(false);
            grdPnField.setDisable(false);
            lblWinner.setVisible(false);
            toggleVisability(false);
            initialize();
        }
    }

    /**
     * Hilfsmethode für die Sichtbarkeitseinstellungen von gemeinsamen Elementen
     * @param flag der Zustand der für das jeweilige Element dargestellt werden soll
     */
    private void toggleVisability(boolean flag) {
        lblPlyrNm1.setVisible(flag);
        lblPlyrNm2.setVisible(flag);
        txtFdPlrNm1.setVisible(flag);
        txtFdPlrNm2.setVisible(flag);
    }

}