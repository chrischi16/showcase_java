package ueb.nodelist;

import ueb.ListGraphNode;

/**
 * Diese Klasse repräsentiert eine leere Liste oder ihr Ende
 *
 * @author Jeremi & Christoph
 */

public class NodeListEmpty implements NodeListInterface {
    @Override
    public ListGraphNode getPayload() {
        throw new AssertionError();
    }

    /**
     * Gibt ein Error aus, da leere Liste/ Ende der Liste
     * @return Error
     */
    @Override
    public NodeListInterface getNext() {
        throw new AssertionError();
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    /**
     * Prüft die Größe der Liste und gibt sie aus. Ist immer 0, da leere Liste/ Ende der Liste
     * @return 0 (größe der leeren Liste)
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * Checkt die leere Liste nach der gegebenen Nutzlast
     *
     * @param payload die gegebene Nutzlast
     * @return immer false, da leere Liste/ Ende der Liste
     */
    @Override
    public boolean contains(ListGraphNode payload) {
        return false;
    }

    @Override
    public NodeListInterface prepend(ListGraphNode payload) {
        return new NodeListElement(payload, this);
    }

    /**
     * Hängt das gegebene Element an das Ende der leeren Liste
     *
     * @param payload die gegebene Nutzlast
     * @return die Liste mit dem hinzugefügten Element
     */
    @Override
    public NodeListInterface append(ListGraphNode payload) {
        return new NodeListElement(payload,this);
    }

    /**
     * Entfernt das gegebene Element aus der leeren Liste
     *
     * @param payload Nutzlast, welche entfernt werden soll
     * @return die aktualisierte Liste
     */
    @Override
    public NodeListInterface remove(ListGraphNode payload) {
        return this;
    }

    /**
     * Holt sich das Element aus der leeren Liste
     *
     * @param index der 0 basierte Index
     * @return immer nichts, da leere Liste/ Ende der Liste
     */
    @Override
    public ListGraphNode getAt(int index) {
        return null;
    }

    /**
     * überprüft die leere Liste mit einem übergebenen Objekt
     *
     * @param o ein anderes Object, möglicherweise eine Liste
     * @return
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof NodeListEmpty;
    }

    /**
     * Gibt die leere Liste als Leer aus
     *
     * @return
     */
    @Override
    public String toString() {
        return "";
    }
}
