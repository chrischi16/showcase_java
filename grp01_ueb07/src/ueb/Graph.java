package ueb;

/**
 * Klasse für einen ungerichteten, ungewichteten Graphen mit Knoten vom Typ String.
 *
 * @author ##### & Christoph
 */
public abstract class Graph {

    /**
     * Prüft, ob der gegebene Knoten Teil des Graphen ist.
     *
     * @param node Knoten, der möglicherweise Teil des Graphen ist
     * @return true, wenn der Knoten Teil des Graphen ist
     */
    public abstract boolean contains(String node);

    /**
     * Überprüft, ob der Graph die gegebene Kante enthält. Nimm an, dass der Graph konsistent ist
     * (keine zusätzlichen Überprüfungen erforderlich, ob es eine Verbindung von start zu end UND von end
     * zu start gibt). Wenn einer der Knoten nicht existiert, liefert die Methode false.
     *
     * @param start Starknoten der Kante
     * @param end   Endknoten der Kante
     * @return true, wenn es eine Kante zwischen den gegebenen Knoten gibt
     */
    public abstract boolean containsEdge(String start, String end);

    /**
     * Fügt den gegebenen Knoten dem Graphen hinzu. Ein Knoten kann einem Graphen nur einmalig zugefügt werden. Ist
     * der Knoten bereits enthalten, bleibt der Graph unverändert.<br>
     * Neue Knoten sollen der Liste am Ende zugefügt werden.
     *
     * @param node Knoten, der zugefügt werden soll
     */
    public abstract void add(String node);

    /**
     * Fügt eine Kante vom Startknoten zum Endknoten zu. Da der Graph NICHT gerichtet ist, muss auch eine Kante vom
     * Endknoten zum Anfangsknoten zugefügt werden. Existiert die Kante bereits, passiert nichts. Ist einer der
     * Knoten nicht vorhanden, wird eine IllegalArgumentException ausgelöst.
     *
     * @param start Startknoten für die Kante
     * @param end   Endknoten für die Kante
     */
    public abstract void addEdge(String start, String end);

    /**
     * Entfernt den gegebenen Knoten aus dem Graphen. Alle Verbindungen (Kanten) des Knoten werden auch entfernt.
     * Ist der Knoten nicht Teil des Graphen, bleibt der Graph unverändert.
     *
     * @param node Knoten, der aus dem Graphen entfernt werden soll
     */
    public abstract void remove(String node);

    /**
     * Entfernt die Kante zwischen zwei Knoten. Für ungerichtete Graphen darf nach diesem Methodenaufruf weder eine
     * Kante von start zu end noch eine Kante von end zu start existieren. Es darf davon ausgegangen werden, dass
     * der Graph konsistent ist.<br>
     * Existiert start oder end nicht, bleibt der Graph unverändert.
     *
     * @param start Startknoten für die Kante
     * @param end   Endknoten für die Kante
     */
    public abstract void removeEdge(String start, String end);

    /**
     * Liefert alle Knoten, die im Graphen enthalten sind. Die Information, welche Kanten zwischen den Knoten
     * bestehen, kann hiermit nicht erworben werden. Es wird KEINE direkte Referenz auf die Knotenliste herausgegeben.
     *
     * @return alle Knoten, die Teil des Graphen sind
     */
    public abstract StringList getAllNodes();

    /**
     * Liefert alle Nachbarn eines Knoten. Hat der Knoten keine Nachbarn, is die Liste leer. Ist der Knoten nicht
     * Teil des Graphen, liefert die Methode `null`. Zwei Knoten sind Nachbarn, wenn sie durch eine Kante verbunden
     * sind.
     *
     * @param node Knoten, für den die Nachbarn bestimmt werden sollen
     * @return die Nachbarn des Knoten oder `null`, wenn der Knoten nicht Teil des Graphen ist
     */
    public abstract StringList getAllNeighboursOf(String node);

    /**
     * Überprüft ob 2 Knoten miteinander verknüpft sind
     *
     * @return boolean. Ist verknüpft oder nicht?
     */
    boolean isConnected() {

        if (getAllNodes().size() <= 1) {
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
            for (int i = 0; i < neightbours.size(); i++) {
                String neightbour = neightbours.getAt(i);
                int nIdx = allNodes.getIndexOf(neightbour);
                if (!visited[nIdx]) {
                    visited[nIdx] = true;
                    queue.append(neightbour);
                }
            }
            queue.remove(node);
        }
        boolean visitedAll = true;
        for (int i = 0; i < visited.length && visitedAll; i++) {
            if (!visited[i]) {
                visitedAll = false;
            }
        }
        return visitedAll;

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
        if (obj instanceof Graph graph){
            sameEdgesAndNodes = true;

            StringList selfNodes = getAllNodes();
            StringList otherNodes = graph.getAllNodes();

            if (selfNodes.size() != otherNodes.size()){
                sameEdgesAndNodes = false;
            }

            for (int i = 0; i < selfNodes.size() && sameEdgesAndNodes; i++){
                if (!otherNodes.contains(selfNodes.getAt(i))){
                    sameEdgesAndNodes = false;
                }
            }

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
     * Gibt den Graphen als Stringausgabe aus
     *
     * @return der Graph in Stringformat
     */
    @Override
    public String toString() {
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
     * Ob der Graph eine Schleife enthält. Eine Kante, die einen Knoten mit sich selbst verbindet, ist eine Schleife.
     *
     * https://de.wikipedia.org/wiki/Schleife_(Graphentheorie)
     * @return
     */
    boolean containsLoop() {
        StringList visited = new StringList();
        StringList nodes = getAllNodes(); // #!
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