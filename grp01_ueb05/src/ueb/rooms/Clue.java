package ueb.rooms;

/**
 * Diese Klasse repräsentiert ein Hinweisraum/ Gegenstand. Sprich, Räume/ Gegenstände die Informationen erhalten.
 * Beispielsweise für die Lösung eines Rätsels.
 *
 * @author ##### & Christoph
 */


public class Clue extends Room {
    private byte initialCount;
    private String dynamicHint;



    /**
     * Dieser Konstruktor erzwingt für jeden Raum einen Namen und eine Beschreibung.
     *
     * Zusatz: Dieser Konstruktor ermöglicht die Aufnahme einer Startzahl, ab der gezählt wird.
     *
     * @param name        Der Name des Raumes.
     * @param description Die Beschreibung, die angezeigt wird, wenn ein Spieler den Raum betritt.
     * @param dynamicHint Eine optionale Beschreibung, die dynamisch ist, sprich Inhalte hat (z.b. einen Zähler) der
     *                    sich verändern kann.
     * @param initialCount Der Zähler für die dynamische Schreibung (ebenfalls optional).
     */
    public Clue(String name, String description, String dynamicHint, byte initialCount) {
        super(name, description);
        this.initialCount = initialCount;
        this.dynamicHint = dynamicHint;

    }

    @Override
    public Room processDecision(String userInput) {
        char c;
        if(userInput != null
                && !userInput.isEmpty()
                && (c = userInput.charAt(0)) - '0' >= 0
                && c - '0' < connectedRooms.length){
            return super.processDecision(userInput);
        } else {
            return this;
        }
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


        StringBuilder mainText = new StringBuilder();
        StringBuilder riddleSolvedText = new StringBuilder();

        // String zusammensetzen
        mainText.append(super.describe());
        riddleSolvedText.append(dynamicHint);
        riddleSolvedText.replace(dynamicHint.indexOf('x'), dynamicHint.indexOf('x') + 1, String.format("%d", initialCount++));
        mainText.append(riddleSolvedText);

        // Finale Ausgabe
        return mainText.toString();

    }
}
