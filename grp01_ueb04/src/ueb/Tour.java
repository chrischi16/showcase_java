package ueb;

/**
 * Diese Klasse ist eine Liste, die Elemente enthalten kann.
 *
 * @author ##### & Christoph
 */

public class Tour {

    private TourElement tourElements;

    //<editor-fold desc="Enthält Methoden die zur Erstellung, Untersuchung und Bearbeitung eines Objekt: Liste dienen">

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
        if (isEmpty())
            return null;
        return tourElements.getWaypoint();
    }

    /**
     * Liefert die Anzahl der enthaltenen Wegpunkte
     *
     * @return Anzahl der enthaltenden Wegpunkte
     */

    public int getNoOfWaypoints() {
        if (isEmpty())
            return 0;
        return tourElements.getNoOfWaypoints();
    }

    /**
     * liefert die Gesamtlänge der Tour beim Aufsuchen aller Wegpunkte
     *
     * @return Gesamtlänge der Tour
     */

    public double calcDistance() {
        if (isEmpty())
            return 0;
        return tourElements.calcDistance();
    }

    /**
     * Liefert den Wegpunkt an der Stelle index, bei ungültigem Index wird null zurückgegeben
     *
     * @param index - Untersuchungsindex
     * @return Wegpunkt am Untersuchungsindex
     */

    public Waypoint getAt(int index) {
        if (isEmpty())
            return null;
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
        if (isEmpty()) {
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
     * Fügt den übergebenen Wegpunkt am Index in die Liste ein. Ist der Index ungültig, wird die Tour nicht verändert.
     * In eine leere Liste soll nur eingefügt werden, wenn als Index 0 angegeben wird.
     *
     * @param index    - Einfügeposition
     * @param waypoint - Wegpunkt
     */

    public void insertAt(int index, Waypoint waypoint) {
        if (isEmpty() && index == 0) {
            tourElements = new TourElement(waypoint);
        } else if (!isEmpty()) {
            TourElement newList = tourElements.insertAt(index, waypoint);
            if (newList != null)
                tourElements = newList;
        }
    }

    /**
     * entfernt das Element am Index aus der Liste. Ist der Index ungültig, wird die Tour nicht verändert.
     *
     * @param index - Löschposition
     */

    public void removeAt(int index) {
        if (!isEmpty())
            tourElements = tourElements.removeAt(index);
    }

    /**
     * liefert eine Stringdarstellung der Tourelemente umgeben von eckigen Klammern, Bsp: "[(0/0) -> (1/2)]"
     *
     * @return Stringdarstellung der Tourelemente
     */

    public String toString() {
        String result;
        if (isEmpty()) {
            result = "[]";
        } else {
            result = "[" + tourElements.toString() + "]";
        }
        return result;
    }
    //</editor-fold>

    /**
     * Konstruktor von Tour der eine Leere Tour erzeugt
     */
    public Tour() {
        this.tourElements = null;
    }

    /**
     * Konstruktor von Tour der eine Tour anhand eines Arrays an Wegpunkten erzeugt
     *
     * @param waypoints - Array an Wegpunkten
     */
    public Tour(int[][] waypoints) {
        this();
        if (waypoints != null) {
            for (int i = 0; i < waypoints.length; i++) {
                append(new Waypoint(waypoints[i][0], waypoints[i][1]));
            }
        }
    }

    /**
     * prüft, ob zwei Touren gleichwertig sind
     *
     * @param other - die zu vergleichende Tour
     * @return das Ergebnis der Überprüfung
     */
    public boolean isEqual(Tour other) {
        if (other == null)
            return false;
        if (this.isEmpty() && other.isEmpty())
            return true;
        if (this.isEmpty() || other.isEmpty())
            return false;
        return this.tourElements.isEqual(other.tourElements);
    }

    /**
     * Kopiert eine Tour und gibt sie wieder aus. die TourElemente sind keine Referenzen auf die der originalen Tour,
     * doch die Wegpunkte der TourElemente sind Referenzen auf die Wegpunkte der TourElemente der Originaltour
     *
     * @return die kopierte Tour
     */
    public Tour copy() {
        Tour        copyOfTour = new Tour();
        TourElement iterator   = this.tourElements;
        while (iterator != null) {
            copyOfTour.append(iterator.getWaypoint());
            iterator = iterator.getNext();
        }
        return copyOfTour;
    }

    /**
     * Erstellt eine Tour die an einer anderen Tour rangehängt wird
     *
     * @param other - die anzuhängende Tour
     * @return Tour mit der angehängten tour
     */
    public Tour createConcatenatedTour(Tour other) {
        //TODO - DONE es muss eine neue Tour zurückgegeben werden
        //TODO - DONE diese und die gegebene Tour dürfen nicht verändert werden

        //TODO 2: - DONE vor der Korrektur ist korrekt abgefangen worden, dass other == null sein kann;
        // das muss auch weiterhin korrekt abgefangen werden (ist other == null, soll
        // other wie die leere Liste behandelt werden)
        Tour newTour = copy();

        // Abfangen von 'other == null' und behandlung zur 'leeren Tour'
        if (other == null){
            other = new Tour();
        }
        // --

        Tour cloneOther = other.copy();
        if (isEmpty()) {
            newTour.tourElements = cloneOther.tourElements;
        } else {
            newTour.tourElements = newTour.tourElements.concat(cloneOther.tourElements);
        }

        return newTour;
    }

    /**
     * Erstellt eine Tour aus dieser tour, die nach übergebener index reihenfolge neu erstellt wird, mit keiner referenz
     * auf die original tour elementen, sondern neuen elementen mit den waypoints der gewünschten indexen
     *
     * @param indices - Array an Wegpunkten
     * @return die neue Tour
     */
    public Tour createTourWithOrder(int[] indices) {
        Tour newTour = new Tour();
        if (indices != null && !isEmpty()) {
            for (int i = 0; i < indices.length; i++) {
                Waypoint indexedWaypoint = getAt(indices[i]);
                if (indexedWaypoint != null)
                    newTour.append(indexedWaypoint);
            }
        }
        return newTour;
    }

    /**
     * Erstellt die beliebteste Tour aus dieser und einer anderen Tour, aus Wegpunkten
     *
     * @param other - die hinzuzufügende Tour
     * @return die beliebteste Tour
     */
    public Tour createPopularTour(Tour other) {
        //TODO - DONE immer eine neue Tour zurückgeben; ist eine Tour leer, gibt es keine gemeinsamen Elemente
        Tour newTour = new Tour();
        if (other == null || other.isEmpty() || isEmpty())
            return newTour;

        TourElement checkingElement = this.tourElements;
        while (checkingElement != null) {
            TourElement checkingOtherElement = other.tourElements;
            while (checkingElement != null && checkingOtherElement != null) {
                if (checkingElement.getWaypoint().isEqual(checkingOtherElement.getWaypoint())) {
                    newTour.append(checkingElement.getWaypoint());
                    checkingElement      = checkingElement.removeAt(0);
                    checkingOtherElement = checkingOtherElement.removeAt(0);
                } else {
                    checkingOtherElement = checkingOtherElement.getNext();
                }
            }
            if (checkingElement != null) {
                checkingElement = checkingElement.getNext();
            }
        }
        return newTour;
    }

    /**
     * erstellt die kürzeste tour von einem beliebigen startpunkt aus mit dieser tour als basis
     *
     * @param idxStartPnt - Startpunkt
     * @return die kürzeste Tour
     */
    public Tour createShortestTour(int idxStartPnt) {
        //TODO - DONE bei der leeren Tour soll eine neue, leere Tour zurückgegeben werden
        //TODO - DONE Bei invalidem Startindex soll eine leere Tour zurückgegeben werden.
        Tour newTour = new Tour();
        Waypoint startWaypoint = getAt(idxStartPnt);
        if (isEmpty() || startWaypoint == null){
            return newTour;
        }

        newTour.addStart(getAt(idxStartPnt));

        Tour clone = copy();
        clone.removeAt(idxStartPnt);

        while (!clone.isEmpty()) {
            int getIdxClosest = clone.tourElements.getIdxOfClosestWaypoint(
                    newTour.tourElements.getLastElement().getWaypoint());

            Waypoint closestWaypoint = (getIdxClosest >= 0) ? clone.getAt(getIdxClosest) : null;
            if (closestWaypoint != null)
                newTour.append(closestWaypoint);
            clone.removeAt(getIdxClosest);
        }
        return newTour;
    }

    /**
     * erstellt eine tour wo kein waypoint zweimal vorkommt
     *
     * @return - Tour ohne Duplikate
     */
    public Tour createTourWithoutDuplicates() {
        Tour        newTour      = new Tour();
        TourElement checkElement = this.tourElements;
        while (checkElement != null) {
            Waypoint wp = checkElement.getWaypoint();
            if (!newTour.contains(wp)) {
                newTour.append(wp);
            }
            checkElement = checkElement.getNext();
        }
        return newTour;
    }

    /**
     * erstellt eine vereinigung zweier touren mit jeweils der kürzesten distanz zum nächsten element im vordergrund der
     * einigung, wann aus welcher liste welches element als nächstes hinzugefügt wird
     *
     * @param other - Tour B die mit Tour A vereinigt wird
     * @return vermischte Tour
     */
    public Tour createUnion(Tour other) {
        //TODO - DONE es müssen die bestehenden Methoden verwendet werden: createConcatenatedTour, createShortestTour, createWithoutDuplicates (Reihenfolge nicht der Methoden nicht unbedingt richtig)

        //TODO 2: - DONE durch die Korrektur ist ein weiterer Fehler entstanden: ist this eine leere Tour,
        // gibt es eine Exception
        //FIXME: - DONE Methode kann vereinfacht werden: die aufgerufenen Methode übernehmen bereits
        // Überprüfungen ob die Touren leer sind (oder other == null ist), daher müssen das
        // nicht hier erfolgen
        //FIXME: - DONE es sollte jede der verwendeten Methoden (createConcatenatedTour, createTourWithoutDuplicates,
        // createShortestTour) nur einmal aufgerufen werden

        Tour newTour = new Tour();

        //FIXME 2: -DONE Überprüfung ob other==null muss hier nicht erfolgen, wenn
        // direkt danach die anderen Methoden aufgerufen werden; eine Sonderbehandlung
        // für das erste Element ist nicht notwendig (ebenso für alle anderen Elemente)

        // -> removed Code was here

        //FIXME 2: - DONE kopieren bevor createConcatenatedTour aufgerufen wird, ist nicht  notwendig, da diese Methode bereits Kopien erstellt
        newTour = createConcatenatedTour(other); // concated
        newTour = newTour.createTourWithoutDuplicates(); // no duplicates
        newTour = newTour.createShortestTour(0); // shortest

        return newTour;
    }

}
