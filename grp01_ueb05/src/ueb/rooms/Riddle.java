package ueb.rooms;

import java.util.Objects;

/**
 * Diese Klasse repräsentiert ein Rätselraum. Sprich, Räume für die man ein Rätsel lösen muss (Passwort, Pin, etc ...)
 * um einen dahinterliegenden Raum freizuschalten.
 *
 * @author ##### & Christoph
 */


public class Riddle extends Room {
    private final String solution;
    private final Room blockedPath;
    private final String optionalText;
    private final String optionalTextRiddleSolved;
    private Boolean riddleSolved = false;

    /**
     * Dieser Konstruktor erzwingt für jeden Raum einen Namen und eine Beschreibung.
     *
     * @param name        Der Name des Raumes.
     * @param description Die Beschreibung, die angezeigt wird, wenn ein Spieler den Raum betritt.
     * @param solution    Die Lösung für das Rätsel.
     * @param blockedPath Der versperrte Weg/ Raum.
     * @param optionalText Der optionale Text, der nach Lösen des Rätsels angezeigt werden soll.
     * @param optionalTextRiddleSolved Der optionale Text, wenn das Rätsel gelöst wurde.
     */
    public Riddle(String name, String description, String solution, Room blockedPath, String optionalText,
                  String optionalTextRiddleSolved) {

        super(name, description);
        this.solution = solution;
        this.blockedPath = blockedPath;
        this.optionalText = optionalText;
        this.optionalTextRiddleSolved = optionalTextRiddleSolved;
    }

    /**
     * Beschreibt den Raum.
     * Diese Methode kann den Status des Raums verändern.
     * <p>
     * Zusatz: Fügt zusätzlich die optionale Beschreibung hinzu
     *
     * @return Textuelle Beschreibung des Raums mit optionaler Beschreibung.
     */

    @Override
    public String describe() {
        // Strings zu StringBuilder umwandeln
        StringBuilder descriptionText = new StringBuilder(super.describe());
        descriptionText.append(riddleSolved ? optionalTextRiddleSolved : optionalText);
        return descriptionText.toString();
    }

    /**
     * Verarbeitet die Nutzereingabe.
     *
     * Erwartet einen String, der eine einzelne Ziffer enthält. Diese Methode kann überschrieben werden,
     * um kompliziertere Eingaben zu verarbeiten.
     *
     * Diese Methode kann den Status des Raumes verändern.
     *
     * @param userInput Die Benutzereingabe als String.
     *
     * @return Der neue Raum, in den der Spieler wandert gemäß der verbundenen Räume.
     *         Enthält der Parameter kein Zeichen oder mehr Zeichen als erwartet,
     *         wird dieser Raum zurückgegeben (der Spieler bewegt sich nicht).
     */
    public Room processDecision(String userInput) {
        if (userInput != null && userInput.length() > 1 && userInput.equals(solution)) {
            unlockPath();
            riddleSolved = true;
            return this;
        }
        char c;
        if (userInput != null
                && !userInput.isEmpty()
                && (c = userInput.charAt(0)) - '0' >= 0
                && c - '0' < connectedRooms.length
        ) {
            return super.processDecision(userInput);
        } else {
            return this;
        }
    }

    /**
     * Schaltet den Weg frei und aktualisiert die Raumverbindungen
     */
    private void unlockPath() {
        Room[] connectedRooms2 = new Room[connectedRooms.length + 1];
//      connectedRooms2 = connectedRooms.clone();
        for (int i = 0; i < connectedRooms.length; i++) {
            connectedRooms2[i] = connectedRooms[i];
        }
        connectedRooms2[connectedRooms2.length-1] = blockedPath;
        this.setConnections(connectedRooms2);
    }

}
