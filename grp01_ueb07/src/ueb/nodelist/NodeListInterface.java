package ueb.nodelist;

import ueb.ListGraphNode;

/**
 * Interface einer objektorientierten Liste. Soll implementiert werden von einem `NodeListEmpty` ohne Attribute
 * (leere Liste bzw. Listenende) und einem `NodeListElement` mit einer Nutzlast vom Typ `ListGraphNode`
 * und einer Referenz auf das nächstes Element.
 *
 * @author cei, klk
 */
public interface NodeListInterface {

    /**
     * Liefert die Nutzlast des aktuellen Elements. Wenn diese Methode auf der leeren Liste aufgerufen wird, wird ein
     * AssertionError ausgelöst, da die leere Liste keine Nutzlast enthält.
     *
     * @return die Nutzlast des aktuellen Elements
     */
    ListGraphNode getPayload();

    /**
     * Liefert den Nachfolger des aktuellen Elements. Wenn diese Methode auf der leeren Liste aufgerufen wird, wird ein
     * AssertionError ausgelöst, da die leere Liste keine Nachfolger besitzt.
     *
     * @return der Nachfolger des aktuellen Elements
     */
    NodeListInterface getNext();


    /**
     * Ermittelt, ob die Liste leer ist.
     *
     * @return true, wenn die Liste leer ist.
     */
    boolean isEmpty();

    /**
     * Ermittelt, die Größe der Liste. 0 für die leere Liste. In der Vorlesung besprochen.
     *
     * @return Größe der Liste
     */
    int size();

    /**
     * Überprüft, ob die gegebene Nutzlast, ein Teil der Liste ist.
     *
     * @param payload die gegebene Nutzlast
     * @return true, wenn die gegebene Nutzlast ein Teil der Liste ist
     */
    boolean contains(ListGraphNode payload);

    /**
     * Fügt die übergebene Nutzlast am Anfang der Liste ein.
     *
     * @param payload die Nutzlast, welche der Liste hinzugefügt werden soll.
     * @return eine neue Liste mit der gegebenen Nutzlast und this als Nachfolger
     */
    NodeListInterface prepend(ListGraphNode payload);

    /**
     * Hängt die übergebene Nutzlast am Ende der Liste an.
     *
     * @param payload die gegebene Nutzlast
     * @return eine neue Liste mit der angehängten Nutzlast.
     */
    NodeListInterface append(ListGraphNode payload);

    /**
     * Entfernt die gegebene Nutzlast von der Liste. Die leere Liste bleibt unverändert. Wenn die Nutzlast nicht Teil
     * der Liste ist, wird die Liste ebenfalls nicht verändert.
     *
     * @param payload Nutzlast, welche entfernt werden soll
     * @return eine neue Liste mit der entfernten Nutzlast
     */
    NodeListInterface remove(ListGraphNode payload);

    /**
     * Liefert die Nutzlast an einem bestimmten Index in der Liste. Wenn der Index ungültig ist, gibt die Methode `null`
     * zurück.
     *
     * @param index der 0 basierte Index
     * @return die Nutzlast an dem gegebenen Index
     */
    ListGraphNode getAt(int index);

    /**
     * Liefert die String Darstellung der Liste: alle Nutzlasten der Liste, getrennt durch ein Komma
     * (aber ohne ein Komma am Ende),
     * z. B. eine Integer-Liste mit drei Werten könnte so dargestellt werden "1, 2, 3". Diese Methode muss in
     * allen implementierenden Klassen überschrieben werden.
     *
     * @return alle Nutzlasten der Liste getrennt durch ein Komma, aber ohne ein Komma am Ende
     */
    @Override
    String toString();

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
    boolean equals(Object obj);
}
