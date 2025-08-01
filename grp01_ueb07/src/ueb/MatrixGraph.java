package ueb;

/**
 * Implementation eines ungerichteten, ungewichteten Graphen mit Strings als Knotenbezeichner.
 * Nutzt eine Adjazenzmatrix zum Speichern der Kanten/Verbindungen zwischen den Knoten des Graphen.
 *
 * @author ##### & Christoph
 */
public class MatrixGraph extends Graph {

    /**
     * Alle Knoten des Graphen.
     */
    private final StringList nodes;


    /**
     * Kanten (Verbindungen) zwischen den Knoten. Enthalten true an [i][j], wenn die Knoten mit den Indizees i und j
     * in der Knotenliste verbunden sind. Siehe auch https://de.wikipedia.org/wiki/Adjazenzmatrix
     */
    private boolean[][] adjacencyMatrix;

    /**
     * Default-Konstruktor für den Graphen. Erzeugt einen leeren Graphen.
     */
    public MatrixGraph() {
        this.nodes = new StringList();
        this.adjacencyMatrix = new boolean[0][0];
    }

    /**
     * Konstruktor, der ausschließlich zum Testen genutzt wird. Die Sichtbarkeit ist eingeschränkt, so dass er nur
     * innerhalb dieses Paketes sichtbar ist. Hereingegebene Parameter müssen daher nicht validiert werden.
     *
     * @param nodes     die Knoten des Graphen
     * @param adjMatrix die Verbindungen der Knoten in einer Matrix
     */
    MatrixGraph(StringList nodes, boolean[][] adjMatrix) {
        this.nodes = nodes;
        this.adjacencyMatrix = adjMatrix;
    }

    /**
     * Liefert die Adjazenzmatrix. AUSSCHLIESSLICH zum Testen zu nutzen, daher eingeschränkte Sichtbarkeit.
     *
     * @return Referenz der Adjazenzmatrix
     */
    boolean[][] getAdjacencyMatrix() {
        return this.adjacencyMatrix;
    }


    /**
     * Prüft, ob der gegebene Knoten im Graphen enthalten ist.
     *
     * @param node Knoten, der möglicherweise Teil des Graphen ist
     * @return true, wenn der Knoten Teil des Graphen ist
     */
    @Override
    public boolean contains(String node) {
        return this.nodes.contains(node);
    }

    /**
     * Prüft, ob der Graph eine Kante zwischen den gegebenen Knoten enthält. Es darf vorausgesetzt werden, dass
     * der Graph konsistent ist (es ist also keine Prüfung notwendig ob es eine Verbindung von start zu end gibt
     * UND von end zu start). Wenn einer der Knoten nicht existiert, liefert diese Methode false.
     *
     * @param start ein Knoten der Kante
     * @param end   anderer Knoten der Kante
     * @return true, wenn der Graph die Kante von einem zum anderen Knoten enthält
     */
    @Override
    public boolean containsEdge(String start, String end) {
        if ((!this.nodes.contains(start) || !this.nodes.contains(end))) {
            return false;
        }
        int startIdx = this.nodes.getIndexOf(start);
        int endIdx = this.nodes.getIndexOf(end);

        return this.adjacencyMatrix[startIdx][endIdx];
    }

    /**
     * Fügt den gegebenen Knoten dem Graphen hinzu. Ein Knoten kann einem Graphen nur einmalig zugefügt werden. Ist
     * der Knoten bereits enthalten, bleibt der Graph unverändert. Wird der Knoten zugefügt, so wird auch der
     * Adjazenzmatrix eine Reihe und eine Spalte zugefügt aber keine Kanten eingetragen (die Zellen der neuen
     * Reihe/Spalte werden mit false belegt).<br>
     * Neue Knoten sollen der Liste am Ende zugefügt werden.
     *
     * @param node Knoten, der zugefügt werden soll
     */
    @Override
    public void add(String node) {
        if (this.contains(node)) {
            System.err.println("Hinweis: Der eingegebene Knoten ist bereits im Graph enthalten. (Graph wurde nicht" +
                    "verändert.)");
        } else {

            // Dimension des modifiziertenGraph vergrößern
            this.nodes.append(node);
            boolean[][] modifizierterGraph = new boolean[this.adjacencyMatrix.length + 1][this.adjacencyMatrix.length
                    + 1];

            // Originalgraph auf modifizierten Graph packen
            for (int i = 0; i < this.adjacencyMatrix.length; i++) {
                System.arraycopy(this.adjacencyMatrix[i], 0, modifizierterGraph[i], 0,
                        this.adjacencyMatrix[i].length);
            }

            // Die neuen Felder leer setzen (False setzen)
            for (int i = 0; i < modifizierterGraph.length; i++) {
                modifizierterGraph[i][modifizierterGraph.length - 1] = false;
                modifizierterGraph[modifizierterGraph.length - 1][i] = false;
            }

            this.adjacencyMatrix = modifizierterGraph;
        }
    }


    /**
     * Fügt eine Kante vom Startknoten zum Endknoten zu. Da der Graph NICHT gerichtet ist, muss auch eine Kante vom
     * Endknoten zum Anfangsknoten zugefügt werden. Existiert die Kante bereits, passiert nichts. Ist einer der
     * Knoten nicht vorhanden, wird eine IllegalArgumentException ausgelöst.
     *
     * @param start Startknoten für die Kante
     * @param end   Endknoten für die Kante
     */
    @Override
    public void addEdge(String start, String end) {
        if (!this.contains(start) || !this.contains(end)) {
            throw new IllegalArgumentException("Fehler: Der Knoten ist nicht vorhanden!");
        } else {
            // 1. Koordinaten true setzen und dann 2. Koordinaten true setzen,
            // wenn umgekehrt, dann müssten diese Koordinaten das Gegenstück sein.
            this.adjacencyMatrix[this.nodes.getIndexOf(start)][this.nodes.getIndexOf(end)] = true;
            this.adjacencyMatrix[this.nodes.getIndexOf(end)][this.nodes.getIndexOf(start)] = true;
        }
    }

    /**
     * Entfernt den gegebenen Knoten aus dem Graphen. Alle Verbindungen (Kanten) des Knoten werden auch entfernt.
     * Die Adjazenzmatrix muss also hinterher eine Spalte weniger und eine Reihe weniger enthalten.<br>
     * Ist der Knoten nicht Teil des Graphen, bleibt der Graph unverändert.
     *
     * @param node Knoten, der aus dem Graphen entfernt werden soll
     */
    @Override
    public void remove(String node) {
        if (nodes.contains(node)) {
            int inputNodeIdx = nodes.getIndexOf(node);
            nodes.remove(node);

//            if (nodes.size() > 1) {
                boolean[][] newAdjacencyMatrix = new boolean[adjacencyMatrix.length - 1][adjacencyMatrix[0].length - 1];

                for (int i = 0; i < newAdjacencyMatrix.length; i++) {
                    for (int j = 0; j < newAdjacencyMatrix[i].length; j++) {
                        newAdjacencyMatrix[i][j] = adjacencyMatrix[(i < inputNodeIdx) ? i : i + 1]
                                                                  [(j < inputNodeIdx) ? j : j + 1];
                    }
                }
                adjacencyMatrix = newAdjacencyMatrix;
//            } else {
//                adjacencyMatrix = new boolean[1][1];
//            }
        }
    }

    /**
     * Entfernt die Kante zwischen zwei Knoten. Für ungerichtete Graphen darf nach diesem Methodenaufruf weder eine
     * Kante von start zu end noch eine Kante von end zu start existieren. Es darf davon ausgegangen werden, dass
     * der Graph konsistent ist.<br>
     * Existiert start oder end nicht, bleibt der Graph unverändert.
     *
     * @param start Startknoten für die Kante
     * @param end   Endknoten für die Kante
     */
    @Override
    public void removeEdge(String start, String end) {

        Integer startidx = nodes.getIndexOf(start);
        Integer endtidx = nodes.getIndexOf(end);

        if (startidx != null && endtidx != null) {
            adjacencyMatrix[startidx][endtidx] = false;
            adjacencyMatrix[endtidx][startidx] = false;
        }
    }


    /**
     * Liefert alle Knoten, die im Graphen enthalten sind. Die Information, welche Kanten zwischen den Knoten
     * bestehen, kann hiermit nicht erworben werden. Es wird KEINE direkte Referenz auf die Knotenliste herausgegeben.
     *
     * @return alle Knoten, die Teil des Graphen sind
     */
    @Override
    public StringList getAllNodes() {
        return nodes.copy();
    }


    /**
     * Liefert alle Nachbarn eines Knoten. Hat der Knoten keine Nachbarn, is die Liste leer. Ist der Knoten nicht
     * Teil des Graphen, liefert die Methode `null`. Zwei Knoten sind Nachbarn, wenn sie durch eine Kante verbunden
     * sind.
     *
     * @param node Knoten, für den die Nachbarn bestimmt werden sollen
     * @return die Nachbarn des Knoten oder `null`, wenn der Knoten nicht Teil des Graphen ist
     */
    @Override
    public StringList getAllNeighboursOf(String node) {
        if (!nodes.contains(node)) return null;

        StringList neighbours = new StringList();
        int idx = nodes.getIndexOf(node);

        for (int i = 0; i < adjacencyMatrix[idx].length; i++) {
            if (adjacencyMatrix[idx][i]) {
                neighbours.append(nodes.getAt(i));
            }
        }

        return neighbours;
    }

}
