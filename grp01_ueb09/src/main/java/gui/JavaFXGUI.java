package gui;

import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import logic.GUIConnector;
import logic.HighScore;
import logic.exceptions.*;

import java.util.List;

/**
 * Diese Klasse nutzt JavaFX um das Interface GUIConnector zu implementieren. Sie nutzt rein logische Informationen um
 * das Spiel zu verändern und dem Nutzer darzustellen. Dabei werden abgesehen von den Namen in der Highscoreliste
 * niemals Texte aus der Logik übernommen.
 *
 * @author nvk, jeremi, christoph
 */
public class JavaFXGUI implements GUIConnector {

    /**
     * Interaktive GUI-Elemente:
     */
    private final Label lbl_score;
    private final Label lbl_currentNumber;
    private final Label lbl_range;
    private final Label lbl_currentScoreText;
    private final Label lbl_currentScoreNumber;
    private final Label lbl_notification;
    private final Button btn_gameElementGuessHigh;
    private final Button btn_gameElementGuessLow;
    private final Button btn_gameElementEndGame;
    private final Button btn_startNewGame;
    private final MenuItem menItem_SaveGame;
    private final MenuItem menItem_LoadHighScore;
    private final MenuItem menItem_StartNewGame;
    private final TextField txtFd_enterName;
    private final TableView<HighScore> table_HighScore;
    private final TableColumn<HighScore, String> tblCol_Name;
    private final TableColumn<HighScore, Integer> tblCol_Score;

    /**
     * Der Konstruktor erhält alle Komponenten die von der JavaFXGUI verändert werden sollen als Parameter und legt sie auf Klassenvariabeln ab.
     */
    public JavaFXGUI(Label lbl_currentScore, Label lbl_currentNumber, Label lbl_range, Button btn_gameElementGuessHigh,
                     Button btn_gameElementGuessLow, Button btn_gameElementEndGame, TextField txtFd_enterName,
                     Button btn_startNewGame, Label lbl_currentScoreText, Label lbl_currentScoreNumber,
                     Label lbl_playerName, Label lbl_notification, MenuItem menItem_SaveGame,
                     MenuItem menItem_LoadHighScore, MenuItem menItem_StartNewGame, TableView<HighScore> tableHighScore,
                     TableColumn<HighScore, String> tblColName, TableColumn<HighScore, Integer> tblColScore) {

        this.lbl_currentNumber = lbl_currentNumber;
        this.lbl_score = lbl_currentScore;
        this.lbl_range = lbl_range;
        this.lbl_currentScoreText = lbl_currentScoreText;
        this.lbl_currentScoreNumber = lbl_currentScoreNumber;
        this.lbl_notification = lbl_notification;
        this.btn_gameElementGuessHigh = btn_gameElementGuessHigh;
        this.btn_gameElementGuessLow = btn_gameElementGuessLow;
        this.btn_gameElementEndGame = btn_gameElementEndGame;
        this.btn_startNewGame = btn_startNewGame;
        this.menItem_SaveGame = menItem_SaveGame;
        this.menItem_LoadHighScore = menItem_LoadHighScore;
        this.menItem_StartNewGame = menItem_StartNewGame;
        this.txtFd_enterName = txtFd_enterName;
        this.table_HighScore = tableHighScore;
        this.tblCol_Name = tblColName;
        this.tblCol_Score = tblColScore;
    }

    @Override
    public void showNumber(int number) {
        this.lbl_currentNumber.setText(String.valueOf(number));
    }

    @Override
    public void updateHighScores(List<HighScore> highScores) {

        tblCol_Name.setCellValueFactory(highScore -> new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return highScore.getValue().name();
            }
        });
        tblCol_Score.setCellValueFactory(highScore -> new ObservableValueBase<Integer>() {
            @Override
            public Integer getValue() {
                return highScore.getValue().score();
            }
        });
        ObservableList<HighScore> hs = FXCollections.observableArrayList(highScores);
        table_HighScore.setItems(hs);

    }

    @Override
    public void updateCurrentScore(int score) {
        this.lbl_score.setText(String.valueOf(score));
    }

    @Override
    public void updateRange(int lowerLimit, int upperLimit) {
        lbl_range.setText("Im Zahlenbereich von " + lowerLimit + " bis " + upperLimit);
    }

    @Override
    public void handleEndOfGame(int score, boolean wrongGuess, int number) {
        if (wrongGuess) {
            showEndgameInformation(score, number, "Leider wurde falsch geraten ...");
            enableGameElements(false, true);
        } else if (!wrongGuess) {
            showEndgameInformation(score, number, "Spiel wurde beendet ...");
        }
    }

    @Override
    public void handleSameNumber(int number) {
        lbl_notification.setText("Die " + number + " wurde erneut gewürfelt. Du erhältst keine Punkte, " +
                "darfst aber nochmal raten");
    }

    @Override
    public void showException(Exception e1) {
        Alert info = new Alert(Alert.AlertType.ERROR);

        String title = "Fehler", header = e1.getMessage(), content = String.valueOf(e1.getCause());

        if (e1 instanceof EmptyNameException e2) {
            title = "Fehler mit einem Spieler Namen!";
            content = e2 instanceof PositionException e3
                    ? "Spieler Eintrag aus folger mit index " + e3.getPosition() + " ist nicht vorhanden und ungültig!"
                    : "Kein Name Gefunden zum Verarbeiten der Highscores";

        } else if (e1 instanceof NegativScoreException e2) {
            //TODO DONE inhalt
            title = "Fehler mit einem Punktestand";
            content = e2 instanceof PositionException e3
                    ? "Spielerpunktezahl " + e2.getNegativScore() + " mit index " + e2.getPosition() +
                    " ist im negativen Bereich und ungültig!"
                    : "";

        } else if (e1 instanceof PositionException e2) {
            title = "Fehler auf einer Position";
            content = "Positionsfehler auf Position " + e2.getPosition() + " gefunden.";

        } else if (e1 instanceof FilePathException e2) {
            title = "Fehler bei der Dateipfad wahl!";
            header = "Es ist ein fehler bei der wahl des Dateipfades aufgetreten";

        } else if (e1 instanceof HighScoresLadeException e2) {
            title = "Fehler beim Laden der Highscores!";
            header = "Ein Fehler ist beim Laden der Highscores aufgetreten.";
            content = "Bitte prüfe ob die angegebene Datei wirklich Highscores beinhaltet und " +
                    "auch richtig formatiert ist! Fehlerhaftes speichern kann dazu führen das es nicht " +
                    "als Highscore Liste erkannt wird";
        } else if (e1 instanceof HighScoresSpeichernException e2) {
            title = "Fehler beim Speichern der Highscores!";
            header = "Ein Fehler ist beim Speichern der Highscores aufgetreten.";
            content = "Bitte prüfe ob du dich im richtigen verzeichnis befindest und diese Anwendung " +
                    "Schreibberechtigungen in diesem Verzeichnis besitzt. Ohne die kann die Highscore Liste nicht " +
                    "gespeichert werden";
        } else if (e1 instanceof InvalidRangeException e2) {
            title = "Fehler beim laden des Zahlenbereichs";
            header = "Ein fehler ist beim laden der des Zahlenbereichs aufgetreten. ";
            content = e2.getMessage() + " Gegebene Minimum und Maximum sind: [lowerLimit = " + e2.getLowerLimit() +
                    ", upperLimit = " + e2.getUpperLimit() + "]";
        } else if (e1 instanceof SpielStandException e2) {
            title = "Fehler mit dem Spielstand";
            header = "Ein Lade oder Speicher Fehler ist bei dem gewählten Speicherstand aufgetreten";
            content = e2.getMessage() + "\n\n" + e2.getCause();
        }


        info.setTitle(title);
        info.setHeaderText(header);
        info.setContentText(content);
        info.showAndWait();
    }

    /**
     * Zeigt die Endgame Information an
     *
     * @param score            - Punktzahl
     * @param number           - Nächste Nummer
     * @param uniqueHeaderText - Benutzerdefinierter Header
     */
    private void showEndgameInformation(int score, int number, String uniqueHeaderText) {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Spiel beendet");
        info.setHeaderText(uniqueHeaderText);
        info.setContentText("Du hast " + score + " mal richtig geraten! Die letzte Zahl war " + number + ".");
        info.showAndWait();
    }

    /**
     * Diese Methode regelt die Sichtbarkeit und Verfügbarkeit der Spielelemente für innerhalb des Spieles und außerhalb
     * des Spieles.
     *
     * @param newState - welchen Zustand die Spielelemente haben sollen (Sichtbar/Verwendbar oder nicht)
     */
    public void enableGameElements(boolean newState, boolean gameJustEnded) {
        // Menuelemente 1-3 deaktivieren/aktivieren
        menItem_SaveGame.setDisable(newState);
        menItem_LoadHighScore.setDisable(newState);
        menItem_StartNewGame.setDisable(newState);

        // Spielelemente deaktivieren/aktivieren
        btn_gameElementEndGame.setDisable(!newState);
        btn_gameElementGuessHigh.setDisable(!newState);
        btn_gameElementGuessLow.setDisable(!newState);

        txtFd_enterName.setVisible(!newState);
        btn_startNewGame.setVisible(!newState);
        if (gameJustEnded) {
            btn_startNewGame.setText("Erneut spielen");
        }
        lbl_currentScoreNumber.setVisible(newState);
        lbl_currentScoreText.setVisible(newState);
    }
}
