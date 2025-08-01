package logic;

/**
 * Stellt die Position auf einem Spielfeld mit x und y dar.
 *
 * @param x die x-Koordinate
 * @param y die y-Koordinate
 *
 * @author #####, co
 */
public record Position(int x, int y) {

    /**
     * Liefert die Position, die auf dem Spielfeld dieser folgt.
     * Im Allgemeinen ist dies eine Position weiter rechts, vom rechten
     * Spielfeldrand aus ist die nächste Position ganz links in der nächsten Reihe.<br>
     * Gibt es keine nächste Position, weil diese außerhalb des Spielfeldes wäre,  
     * wird eine IllegalArgumentException ausgelöst.
     *
     * @param width Breite des Spielbretts
     * @param height Höhe des Spielbretts
     * @return nächste Position
     */
    public Position nextPos(int width, int height) {
        if (x >= width - 1 && y >= height - 1)
            throw new IllegalArgumentException("Next Position of " + this + " would be out of bounds.");
        int newX = x + 1;
        int newY = y;
        if (newX == width) {
            newX = 0;
            newY++;
        }
        return new Position(newX, newY);
    }
}
