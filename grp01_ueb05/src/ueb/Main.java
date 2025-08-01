package ueb;

import ueb.rooms.*;

import java.util.Scanner;

/**
 * Die Hauptklasse für ein konsolen basiertes Text-Adventure.
 *
 * @author ##### & Christoph
 */
public class Main {

    /**
     * Erstellt alle Räume des Pyramiden-Textadventures und liefert den Startraum.
     *
     * @return Der Raum, in dem das Spiel startet.
     */
    public static Room createPyramidWorld() {
        // erstelle alle Räume
        Room quit = new QuitGame();

        Room start = new Room(
                "Draußen",
                "Du stehst vor der alten Pyramide.\n");

        Room longHallway = new Room(
                "Langer Gang",
                "Dein Weg führt dich zu einer Kreuzung. Links befindet sich ein schmaler Raum. " +
                        "Rechts befindet sich ein großer Raum. Dazwischen ist ein dunkler Korridor.\n");

        Riddle entrance = new Riddle(
                "Eingang",
                "Du stehst am Eingang. An der Wand neben dir klebt ein Zettel.\n\n",
                "0000",
                longHallway,
                "Du musst die Tür entriegeln, bevor du die Pyramide betreten kannst. Auf dem Schloss " +
                        "steht „Bitte PIN eingeben“.\n",
                "Die Tür ist offen. Vor dir ist ein langer Gang, der in die düsteren Tiefen " +
                        "der alten Pyramide führt.\n");

        Clue note = new Clue(
                "Zettel",
                "Auf dem Zettel steht „PIN: 0000“\n\n",
                "Dieser Zettel wurde x Mal gelesen.\n",
                (byte) 42);


        Room tightRoom = new Room(
                "Schmaler Raum",
                "Der Boden des schmalen Raumes ist mit Trümmern übersät und alles ist mit Staub bedeckt." +
                        "\nEin paar Hieroglyphen sind erkennbar: „Ziffern sind lächerlich, Sterblicher. Rufe SEINEN " +
                        "Namen und tritt ein\".\n");

        Room largeRoom = new Room(
                "Großer Raum",
                "Der große Raum ist aufwendig verziert. Mit goldener Farbe steht „CHEOPS“ überall an " +
                        "den Wänden.\n");

        Trap burialChamber = new Trap(
                "Grabkammer",
                "Du bewunderst die Schätze der Grabkammer im Herzen der Pyramide.\n",
                "Notiere deine Beobachtung und mache ein paar Fotos.",
                "Du hast Fotos gemacht und alles dokumentiert\n",
                "Stehle den Schatz",
                "Oh nein! Das Grab hat sich geöffnet die Mumie verfolgt dich.\n");

        Riddle darkCorridor = new Riddle(
                "Dunkler Korridor",
                "Der dunkle Korridor führt tiefer in die Pyramide.\n",
                "CHEOPS",
                burialChamber,
                "\nEin schwerer, großer Granitblock versperrt den Weg.",
                "\nDer Granitblock hat den Weg freigegeben!\n\n");

        Room death = new Room(
                "Sterben",
                "Du bist nicht geflohen, nachdem du den Schatz der Mumie gestohlen hast. Nun wird " +
                        "die Pyramide auch dein Grab sein.\n");

        // erstelle die Verbindungen zwischen den Räumen

        // Position: Draußen
        start.setConnections(new Room[]{quit, entrance});
        // Position: Eingang
        entrance.setConnections(new Room[]{start, note});
        // Position: Notiz
        note.setConnections(new Room[]{entrance});
        // Position: Langer Flur
        longHallway.setConnections(new Room[]{entrance, tightRoom, darkCorridor, largeRoom});
        // Position: Großer Raum
        largeRoom.setConnections(new Room[]{longHallway});
        // Position: Enger Raum
        tightRoom.setConnections(new Room[]{longHallway});
        // Position: Dunkler Flur
        darkCorridor.setConnections(new Room[]{longHallway});
        // Position: Grabkammer
        burialChamber.setConnections(new Room[]{darkCorridor, death});
        // Position: Tod
        death.setConnections(new Room[]{quit});


        // liefere den Startraum
        return start;
    }

    /**
     * Gestaltet den gesamten Spielablauf beginnend beim Startraum.
     *
     * Diese Methode darf nicht geändert werden. {@code System.out} darf ausschließlich
     * in dieser Methode genutzt werden, keine andere Stelle darf Ausgaben tätigen.
     *
     * @param args – ungenutzt
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Room currentRoom = createPyramidWorld();
        // TODO - DONE while benutzten anstatt do-while
        while (!currentRoom.isFinal()) {
            System.out.print(currentRoom.describe());
            System.out.println();
            System.out.print(currentRoom.getOptions());
            System.out.print("Deine Wahlt: ");
            // TODO - DONE "Do not use embedded assignments in an attempt to improve run-time performance. This is the job of the compiler"
            currentRoom = currentRoom.processDecision(sc.next());
        }
        System.out.print(currentRoom.describe());
    }
}
