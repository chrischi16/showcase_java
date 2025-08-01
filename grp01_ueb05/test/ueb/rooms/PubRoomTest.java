package ueb.rooms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Grundlegende Tests für Room.
 * <p>
 * Herausgegebene Tests müssen bei der Abnahme bestanden werden, um Chance auf eine Nachbesserung zu erhalten.
 * Erstellen Sie gerne eigene Tests in einer Testdatei ohne das Präfix "Pub".
 *
 * @author #####
 */
public class PubRoomTest {

    @Test
    public void invalidUserInputOnRoom() {
        Room oneRoom = new Room("One", "");
        Room otherRoom = new Room("Other", "");
        oneRoom.setConnections(new Room[]{otherRoom});
        otherRoom.setConnections(new Room[]{oneRoom});
        //bei jeder der nicht erlaubten Eingaben verbleibt der Nutzer im Raum
        assertAll(
                () -> assertSame(oneRoom, oneRoom.processDecision(""), "Leerstring ist keine sinnvolle Eingabe"),
                () -> assertSame(oneRoom, oneRoom.processDecision("1"), "es gibt keinen zweiten verbundenen Raum"),
                () -> assertSame(oneRoom, oneRoom.processDecision("-1"), "nur ein Zeichen ist erlaubt"),
                () -> assertSame(oneRoom, oneRoom.processDecision("foobar"), "nur ein Zeichen ist erlaubt")
        );
    }

    @Test
    public void runInCircle() {
        Room aRoom = new Room("A", "");
        Room bRoom = new Room("B", "");
        Room cRoom = new Room("B", "");
        aRoom.setConnections(new Room[]{cRoom, bRoom});
        bRoom.setConnections(new Room[]{aRoom, cRoom});
        cRoom.setConnections(new Room[]{bRoom, aRoom});
        //jeweils mit der Eingabe von "0" wird in den vorigen Raum gewandert,
        // mit Eingabe von "1" in den nächsten bzw. wieder ursprünglichen
        assertAll(
                () -> assertSame(cRoom, aRoom.processDecision("0"), "von a rückwärts muss c kommen"),
                () -> assertSame(bRoom, aRoom.processDecision("1"), "von a vorwärts muss b kommen"),
                () -> assertSame(aRoom, bRoom.processDecision("0"), "von b rückwärts muss a kommen"),
                () -> assertSame(cRoom, bRoom.processDecision("1"), "von b vorwärts muss c kommen"),
                () -> assertSame(bRoom, cRoom.processDecision("0"), "von c rückwärts muss b kommen"),
                () -> assertSame(aRoom, cRoom.processDecision("1"), "von c vorwärts muss a kommen")
        );
    }
}
