package ueb;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ueb.rooms.Room;

/**
 * Grundlegende Tests für ein Spiel.
 * <p>
 * Herausgegebene Tests müssen bei der Abnahme bestanden werden, um Chance auf eine Nachbesserung zu erhalten.
 *
 * Erstellen Sie gerne eigene Tests in einer Testdatei ohne das Präfix "Pub".
 * Hierin darf gerne `assertRoomStep()` importiert und aufgerufen werden.
 *
 * @author #####
 */
public class PubAdventureGameTest {

    /**
     * Sollten Sie Schwierigkeiten haben, einen Test zu debuggen, können Sie diese
     * Konstante auf `true` setzen, um die Ausgaben der im Test besuchten Räume
     * nachzuvollziehen.
     */
    private static final boolean DEBUG_OUTPUT = true;

    /**
     * Die gegebene `Main.main()` ruft `describe()` und `getOptions()` vor jeder
     * `processDecision()` auf. Diese Methoden könnten relevante Seiteneffekte
     * in spezialisierten Klassen haben (z.B. andere Zustände erzeugen).
     * Um das Verhalten der `main()` nachzubilden, sollte diese Hilfsmethode
     * in den Tests eingesetzt werden.
     *
     * @param currentRoom Der Raum, in dem der Spieler gerade steht.
     * @param decision Die Entscheidung, die der Spieler per Tastatur eingegeben hat.
     * @param expRoomName Der Raum, in dem der Spieler hinterher stehen sollte.
     * @param expIsFinal Ob der erreichte Raum final ist oder nicht.
     *
     * @return Der Raum, der mit der gegebenen Entscheidung erreicht wird.
     */
    public static Room assertRoomStep(Room currentRoom, String decision, String expRoomName, boolean expIsFinal) {
        StringBuilder output = new StringBuilder(1024); // Jeder Raum sollte in ein Kilobyte hineinpassen

        // Methodenaufrufe wie in der Main.main()
        output.append(currentRoom.describe());
        output.append(currentRoom.getOptions());

        if (DEBUG_OUTPUT) {
            System.out.println(output.toString());
            System.out.printf("Processing decision \"%s\"%n", decision);
        }

        // Aufrufe wie in `Main.main()`
        Room newRoom = currentRoom.processDecision(decision);

        // Prüfen, ob der Spieler im erwarteten Raum landet
        assertEquals(expRoomName, newRoom.getName(),
                     // Zur Erinnerung: Jeder JUnit-`assert*()`-Methode kann als letztes Argument
                     //                 ein String übergeben werden, der angezeigt wird, wenn
                     //                 die Prüfung fehlschlägt.
                     //
                     // Gibt eine präzise Beschreibung des vorigen Raums und der Entscheidung an,
                     // wenn der Name des neuen Raums nicht der Erwartung entspricht.
                     String.format(
                             "Going from \"%s\" with decision \"%s\" should end in \"%s\"",
                             currentRoom.getName(), decision, expRoomName
                     )
        );
        assertEquals(expIsFinal, newRoom.isFinal());

        return newRoom;
    }

    @Test
    public void startRoomIsOutside() {
        Room startRoom = Main.createPyramidWorld();
        assertEquals("Draußen", startRoom.getName());
    }

    @Test
    public void canQuitFromStartRoom() {
        Room currentRoom = Main.createPyramidWorld();

        currentRoom = assertRoomStep(currentRoom, "0", "Spiel Beenden", true);
    }

    @Test
    public void canEnterAndLeaveThePyramid() {
        Room currentRoom = Main.createPyramidWorld();

        currentRoom = assertRoomStep(currentRoom, "1", "Eingang", false);
        currentRoom = assertRoomStep(currentRoom, "0", "Draußen", false);
    }

}
