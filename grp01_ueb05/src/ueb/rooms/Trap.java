package ueb.rooms;


/**
 * Diese Klasse repräsentiert ein Fallenraum. Sprich ein Raum, der eine sichere Option und eine gefährliche Option
 * enthält, die dazu führen kann, das der Spieler in seinem Play-through beeinträchtigt wird.
 *
 * @author ##### & Christoph
 */

public class Trap extends Room{

    // FIXME - DONE zusätzliche Optionen direkt als String[] übergeben, dann könnt ihr euch die doppelt abspeicherung durch availableActions sparen
    private final String[] safeOption = new String[2];
    private final String[] trapOption = new String[2];

    /**
     * Ausgabestring für folge aufruf nach einer zusatzOptionsAusführung
     */
    private String currentOption = null;
    /**
     * Zusätzliche Aktionen, per Default {false, false}
     */
    protected boolean[] availableActions = new boolean[]{false, false};

    /**
     * Eine Lokale variable, die zur identifizierung des Zustandes ist, im falle die {@link #trapOption} wird getriggert
     */
    // FIXME 2 - DONE stepIntoTrap durch eine boolean ersetzten
    private boolean stepsIntoTrap = false;

    /**
     * Dieser Konstruktor erzwingt für jeden Raum einen Namen und eine Beschreibung.
     *
     * @param name        Der Name des Raumes.
     * @param description Die Beschreibung, die angezeigt wird, wenn ein Spieler den Raum betritt.
     */
    public Trap(String name, String description, String safeOption, String safeOptionalText, String trapOption,
                String triggeredTrapText) {
        super(name, description);
        this.safeOption[0] = safeOption;
        this.safeOption[1] = safeOptionalText;
        this.trapOption[0] = trapOption;
        this.trapOption[1] = triggeredTrapText;
        if (safeOption != null || trapOption != null){
            availableActions = new boolean[2];
            availableActions[0] = this.safeOption[0] != null;
            availableActions[1] = this.trapOption[0] != null;
        }
    }

    /**
     * Erstellt einen String mit den Optionen, aus denen der Spieler wählen kann.
     *<p>
     * Diese Methode kann überschrieben werden, um absichtlich Optionen vor dem Spieler zu verstecken.
     * Es kann auch zusätzliche Optionen geben, die sich nicht aus den verbundenen Räumen ergeben.
     * Diese Methode ist ein Getter. Sie darf den Status des Raumes nicht ändern.
     * Beispielausgabe:
     * "Hier gibt es diese Möglichkeiten:\n0: Gehe zurück\n"
     * <p>
     * Zusatz: fügt zusätzliche Optionen/ Aktionen hinzu
     *
     * @return Der String mit der Beschreibung der verfügbaren Optionen. Endet immer mit newline `\n`.
     */
    public String getOptions() {
        StringBuilder options = new StringBuilder();
        options.append(OPTION_HEADER);
        byte optionsCounter = 0;
        if (stepsIntoTrap && connectedRooms.length > 0){
            options.append(0).append(": ").append(connectedRooms[connectedRooms.length-1].getName()).append("\n");
        } else if (connectedRooms.length > 0){
            for (int i = 0; i < connectedRooms.length-1; i++) {
                options.append(i).append(": ").append(connectedRooms[i].getName()).append("\n");
                optionsCounter++;
            }
            options.append(getAvailableOptions(optionsCounter));
        }
        return options.toString();
    }

    @Override
    public String describe() {
        if (currentOption != null){
            return currentOption + "\n" + super.describe();
        }
        return super.describe();
    }

    // FIXME 2 - DONE man muss die Funktion nicht überschreiben, da zu große Indexe durch proceedToRoom abgefangen werden


    /**
     * Wählt und gibt den nächsten Raum aus. verarbetiet ebenso den fall der {@link #availableActions}
     * @param connectionIndex Der Index, zu dem der verbundene Raum geliefert werden soll.
     *
     * @return Den Raum der gewählt wurde
     */
    @Override
    protected Room proceedToRoom(int connectionIndex) {
        currentOption = null;
        // if index in range and there are available Actions
        if (connectionIndex <= availableActions.length && connectionIndex > 0){
            // if has safeOption, do safeoption
            // FIXME - DONE keine String vergleiche -> vergleicht lieber den connectionIndex direkt mit z.B einem Index oder einen Enum
            // FIXME 2 - DONE keine MagicNumber -> nutzt lieber connectecRooms.length, so kann man dann auch theoretisch mehrere Räume an die Grabkammer anhängen
            if (connectionIndex == connectedRooms.length-1 && availableActions[0]) {
                // if fallen into trap, die
                if (!availableActions[1]){
                    stepsIntoTrap = true;
                }

                // FIXME - DONE keine System.out.println() außerhalb der Main.main(String[] args)
                currentOption = safeOption[1];
                availableActions[connectionIndex-1] = false; // remove saveOption
                return this;
            }
            // if has trapOption, do trapOption
            // FIXME - DONE keine String vergleiche -> vergleicht lieber den connectionIndex direkt mit z.B einem Index oder einen Enum
            // FIXME 2 - DONE keine MagicNumber -> nutzt lieber connectecRooms.length
            else if (connectionIndex == connectedRooms.length && availableActions[1]){
                // FIXME - DONE keine System.out.println() außerhalb der Main.main(String[] args)
                currentOption = trapOption[1];
                availableActions[connectionIndex-1] = false; // remove trapOption

                return this;
            }
        }
        if (!availableActions[1]){ // fallen into trap
            if (stepsIntoTrap) return connectedRooms[connectedRooms.length-1]; // died
            stepsIntoTrap = true; // did not die but leave, so next time you enter, you die
        }
        return super.proceedToRoom(connectionIndex);
    }

    /**
     * gibt alle Optionen von {@link #availableActions} aus, wenn welche vorhanden
     * @param index von welchen auswahl Index gestartet werden soll beim ausgabe String
     * @return einen fertig gebildeten String
     */
    private String getAvailableOptions(int index){
        StringBuilder sb = new StringBuilder();
        // FIXME 2 - DONE Wenn Foto gemacht wurde und der Schatz noch da ist, soll die Stehlen-Option gleich zur 1 werden und nicht 2 bleiben
        if (availableActions.length != 0){
            if (availableActions[0] && availableActions[1]){
                for (int i = 0; i < availableActions.length; i++){
                    sb.append(String.format("%d: %s\n", index + i, (i == 0) ? safeOption[0] : trapOption[0]));
                }
            } else {
                if (availableActions[0]){
                    sb.append(String.format("%d: %s\n", index ,safeOption[0]));
                } else {
                    sb.append(String.format("%d: %s\n", index ,trapOption[0]));
                }
            }
        }
        return sb.toString();
    }
}
