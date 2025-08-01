package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Klasse die das Spiel startet und das Fenster vorbereitet.
 *
 * @author nvk, Jeremi, Christoph
 */
public class ApplicationMain extends Application {

    /**
     * Start Methode unserer Anwendung. Setzt das Anwendungsfenster auf und macht es sichtbar
     * @param stage die Stage die der Anwendung übergeben wird
     * @throws Exception wirft fehler falls diese auftreten und geworfen werden damit sie gedebuggt werden
     */
    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationMain.class.getResource("UserInterface.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 300);

        stage.setTitle("Neujahrs Spielshow");
        stage.setMinHeight(300);
        stage.setMinWidth(550);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Haupt Methode des Programms. Started das Programm und lässt die Anwendung mit übergebenen
     *      argumenten sich selbst aufbauen
     * @param args programm argumente womit das Programm gestartet wurde
     */
    public static void main(String[] args) {
        launch(args);
    }
}
