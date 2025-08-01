package ueb;

/**
 * Diese Klasse ist eine Liste, die Elemente enthalten kann.
 *
 * @author ##### & Christoph
 */

public class Tour {

    private TourElement tourElements;

    /**
     * Gibt an, ob Listenelemente enthalten sind
     *
     * @return boolean Elemente in Liste oder nicht?
     */

    public boolean isEmpty() {
        return tourElements == null;
    }

    /**
     * liefert den Startpunkt der Tour, null bei leerer Liste.
     *
     * @return Startpunkt der Tour
     */

    public Waypoint getStart() {
        if (isEmpty()) return null;
        return tourElements.getWaypoint();
    }

    /**
     * Liefert die Anzahl der enthaltenen Wegpunkte
     *
     * @return Anzahl der enthaltenden Wegpunkte
     */

    public int getNoOfWaypoints() {
        if (isEmpty()) return 0;
        return tourElements.getNoOfWaypoints();
    }

    /**
     * liefert die Gesamtlänge der Tour beim Aufsuchen aller Wegpunkte
     *
     * @return Gesamtlänge der Tour
     */

    public double calcDistance() {
        if (isEmpty()) return 0;
        return tourElements.calcDistance();
    }

    /**
     * Liefert den Wegpunkt an der Stelle index, bei ungültigem Index wird null zurückgegeben
     *
     * @param index - Untersuchungsindex
     * @return Wegpunkt am Untersuchungsindex
     */

    public Waypoint getAt(int index) {
        if (isEmpty()) return null;
        return tourElements.getAt(index);
    }

    /**
     * Prüft, ob ein Wegpunkt enthalten ist.
     *
     * @param waypoint - der zu untersuchende Wegpunkt
     * @return boolean ist Wegpunkt enthalten?
     */

    public boolean contains(Waypoint waypoint) {
        return !isEmpty() && tourElements.contains(waypoint);
    }

    /**
     * Fügt den übergebenen Wegpunkt an den Anfang der Liste.
     *
     * @param waypoint - Übergabewegpunkt
     */

    public void addStart(Waypoint waypoint) {
        if (isEmpty()){
            tourElements = new TourElement(waypoint);
        } else {
            tourElements = tourElements.addStart(waypoint);
        }
    }

    /**
     * Fügt den übergebenen Wegpunkt an das Ende der Liste
     *
     * @param waypoint - Übergabewegpunkt
     */

    public void append(Waypoint waypoint) {
        if (isEmpty())
            tourElements = new TourElement(waypoint);
        else
            tourElements = tourElements.append(waypoint);
    }

    /**
     * Fügt den übergebenen Wegpunkt am Index in die Liste ein. Ist der Index ungültig,
     * wird die Tour nicht verändert.
     * In eine leere Liste soll nur eingefügt werden, wenn als Index 0 angegeben wird.
     * @param index - Einfügeposition
     * @param waypoint - Wegpunkt
     */

    public void insertAt(int index, Waypoint waypoint) {
        if (isEmpty() && index == 0){
            tourElements = new TourElement(waypoint);
        } else if (!isEmpty()){
            TourElement newList = tourElements.insertAt(index, waypoint);
            if (newList != null) tourElements = newList;
        }
    }

    /**
     * entfernt das Element am Index aus der Liste. Ist der Index ungültig,
     * wird die Tour nicht verändert.
     * @param index - Löschposition
     */

    public void removeAt(int index) {
        if(!isEmpty())
            tourElements = tourElements.removeAt(index);
    }

    /**
     * liefert eine Stringdarstellung der Tourelemente umgeben von eckigen Klammern,
     * Bsp: "[(0/0) -> (1/2)]"
     * @return Stringdarstellung der Tourelemente
     */

    public String toString() {
        String result;
        if (isEmpty()){
            result = "[]";
        } else {
            result = "["+tourElements.toString()+"]";
        }
        return result;
    }

}
