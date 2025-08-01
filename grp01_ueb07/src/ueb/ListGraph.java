package ueb;

import ueb.nodelist.NodeList;

/**
 * Diese Klasse umhüllt alle Knoten und repräsentiert eine Adjenzliste anstelle einer Adjenzmatrix
 *
 * @author ##### & Christoph
 */



public class ListGraph extends Graph{
    private final NodeList nodes;

    /**
     * Erzeugt einen Leeren Graph mit keiner Node
     */
    public ListGraph(){
        nodes = new NodeList();
    }

    /**
     * Package-Private - Erzeugt einen Graph mit einer schon vorgegebener Liste an Nodes
     * @param nodes die Liste an vorgegebenen Nodes
     */
    ListGraph(NodeList nodes){
        this.nodes = nodes;
    }

    /**
     * Gibt die Vollkommene Node zurück -> Node + Nachbarn
     * @param node die gesucht wird
     * @return null, wenn Node nicht existiert im graphen, ansosnten die vollständinge Node referenz im Graphen
     */
    private ListGraphNode getWrappedNode(String node){
        if(!contains(node)) return null;
        ListGraphNode res = null;

        int size = nodes.size();
        for (int i = 0; i < size && res == null; i++){
            if (nodes.getAt(i).getNode().equals(node)) res = nodes.getAt(i);
        }

        return res;
    }




    /**
     * Prüft, ob der gegebene Knoten Teil des Graphen ist.
     *
     * @param node Knoten, der möglicherweise Teil des Graphen ist
     * @return true, wenn der Knoten Teil des Graphen ist
     */
    @Override
    public boolean contains(String node) {
        return nodes.contains(new ListGraphNode(node));
    }

    /**
     * Überprüft, ob der Graph die gegebene Kante enthält. Nimm an, dass der Graph konsistent ist
     * (keine zusätzlichen Überprüfungen erforderlich, ob es eine Verbindung von start zu end UND von end
     * zu start gibt). Wenn einer der Knoten nicht existiert, liefert die Methode false.
     *
     * @param start Starknoten der Kante
     * @param end   Endknoten der Kante
     * @return true, wenn es eine Kante zwischen den gegebenen Knoten gibt
     */
    @Override
    public boolean containsEdge(String start, String end) {
        ListGraphNode nodeStart = getWrappedNode(start);
        ListGraphNode nodeEnd = getWrappedNode(end);
        return !(nodeStart == null || nodeEnd == null)
                && nodeStart.getNeighbours().contains(end)
                && nodeEnd.getNeighbours().contains(start);
    }

    /**
     * Fügt den gegebenen Knoten dem Graphen hinzu. Ein Knoten kann einem Graphen nur einmalig zugefügt werden. Ist
     * der Knoten bereits enthalten, bleibt der Graph unverändert.<br>
     * Neue Knoten sollen der Liste am Ende zugefügt werden.
     *
     * @param node Knoten, der zugefügt werden soll
     */
    // TODO - DONE: Darf Knoten nur hinzufügen wenn dieser noch nicht im Graphen ist.
    @Override
    public void add(String node) {
        if (!contains(node)){
            nodes.append(new ListGraphNode(node));
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
        ListGraphNode nodeStart = getWrappedNode(start);
        ListGraphNode nodeEnd = getWrappedNode(end);

        if (nodeStart == null || nodeEnd == null) {
            throw new IllegalArgumentException();
        }
        nodeStart.addNeighbour(end);
        nodeEnd.addNeighbour(start);
    }

    /**
     * Entfernt den gegebenen Knoten aus dem Graphen. Alle Verbindungen (Kanten) des Knoten werden auch entfernt.
     * Ist der Knoten nicht Teil des Graphen, bleibt der Graph unverändert.
     *
     * @param node Knoten, der aus dem Graphen entfernt werden soll
     */
    @Override
    public void remove(String node) {
        if (nodes.contains(new ListGraphNode(node))){

            int idx = 0;
            while (idx < nodes.size()){
                ListGraphNode lgn = nodes.getAt(idx);
                lgn.removeNeighbour(node);
                if (lgn.getNode().equals(node)) {
                    nodes.remove(lgn);
                } else {
                    idx++;
                }
            }
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
        ListGraphNode nodeStart = getWrappedNode(start);
        ListGraphNode nodeEnd = getWrappedNode(end);
        if (nodeStart == null || nodeEnd == null) {
            return;
        }
        nodeStart.removeNeighbour(end);
        nodeEnd.removeNeighbour(start);
    }

    /**
     * Liefert alle Knoten, die im Graphen enthalten sind. Die Information, welche Kanten zwischen den Knoten
     * bestehen, kann hiermit nicht erworben werden. Es wird KEINE direkte Referenz auf die Knotenliste herausgegeben.
     *
     * @return alle Knoten, die Teil des Graphen sind
     */
    @Override
    public StringList getAllNodes() {
        StringList sl = new StringList();
        for (int i = 0; i < nodes.size(); i++){
            sl.append(nodes.getAt(i).getNode());
        }
        return sl.copy();
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
        ListGraphNode lgn = getWrappedNode(node);
        if (lgn == null) return null;
        return lgn.getNeighbours();
    }
}
