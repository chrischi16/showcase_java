package ueb;

/**
 * Diese Klasse ist ein Element welches ein Wegpunkt als Datum und eine Verlinkung
 * auf ein nächstes Element enthält.
 *
 * @author ##### & Christoph
 */

public class TourElement {
    private Waypoint waypoint;
    private TourElement next;

    // Den Wegpunkt für das Listenelement setzen
    public TourElement(Waypoint waypoint) {
        if (waypoint == null) {
            throw new IllegalArgumentException("'null' ist kein Wegpunkt und darf nicht zugefügt werden");
        } else {
            this.waypoint = waypoint;
        }
    }

    /**
     * Den Wegpunkt erhalten
     *
     * @return Koordinaten des angesprochenen Wegpunktes
     */

    public Waypoint getWaypoint() {
        return this.waypoint;
    }

    /**
     * Die Verknüpfung des Wegpunktes erhalten
     *
     * @return Verknüpfung des betroffenen Wegpunktes
     */

    public TourElement getNext() {
        return this.next;
    }

    /**
     * Prüft, ob es ein nächstes Element gibt.
     *
     * @return boolean, ob es ein nächstes Element gibt
     */

    public boolean hasNext() {
        return this.next != null;
    }

    /**
     * Ermittelt die Anzahl der enthaltenen Wegpunkte.
     *
     * @return Anzahl der enthaltenen Wegpunkte
     */

    public int getNoOfWaypoints() {
        int waypointCounter = 1;

        if (this.hasNext()) {
            waypointCounter += this.next.getNoOfWaypoints();
        }
        return waypointCounter;

    }

    /**
     * Prüft, ob ein Wegpunkt enthalten ist (Waypoint.isEqual() zum Vergleich genutzt)
     *
     * @param waypoint - der angesprochene Wegpunkt
     * @return boolean ist Wegpunkt vorhanden, oder nicht?
     */

    public boolean contains(Waypoint waypoint) {
        return this.waypoint.isEqual(waypoint) || (hasNext() && this.next.contains(waypoint));
    }

    /**
     * Berechnet die Wegstrecke der gesamten Liste
     *
     * @return die Wegstrecke der Liste
     */

    public double calcDistance() {
        double distance;
        if (hasNext()) {
            distance = this.waypoint.calcDistance(this.next.waypoint) + this.next.calcDistance();
        } else {
            distance = 0;
        }
        return distance;
    }

    /**
     * Gibt den Wegpunkt an Stelle Index aus der Liste zurück. Ist der Index ungültig, wird null zurückgegeben.
     *
     * @param index - betroffene Stelle
     * @return Wegpunkt an der angesprochenen Stelle
     */

    public Waypoint getAt(int index) {

        Waypoint gesucht;

        if (index > 0 && hasNext()){
            gesucht = this.next.getAt(index-1);
        } else {
            if (index == 0){
                gesucht = this.waypoint;
            } else {
                gesucht = null;
            }
        }

        return gesucht;
    }

    /**
     * Fügt den Wegpunkt vor das erste Element ein
     *
     * @param waypoint - der einzufügende Wegpunkt
     */

    public TourElement addStart(Waypoint waypoint) {
        TourElement newStart = new TourElement(waypoint);
        newStart.next = this;
        return newStart;
    }


    /**
     * Fügt den Wegpunkt am Ende der Liste an
     *
     * @param waypoint - der einzufügende Wegpunkt
     */

    public TourElement append(Waypoint waypoint) {
        if (hasNext())
            this.next.append(waypoint);
        else
            this.next = new TourElement(waypoint);
        return this;

    }

    /**
     * Fügt einen Wegpunkt an Stelle index in die Liste ein. Ist der Index ungültig, erfolgt keine Änderung
     *
     * @param index    - Einfügeposition
     * @param waypoint - Einfügewegpunkt
     */

    public TourElement insertAt(int index, Waypoint waypoint) {
        // Fehlerfall negativer Index
        if (index < 0) return this;

        // Fall - Index = 0
        if (index == 0) {
            TourElement newTourElement = new TourElement(waypoint);
            newTourElement.next = this;
            return newTourElement;
        }

        // Normaldurchlauf: Abzählen, bis Index erreicht
        if (hasNext()) {
            this.next = this.next.insertAt(index -1, waypoint);
        }
        else if (index == 1) {
            // Index ist 1 nach dem letzten Element
            this.next = new TourElement(waypoint);
        }

        return this;
    }

    /**
     * Löscht das Element an Stelle Index aus der Liste. Ist der Index ungültig, erfolgt keine Änderung.
     *
     * @param index - Löschposition
     */

    public TourElement removeAt(int index) {
        TourElement result = this;
        if (index == 0) {
            if (hasNext()) {
                this.waypoint = this.next.waypoint;
                this.next = this.next.next;
            } else {
                result = null;
            }
        }
        // Abzählen, bis wann Index erreicht
        else if (index > 0 && hasNext()){
            this.next = this.next.removeAt(index-1);
        }
        return result;
    }

    /**
     * Gibt die String darstellung der enthaltenen Wegpunkte getrennt durch "→" zurück, Bsp: "(0/0) → (1/2)".
     * Benutzt hierfür die toString()-Methode aus der Klasse Waypoint.
     *
     * @return String darstellung der enthaltenen Wegpunkte
     */
    @Override
    public String toString() {
        String waypointsInString = waypoint.toString();

        if (this.hasNext()) {
            waypointsInString += " -> " + this.next.toString();
        }

        return waypointsInString;
    }
}
