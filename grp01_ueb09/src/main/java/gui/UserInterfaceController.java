package gui;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import logic.Game;
import logic.HighScore;
import logic.exceptions.FilePathException;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Der Controller der GUI. Er erstellt bei Programmstart das game und die JavaFXGUI. Im Spielverlauf verarbeitet er die
 * Eingaben des Nutzers und gibt diese an das game weiter.
 *
 * @author nvk
 */
public class UserInterfaceController {

    /**
     * Die Logik des Spiels.
     */
    private Game game;
    /**
     * Die Implementation des GUIConnectors, die die Logik nutzt um mit der GUI zu kommunizieren.
     */
    private JavaFXGUI gui;
    @FXML
    private AnchorPane anchrPnMain;
    @FXML
    private Button btn_guessLower;
    @FXML
    private Button btn_guessHigher;
    @FXML
    private Label lbl_currentNumber;
    @FXML
    private Button btn_exitGame;
    @FXML
    private Label lbl_currentScore;
    @FXML
    private TextField txtField_PlayerName;
    @FXML
    private Label lbl_Name;
    @FXML
    private Button btn_startGame;
    @FXML
    private Label lbl_currentScoreNumber;
    @FXML
    private Label lbl_zahlbereich;
    @FXML
    private MenuItem menItem_SaveGame;
    @FXML
    private MenuItem menItem_ExitGame;
    @FXML
    private MenuItem menItem_LoadHighScore;
    @FXML
    private TableView<HighScore> table_HighScore;
    @FXML
    private TableColumn<HighScore, String> tblCol_Name;
    @FXML
    private TableColumn<HighScore, Integer> tblCol_Score;

    @FXML
    private MenuItem menItem_StartNewGame;
    @FXML
    private Label lbl_Notification;


    @FXML
    public void initialize() {
        // Komponenten, die von der JavaFXGUI verändert werden sollen wie Textfelder für Ausgaben, dem Konstruktor als Parameter übergeben.
        gui = new JavaFXGUI(lbl_currentScoreNumber, lbl_currentNumber, lbl_zahlbereich, btn_guessHigher, btn_guessLower,
                btn_exitGame, txtField_PlayerName, btn_startGame, lbl_currentScore, lbl_currentScoreNumber, lbl_Name,
                lbl_Notification, menItem_SaveGame, menItem_LoadHighScore, menItem_StartNewGame, table_HighScore,
                tblCol_Name, tblCol_Score);
        game = new Game(gui);

        // Tabelle initialisieren:
        // Den Tabellenspalten sagen, welche Daten sie aufnehmen sollen
//        tblCol_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
//        tblCol_Score.setCellValueFactory(new PropertyValueFactory<>("score"));
//        ObservableList<HighScore> hs = FXCollections.observableArrayList(highScoreList.getHighScores());
//        table_HighScore.setItems(hs);
    }

    /**
     * Wetteingabe für den Nutzer (Lower = Niedriger)
     */
    @FXML
    private void onBtnGuessLowerClicked(ActionEvent actionEvent) {
        lbl_Notification.setText("");
        game.setGuess(0);
        game.runGame();
    }

    /**
     * Wetteingabe für den Nutzer (Lower = Niedriger)
     */
    @FXML
    private void onBtnGuessHigherClicked(ActionEvent actionEvent) {
        lbl_Notification.setText("");
        game.setGuess(1);
        game.runGame();
    }

    /**
     * Beendet das aktuelle Spiel (Die derzeitige Runde)
     */
    @FXML
    private void onBtnEndCurrentGame(ActionEvent actionEvent) {
        game.endGame();
        gui.enableGameElements(false, true);

    }

    /**
     * Verarbeitet den Spielstart
     */
    @FXML
    private void onBtnStartGameClicked(ActionEvent actionEvent) {
        if (onTxtEntered() != null) {
            game.startNewGame(onTxtEntered());
            lbl_Notification.setText("");
            gui.enableGameElements(true, false);
        }

    }

    /**
     * Verarbeitet die Namenseingabe
     */
    @FXML
    private String onTxtEntered() {
        String player = txtField_PlayerName.getText();
        if (!player.isEmpty() && !player.isBlank()) {
            return player;
        } else {
            lbl_Notification.setText("Namensfeld ist leer, bitte Name eingeben.");
            return null;
        }
    }

    /**
     * Verarbeitet die MenuItem Eingabe
     */
    @FXML
    private void onMenItemNewGameClicked(ActionEvent actionEvent) {
        gui.enableGameElements(false, false);
    }

    /**
     * Ermöglicht das Speichern einer Bestenliste
     */
    @FXML
    private void onMenItemSaveGameClicked(ActionEvent actionEvent) throws IOException {
        //TODO load und save zusammenfassen
        //Step 1: Figure out where we are currently are, so we can open the dialog in
        File currDir = null;
        try {
            currDir = new File(UserInterfaceController.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        } catch (URISyntaxException ex) {
            gui.showException(new FilePathException(ex));
            //oops... ¯\_(ツ)_/¯
            //guess we won't be opening the dialog in the right directory
        }
        //Step 2: Put it together
        FileChooser fileChooser = new FileChooser();
        if (currDir != null) {
            //ensure the dialog opens in the correct directory
            fileChooser.setInitialDirectory(currDir.getParentFile());
        }
        fileChooser.setTitle("Highscore speichern");
        //Step 3: Open the Dialog (set window owner, so nothing in the original window
        //can be changed)
        File selectedFile = fileChooser.showSaveDialog(anchrPnMain.getScene().getWindow());
        try {
            game.saveHighScore(selectedFile);
        } catch (Exception e){
            gui.showException(e);
        }
    }

    @FXML
    private void onMenItemCloseGameClicked(ActionEvent actionEvent) {
        Platform.exit();
    }

    /**
     * Ermöglicht das Laden einer Bestenliste
     */
    @FXML
    private void onMenItemLoadHighScoreClicked(ActionEvent actionEvent) throws IOException {
        //Step 1: Figure out where we are currently are, so we can open the dialog in
        File currDir = null;
        try {
            currDir = new File(UserInterfaceController.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        } catch (URISyntaxException ex) {
            gui.showException(new FilePathException(ex));
            //oops... ¯\_(ツ)_/¯
            //guess we won't be opening the dialog in the right directory
        }
        //Step 2: Put it together
        FileChooser fileChooser = new FileChooser();
        if (currDir != null) {
            //ensure the dialog opens in the correct directory
            fileChooser.setInitialDirectory(currDir.getParentFile());
        }
        fileChooser.setTitle("Highscore laden");
        //Step 3: Open the Dialog (set window owner, so nothing in the original window
        //can be changed)
        File selectedFile = fileChooser.showOpenDialog(anchrPnMain.getScene().getWindow());
        game.loadHighScore(selectedFile);
        try {
            game.loadHighScore(selectedFile);
        } catch (Exception e){
            gui.showException(e);
        }
    }
}
