package ueb;

import java.util.Arrays;

/**
 * Implementation eines ungerichteten, ungewichteten Graphen mit Strings als Knotenbezeichner.
 * Nutzt eine Adjazenzmatrix zum Speichern der Kanten/Verbindungen zwischen den Knoten des Graphen.
 *
 * @author ##### & Christoph
 */
public class MatrixGraph {

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
    public boolean containsEdge(String start, String end) {
        //TODO - DONE nur contains für die Überprüfung nutzen
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
    public StringList getAllNodes() {
        //TODO - DONE nicht die Referenz rausgeben
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

    /**
     * Überprüft ob 2 Knoten miteinander verknüpft sind
     *
     * @return boolean. Ist verknüpft oder nicht?
     */
    boolean isConnected() {
        //TODO - DONE Pseudocode umsetzen (mit queue und while-schleife)
        //TODO - DONE Testfall: Kreis aus 6 Knoten muss funktionieren
        //TODO - DONE nicht direkt auf nodes zugreifen

        if (getAllNodes().size() <= 1){
            return true;
        }

        StringList allNodes = getAllNodes();
        boolean[] visited = new boolean[allNodes.size()];

        String startNode = allNodes.getAt(0);
        StringList queue = new StringList(startNode);
        visited[0] = true;

        while (!queue.isEmpty()) {
            String node = queue.getAt(0);
            StringList neightbours = getAllNeighboursOf(node);
            for (int i = 0; i < neightbours.size(); i++){
                String neightbour = neightbours.getAt(i);
                int nIdx = allNodes.getIndexOf(neightbour);
                if (!visited[nIdx]){
                    visited[nIdx] = true;
                    queue.append(neightbour);
                }
            }
            queue.remove(node);
        }
        boolean visitedAll = true;
        for (int i = 0; i < visited.length && visitedAll; i++){
            if (!visited[i]) {
                visitedAll = false;
            }
        }
        return visitedAll;

    }

    /**
     * Gibt den Graphen als Stringausgabe aus
     *
     * @return der Graph in Stringformat
     */
    @Override
    public String toString() {
        //TODO - DONE nicht direkt auf nodes zugreifen
        StringBuilder sb = new StringBuilder();
        StringList nodes = getAllNodes();
        for (int i = 0; i < nodes.size(); i++) {
            String node = nodes.getAt(i);
            sb.append(node)
                    .append(" -> ")
                    .append(getAllNeighboursOf(node))
                    .append("\n");
        }
        return sb.toString();
    }

    /**
     * Überprüft ob ein Graph und das übergebene Object gleichwertig sind
     *
     * @param obj das zu übergebene object
     * @return true, wenn gleichwertig, andernfalls false
     */
    @Override
    public boolean equals(Object obj) {
        boolean sameEdgesAndNodes = false;
        if (obj instanceof MatrixGraph graph){
            sameEdgesAndNodes = true;

            StringList selfNodes = getAllNodes();
            StringList otherNodes = graph.getAllNodes();

            if (selfNodes.size() != otherNodes.size()){
                sameEdgesAndNodes = false;
            }

            //TODO - DONE nicht mit return aus Schleife
            for (int i = 0; i < selfNodes.size() && sameEdgesAndNodes; i++){
                if (!otherNodes.contains(selfNodes.getAt(i))){
                    sameEdgesAndNodes = false;
                }
            }

            //TODO - DONE Anzahl der Kanten zählen reicht nicht
            //TODO - DONE nicht mit der MatrixArbeiten, getAllNeighboursOf() nutzen
            StringList queue = selfNodes.copy();
            while (!queue.isEmpty() && sameEdgesAndNodes){
                String node = queue.getAt(0);
                StringList neighboursSelf = getAllNeighboursOf(node);
                StringList neighboursOther = graph.getAllNeighboursOf(node);
                if (neighboursSelf.size() != neighboursOther.size()){
                    sameEdgesAndNodes = false;
                }
                for (int i = 0; i < neighboursSelf.size() && sameEdgesAndNodes; i++){
                    if (!neighboursOther.contains(neighboursSelf.getAt(i))){
                        sameEdgesAndNodes = false;
                    }
                }
                queue.remove(node);
            }
        }
        return sameEdgesAndNodes;
    }

    /**
     * Ob der Graph eine Schleife enthält. Eine Kante, die einen Knoten mit sich selbst verbindet, ist eine Schleife.
     *
     * https://de.wikipedia.org/wiki/Schleife_(Graphentheorie)
     * @return
     */
    boolean containsLoop() {
        StringList visited = new StringList();
        for (int i = 0; i < nodes.size(); i++) {
            String node = nodes.getAt(i);
            StringList connectedNodes = getAllNeighboursOf(node);
            if (!visited.contains(node)) visited.append(node);
            for (int j = 0; j < connectedNodes.size(); j++) {
                String connectedNode = connectedNodes.getAt(i);
                if (visited.contains(connectedNode)) {
                    return true;
                } else {
                    visited.append(connectedNode);
                }
            }
        }
        return false;

        //throw new UnsupportedOperationException("not implemented yet");
    }

    /**
     * Ob der Graph ein einfacher Graph ist. Ein einfacher Graph hat keine Schleifen.
     *
     * https://de.wikipedia.org/wiki/Einfacher_Graph
     * @return
     */
    boolean isSimpleGraph() {
        return !containsLoop();
    }

    /**
     * Ob der Graph vollständig ist. Ein vollständiger Graph ist ein einfacher Graph, in dem alle Knoten direkt mit
     * allen anderen Knoten verbunden sind.
     *
     * https://de.wikipedia.org/wiki/Vollständiger_Graph
     * @return
     */
    boolean isComplete() {
        if (!isSimpleGraph()) return false;
        throw new UnsupportedOperationException("not implemented yet");
    }

}
