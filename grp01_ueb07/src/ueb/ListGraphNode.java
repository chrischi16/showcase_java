package ueb;

/**
 * Diese Klasse repräsentiert einen Knoten mit seinen benachbarten Knoten.
 *
 * @author ##### & Christoph
 */

public class ListGraphNode {
    private final String node;
    private final StringList neighbours;

    /**
     * Ein Konstruktor der eine ListGraphNode ohne Nachbarn instanziert.
     * @param node die ListGraphNode ausmacht
     */
    public ListGraphNode(String node){
        this.node = node;
        this.neighbours = new StringList();
    }
    /**
     * Ein Package-Private Konstruktor der eine ListGraphNode mit Nachbarn instanziert.
     * @param node die ListGraphNode ausmacht
     * @param neighbours eine sammlung an nachbarNodes als String die übergeben wird
     */
    ListGraphNode(String node, String... neighbours){
        this.node = node;
        this.neighbours = new StringList(neighbours);
    }

    /**
     * Gibt die Node wieder ohne die Nachbarn
     * @return die Node
     */
    public String getNode(){
        return node;
    }

    /**
     * Prüft ob ein Object dieser ListGraphNode gleicht und nachbarn dabei nicht beachtet
     * @param obj das zu überprüfen ist
     * @return true, wenn es eine Instanz von ListGraphNode ist und die selbe Node hält
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ListGraphNode lgn){
            return lgn.getNode().equals(node);
        }
        return false;
    }

    /**
     * Gibt eine String representation einer ListGraphNode aus
     * @return die StringRepresentation
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(node);
        sb.append("->");
        sb.append(neighbours);
        return sb.toString();
    }

    /**
     * Gibt alle Nachbarn in einer StringList aus ohne die originale Node
     * @return liste von nachbarn
     */
    public StringList getNeighbours(){
        return neighbours.copy();
    }

    /**
     * Fügt einen Nachbarn hinzu wenn er noch nicht einer ist. Begrenzter Zugriff
     * @param newNeighbour der hinzugefügt wird
     */
    protected void addNeighbour(String newNeighbour){
        if (!neighbours.contains(newNeighbour)){
            neighbours.append(newNeighbour);
        }
    }

    /**
     * Entfernt einen nachbarn wenn er vorhanden ist. Begrenzter Zugriff
     * @param newNeighbour der entfernt wird
     */
    protected void removeNeighbour(String newNeighbour){
        if(neighbours.contains(newNeighbour)){
            neighbours.remove(newNeighbour);
        }
    }

}
