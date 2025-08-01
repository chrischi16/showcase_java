package ueb;

/**
 * Diese Klasse spiegelt ein Element wider, welches ein Wegpunkt als Datum aufnehmen kann, als auch eine Verlinkung auf
 * ein nächstes Element enthält.
 *
 * @author ##### & Christoph
 */

public class TourElement {
    private final Waypoint    waypoint;
    private       TourElement next;

    //<editor-fold desc="Enthält mehrere Methoden die zur Erstellung und Handhabung von Listenelementen dienen.">
    // Den Wegpunkt für das Listenelement setzen
    public TourElement(Waypoint waypoint) {
        this(waypoint, null);
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
//        int waypointCounter = 1;
//
//        if (this.hasNext()) {
//            waypointCounter += this.next.getNoOfWaypoints();
//        }
//        // return waypointCounter;
        return (hasNext())
                ? 1 + this.next.getNoOfWaypoints()
                : 1;

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
//        double distance;
//        if (hasNext()) {
//            distance = this.waypoint.calcDistance(this.next.waypoint) + this.next.calcDistance();
//        } else {
//            distance = 0;
//        }
//        return distance;
        return (hasNext())
                ? this.waypoint.calcDistance(this.next.waypoint) + this.next.calcDistance()
                : 0;
    }

    /**
     * Gibt den Wegpunkt an Stelle Index aus der Liste zurück. Ist der Index ungültig, wird null zurückgegeben.
     *
     * @param index - betroffene Stelle
     * @return Wegpunkt an der angesprochenen Stelle
     */

    public Waypoint getAt(int index) {

        return (index > 0 && hasNext())
                ? this.next.getAt(index-1)
                : (index == 0)
                    ? this.waypoint
                    : null;
    }

    /**
     * Fügt den Wegpunkt vor das erste Element ein
     *
     * @param waypoint - der einzufügende Wegpunkt
     */

    public TourElement addStart(Waypoint waypoint) {
        return new TourElement(waypoint, this);
    }


    /**
     * Fügt den Wegpunkt am Ende der Liste an
     *
     * @param waypoint - der einzufügende Wegpunkt
     */

    public TourElement append(Waypoint waypoint) {
        this.next = (hasNext())
                ? this.next.append(waypoint)
                : new TourElement(waypoint);
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
//        if (index < 0) return this;
//
//        // Fall - Index = 0
//        if (index == 0) {
//            TourElement newTourElement = new TourElement(waypoint);
//            newTourElement.next = this;
//            return newTourElement;
//        }
//
//        // Normaldurchlauf: Abzählen, bis Index erreicht
//        if (hasNext()) {
//            this.next = this.next.insertAt(index -1, waypoint);
//        }
//        else if (index == 1) {
//            // Index ist 1 nach dem letzten Element
//            this.next = new TourElement(waypoint);
//        }
//
//        return this;

        return (index < 0) // falle es invalider index unter 0 ist
                ? this
                : (index == 0) // fals es index 0 ist
                    ? addStart(waypoint)
                    : new TourElement(this.waypoint, (hasNext()) // falls es ein nächstes hat
                                                        ? this.next.insertAt(index -1, waypoint)
                                                        : (index == 1) // falls es index 1 ist, auch wen es kein nächstes hat
                                                            ? new TourElement(waypoint)
                                                            : this.next
                    );


    }

    /**
     * Löscht das Element an Stelle Index aus der Liste. Ist der Index ungültig, erfolgt keine Änderung.
     *
     * @param index - Löschposition
     */

    public TourElement removeAt(int index) {

        return (index == 0)
                ? this.next
                : (index > 0 && hasNext())
                    ? new TourElement(this.waypoint, this.next.removeAt(index-1))
                    : this;
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
    //</editor-fold>

    /**
     * Konstruktor die Wegpunkterstellung, als auch den Zugriff auf die Verlinkung ermöglicht .
     *
     * @param waypoint - der übergebene Wegpunkt
     * @param next - Verlinkung zum nächsten Wegpunkt
     */

    //TODO - DONE JavaDoc fehlt
    public TourElement(Waypoint waypoint, TourElement next){
        if (waypoint == null){
            throw new IllegalArgumentException("'null' ist kein Wegpunkt und darf nicht zugefügt werden");
        }
        this.waypoint = waypoint;
        this.next = next;
    }

    /**
     * Vergleicht, ob beide Listen die gleichen Wegpunkte in der gleichen Reihenfolge enthalten (und auch keinen mehr
     * oder weniger).
     * Nutzt Waypoint.isEqual().
     * Ist der Parameter "null" soll false herauskommen.
     *
     * @param tourElement - Der zu vergleichende Wegpunkt
     * @return boolean - sind die Wegpunkte gleich?
     */
    public boolean isEqual(TourElement tourElement){
//        if (tourElement == null) return false
//        else if (tourElement.getNoOfWaypoints() != getNoOfWaypoints()) return false;
//        else if (!this.waypoint.isEqual(tourElement.waypoint)) return false;
//        else if (tourElement.hasNext() && hasNext()) return this.next.isEqual(tourElement.next);
//        else return !tourElement.hasNext() && !hasNext();

        return tourElement != null
                && this.waypoint.isEqual(tourElement.waypoint)
                && (!(tourElement.hasNext() && hasNext())
                ? !tourElement.hasNext() && !hasNext()
                : this.next.isEqual(tourElement.next));
    }

    /**
     * Gibt das letzte Element einer Liste zurück
     *
     * @return TourElement - letztes Element einer Liste
     */

    public TourElement getLastElement(){
//        if (hasNext()) {
//            return next.getLastElement();
//        } else {
//            return this;
//        }

        return (hasNext())
                ? next.getLastElement()
                : this;
    }

    /**
     * Hängt die Liste other an das Ende dieser Liste.
     *
     * @param other - TourElement, welches an das Ende der Liste angehängt wird
     * @return TourElement - das jetzige element mit seinen neuen nachfolgern
     */

    public TourElement concat(TourElement other){
        //TODO - DONE Liste sollte nicht neu aufgebaut werden
        if (hasNext())
        {
            this.next = this.next.concat(other);
        } else{
            this.next = other;
        }
        return this;

    }

    /**
     * Liefert den Index des Listenelements zurück, dessen Wegpunkt die kürzeste Distanz zum übergebenen aufweist.
     *
     * Sind die zwei Wegpunkte der Liste gleich weit voneinander entfernt, ist der Index des ersten von beiden
     * zurückzugeben.
     * Für die Rekursion ist eine private hilfsmethode notwendig, die mehr Parameter aufnimmt und somit
     * diese Methode überlebt. Wird als Parameter "null" übergeben, soll eine IllegalArgumentException ausgelöst werden.
     *
     * @param waypoint - der Wegpunkt der verglichen werden soll
     * @return int - index vom nächsten Wegpunkt der in Bezug zu Waypoint steht
     *
     */

    public int getIdxOfClosestWaypoint(Waypoint waypoint){
        return getIdxOfClosestWaypoint(waypoint, this.waypoint, 0);
    }
    /**
     * Liefert den Index des Listenelements zurück, dessen Wegpunkt die kürzeste Distanz zum übergebenen aufweist.
     * sind die zwei Wegpunkte der Liste gleich weit voneinander entfernt, wird der Index des ersten der beiden Punkte
     * zurückzugeben.
     *
     * Wird als Parameter "null" übergeben, soll eine IllegalArgumentException ausgelöst werden.
     *
     * @param waypoint übergebener Wegpunkt aus der public {@link #getIdxOfClosestWaypoint(Waypoint)} Methode oder
     *                 durch Aufruf als {@link this.next.#getIdxOfClosestWaypoint(Waypoint, Waypoint, int)} Methode,
     *                 weiterverarbeitet wird und den index des zum Wegpunkt kürzesten index der liste ermittelt
     * @param closest der bisher nächste wegpunkt
     * @param idx der Index des momentan zu prüfenden Wegpunktes in der Liste
     * @return den index, der am kürzesten ist
     *
     */
    private int getIdxOfClosestWaypoint(Waypoint waypoint, Waypoint closest, int idx){
        if (waypoint == null) throw new IllegalArgumentException("'null' ist kein gültiger Wegpunkt");

        int idxReturn = 0;
        if (waypoint.calcDistance(closest) > waypoint.calcDistance(this.waypoint)) {
            idxReturn = idx;
            closest = this.waypoint;
        }
        int idxNext = (hasNext()) ? this.next.getIdxOfClosestWaypoint(waypoint, closest, idx+1) : 0;
        if (idxNext > 0) idxReturn = idxNext;
        return idxReturn;
    }









}
