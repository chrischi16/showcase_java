package ueb;

/**
 * Diese Klasse spiegelt einen Wegpunkt, bestehend aus einer
 * Koordinate (X/Y) wider.
 *
 * @author ##### & Christoph
 */
public class Waypoint {

    /**
     * X-Wert des Punktes. Darf sich nicht verändern.
     */
    private final int x;
    /**
     * Y-Wert des Punktes. Darf sich nicht verändern.
     */
    private final int y;

    /**
     * Konstruktor für einen Wegpunkt. Setzt die Attribute.
     *
     * @param x X-Wert des Punktes
     * @param y Y-Wert des Punktes
     */
    public Waypoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Liefert den getX-Wert.
     *
     * @return getX-Wert
     */
    public int getX() {
        return this.x;
    }

    /**
     * Liefert den getY-Wert.
     *
     * @return getY-Wert
     */
    public int getY() {
        return this.y;
    }

    /**
     * Berechnet die euklidische Distanz zum übergebenen Punkt.
     *
     * @param other - anderer Punkt
     * @return direkte Distanz zu other
     */
    public double calcDistance(Waypoint other) {
        return Math.sqrt(Math.pow(Math.abs(this.x - other.x), 2) +
                Math.pow(Math.abs(this.y - other.getY()), 2));
    }

    /**
     * Vergleicht den aktuellen mit dem übergebenen Punkt. Die beiden Wegpunkten
     * sind gleich, wenn beide Attribute jeweils gleich sind. `null` ist nie
     * gleich dem aktuellen Wegpunkt.
     *
     * @param other - der zu vergleichende Wegpunkt
     * @return true, wenn die Koordinaten gleich sind und nicht null übergeben
     * wurde
     */
    public boolean isEqual(Waypoint other) {
        if (other == null) return false;
        else
            // Grobe Untersuchung
            if (this == other) return true;
                // Tiefere Untersuchung
            else return (this.getX() == other.getX()) && (this.getY()) == other.getY();
    }

    /**
     * Konvertiert den Punkt in ein ein-dimensionales Array der Länge 2. Der
     * X-Wert steht dabei an Index 0, der Y-Wert an Index 1.
     *
     * @return Array mit den beiden Koordinatenwerten (erst X, dann Y)
     */
    public int[] toArray() {
        return new int[]{x, y};
    }

    /**
     * Liefert die Stringdarstellung des Punktes. Das Format des Strings sieht
     * wie folgt aus: "(x/y)" (ohne Anführungszeichen), z.B. "(1/2)"
     *
     * @return die Stringdarstellung des Punktes, X- und Y-Wert sind durch einen
     * Schrägstrich voneinander getrennt und der ganze Punkt ist von runden
     * Klammern eingefasst.
     */
    public String toString() {
        return "(" + this.getX() + "/" + this.getY() + ")";
    }

}
