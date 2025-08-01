package ueb.nodelist;

import ueb.ListGraphNode;

/**
 * Diese Klasse repräsentiert das Elemente einer Liste mit deren Nutzlast und Referenz zum nächsten Element der Liste.
 *
 * @author Jeremi & Christoph
 */

public class NodeListElement implements NodeListInterface {

    private final ListGraphNode data;
    private NodeListInterface next;

    /**
     * Konstruktor, welches das Erstellen eines Knotens mit Nachbarknoten ermöglicht
     *
     * @param data
     * @param next
     */
    protected NodeListElement(ListGraphNode data, NodeListInterface next) {
        this.data = data;
        this.next = next;
    }

    /**
     * holt sich die angesprochene Nutzlast
     *
     * @return Nutzlast
     */
    @Override
    public ListGraphNode getPayload() {
        return this.data;
    }

    /**
     * holt sich das nächste Element
     *
     * @return den Nachbar des aktuellen/ angesprochenen Elements
     */
    @Override
    public NodeListInterface getNext() {
        return this.next;
    }

    /**
     * Prüft ob das Element leer ist
     *
     * @return ob die Liste leer ist
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Ermittelt die Größe der Elemente in der Liste
     *
     * @return die Größe der Liste
     */
    @Override
    public int size() {
        return 1 + next.size();
    }

    /**
     * Prüft nach vorhandensein des übergebenen Knotens
     *
     * @param payload die gegebene Nutzlast
     * @return ob das Element in der Liste ist
     */
    @Override
    public boolean contains(ListGraphNode payload) {
        if (this.data.equals(payload)) {
            return true;
        }
        else return next.contains(payload);
    }

    @Override
    public NodeListInterface prepend(ListGraphNode payload) {

        return new NodeListElement(payload, this);
    }

    /**
     * Fügt ein Element an das Ende der Liste hinzu
     *
     * @param payload die gegebene Nutzlast
     * @return die Liste mit dem neuen Element
     */
    @Override
    public NodeListInterface append(ListGraphNode payload) {
        this.next = this.next.append(payload);
        return this;
    }

    /**
     * Entfernt das übergebene Element in der Liste
     *
     * @param payload Nutzlast, welche entfernt werden soll
     * @return die Liste ohne dem gelöschten Element
     */
    @Override
    public NodeListInterface remove(ListGraphNode payload) {
        if (this.data.equals(payload))
            return this.next;
        else {
            this.next = this.next.remove(payload);
            return this;
        }
    }

    /**
     * Holt sich das Element bei dem übergebenen Index.
     *
     * @param index der 0 basierte Index
     * @return das Element an dem Index
     */
    @Override
    public ListGraphNode getAt(int index) {
        return (index == 0)
                ? this.data
                : next.getAt(index-1);
    }

    /**
     * Vergleicht das übergebene Objekt mit einem Listen Element auf Gleichheit
     *
     * @param obj ein anderes Object, möglicherweise eine Liste
     * @return ob die beiden Elemente gleich sind
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NodeListElement other))
            return false;

        return data.equals(other.data) && next.equals(other.next);
    }

    // TODO: - DONE  Die Methoden in NodeListElement müssen rekursiv implementiert werden, sofern Sie mehr als das aktuelle
    //        Element betrachten. Arbeitet hier also rekursiv, nicht iterativ. Nutzt keine anderen rekursiven Methoden.
    //        Denkt wegen dem Komma daran das nächste Element zu überprüfen, ob es leer ist (isEmpty)
    // TODO 2:  DONE Die Methode arbeitet weiterhin iterativ. Sie muss aber rekursiv arbeiten. Die Methode muss einen
    //         rekursiven Aufruf auf sich selbst enthalten.
    // TODO 2: - DONE Die Methode arbetiet jetzt nicht mehr korrekt. Statt eine Stringdarstellung der Liste mit allen Knoten
    //         zu liefern wird jetzt nur noch wiederholt der erste Knoten ausgegeben. Schreibt einen Test der eine List
    //         mit den Knoten A, B und C mit Hilfe des toStrings aus NodeListElement ausgibt. Das Ergebnis muss bei
    //         eurer Implementierung "A->, B->, C->" sein. Erstellt eine Testklasse ReqNodeListElementTest und fügt
    //         diesen unter test/src dem Projekt hinzu. Fügt den Test in dieser Testklasse ein. Dies ist Teil des
    //         TODO 2s.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(data);
         if(!next.isEmpty()){
             sb.append(", ");
             sb.append(next.toString());
         }

        return sb.toString();
    }
}
