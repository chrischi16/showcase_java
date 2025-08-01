package ueb.nodelist;

/**
 * Diese Klasse repräsentiert eine Liste mit "NodeListElement"-Knoten als Elemente
 *
 * @author Jeremi & Christoph
 */

import ueb.ListGraphNode;

public class NodeList {

    private NodeListInterface listElements = new NodeListEmpty();

    /**
     * Erzeugt eine leere Liste
     */
    public NodeList() {
    }

    /**
     * nutzt diese, um eine Liste zu erzeugen, die evtl. mehrere Elemente enthält
     * (dabei müssen die notwendigen ListGraphNode erzeugt werden).
     * Dieser Konstruktor darf nur in Tests genutzt werden.
     *
     * @param values Nodes die hinzugefügt werden sollen
     */
    public NodeList(String... values) {
        for (String s : values) {
            listElements = listElements.append(new ListGraphNode(s));
        }
    }

    /**
     * Ermittelt, ob die Liste leer ist.
     *
     * @return true, wenn die Liste leer ist.
     */
    public boolean isEmpty() {
        return listElements == null || listElements.isEmpty();
    }

    /**
     * Ermittelt, die Größe der Liste. 0 für die leere Liste. In der Vorlesung besprochen.
     *
     * @return Größe der Liste
     */
    public int size() {
        return (isEmpty()) ? 0 : listElements.size();
    }

    /**
     * Überprüft, ob die gegebene Nutzlast, ein Teil der Liste ist.
     *
     * @param payload die gegebene Nutzlast
     * @return true, wenn die gegebene Nutzlast ein Teil der Liste ist
     */
    public boolean contains(ListGraphNode payload) {
        return listElements.contains(payload);
    }

    /**
     * Fügt die übergebene Nutzlast am Anfang der Liste ein.
     *
     * @param payload die Nutzlast, welche der Liste hinzugefügt werden soll.
     * @return eine neue Liste mit der gegebenen Nutzlast und this als Nachfolger
     */
    protected void prepend(ListGraphNode payload) {
        listElements = listElements.prepend(payload);
    }

    /**
     * Hängt die übergebene Nutzlast am Ende der Liste an.
     *
     * @param payload die gegebene Nutzlast
     * @return eine neue Liste mit der angehängten Nutzlast.
     */
    public void append(ListGraphNode payload) {
        listElements = listElements.append(payload);
    }

    /**
     * Entfernt die gegebene Nutzlast von der Liste. Die leere Liste bleibt unverändert. Wenn die Nutzlast nicht Teil
     * der Liste ist, wird die Liste ebenfalls nicht verändert.
     *
     * @param payload Nutzlast, welche entfernt werden soll
     * @return eine neue Liste mit der entfernten Nutzlast
     */
    public void remove(ListGraphNode payload) {
        listElements = listElements.remove(payload);
    }

    /**
     * Liefert die Nutzlast an einem bestimmten Index in der Liste. Wenn der Index ungültig ist, gibt die Methode `null`
     * zurück.
     *
     * @param index der 0 basierte Index
     * @return die Nutzlast an dem gegebenen Index
     */
    public ListGraphNode getAt(int index) {
        return listElements.getAt(index);
    }

    /**
     * Ermittelt, ob zwei Listen gleich sind. Listen sind gleich, wenn sie die selben Nutzlasten in der gleichen
     * Reihenfolge enthalten. Leere Listen sind immer gleich, unabhängig von ihrem Typ. Diese Methode muss in
     * allen implementierenden Klassen überschrieben werden, auch wenn dies nicht notwendig ist für erfolgreiches
     * Kompilieren (da Object bereits eine gültige Implementation enthält).
     *
     * @param obj ein anderes Object, möglicherweise eine Liste
     * @return true, wenn die Listen gleich sind
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof NodeList nl && listElements.equals(nl.listElements);
    }

    /**
     * Liefert die String Darstellung der Liste: alle Nutzlasten der Liste, getrennt durch ein Komma
     * (aber ohne ein Komma am Ende),
     * z. B. eine Integer-Liste mit drei Werten könnte so dargestellt werden "1, 2, 3". Diese Methode muss in
     * allen implementierenden Klassen überschrieben werden.
     *
     * @return alle Nutzlasten der Liste getrennt durch ein Komma, aber ohne ein Komma am Ende
     */
    @Override
    public String toString() {
        return listElements.toString();
    }
}
