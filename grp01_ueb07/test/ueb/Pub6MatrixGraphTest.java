package ueb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

/**
 * Tests für einen MatrixGraph der Aufgabe 6. PubTests müssen zu Beginn der Abgabe erfolgreich sein, sonst kann kein
 * Testat für diese Aufgabe erworben werden.
 * <p>
 * Diese Tests bauen auf Methoden der StringList auf. Zuerst müssen alle Tests bestehen, deren Name mit "test0"
 * beginnt.
 *
 * @author #####
 */
public class Pub6MatrixGraphTest {

    /**
     * Tests für die Methode getAllNodes() 
     */

    @Test
    public void test0GetAllNodes_Empty() {
        MatrixGraph graph = new MatrixGraph();
        assertEquals(0, graph.getAllNodes().size());
    }

    @Test
    public void test0GetAllNodes_OneNode() {
        MatrixGraph graph = new MatrixGraph(new StringList("A"), new boolean[1][1]);
        assertAll(
                () -> assertEquals(1, graph.getAllNodes().size()),
                () -> assertEquals("A", graph.getAllNodes().getAt(0))
        );
    }

    /**
     * Tests für Graph.contains(..)
     */

    @Test
    public void testContains_Empty() {
        MatrixGraph graph = new MatrixGraph();
        assertFalse(graph.contains("A"));
    }

    @Test
    public void testContains_OneNode() {
        MatrixGraph graph = new MatrixGraph(new StringList("A"), new boolean[1][1]);
        assertTrue(graph.contains("A"));
    }

    @Test
    public void testContains_Not_OneNode() {
        MatrixGraph graph = new MatrixGraph(new StringList("A"), new boolean[1][1]);
        assertFalse(graph.contains("B"));
    }

    /**
     * Tests für Graph.containsEdge(..)
     */

    @Test
    public void testContainsEdge_Empty() {
        MatrixGraph graph = new MatrixGraph();
        assertFalse(graph.containsEdge("A", "B"));
    }

    @Test
    public void testContainsEdge_EdgeExists() {
        StringList list = new StringList("A", "B");

        boolean[][] adjacencyMatrix = new boolean[][]{
                {false, true},
                {true, false}};

        MatrixGraph graph = new MatrixGraph(list, adjacencyMatrix);

        assertAll(
                () -> assertTrue(graph.containsEdge("A", "B")),
                () -> assertTrue(graph.containsEdge("B", "A"))
        );
    }

    @Test
    public void testContainsEdge_Not_NodeExists() {
        StringList  list            = new StringList("A", "B");
        boolean[][] adjacencyMatrix = new boolean[2][2]; //default values für boolean is false, initializing is not necessary
        MatrixGraph graph           = new MatrixGraph(list, adjacencyMatrix);

        assertAll(
                () -> assertFalse(graph.containsEdge("A", "B")),
                () -> assertFalse(graph.containsEdge("B", "A"))
        );
    }

    @Test
    public void testContainsEdge_OnlyOneNodeLoop() {
        StringList  list            = new StringList("A");
        boolean[][] adjacencyMatrix = new boolean[][]{{true}};
        MatrixGraph graph           = new MatrixGraph(list, adjacencyMatrix);

        assertTrue(graph.containsEdge("A", "A"));
    }

    @Test
    public void testContainsEdge_Not_OnlyOneNodeExists() {
        StringList  list            = new StringList("A");
        boolean[][] adjacencyMatrix = new boolean[1][1]; //default values für boolean is false, initializing is not necessary
        MatrixGraph graph           = new MatrixGraph(list, adjacencyMatrix);

        assertAll(
                () -> assertFalse(graph.containsEdge("A", "B")),
                () -> assertFalse(graph.containsEdge("B", "A"))
        );
    }

    @Test
    public void testContainsEdge_ThreeNodesMatchingAndNotMatchingEdge() {
        StringList list = new StringList("A", "B", "C");
        boolean[][] adjacencyMatrix = new boolean[][]{
                {false, true, false},
                {true, false, false},
                {false, false, false}};
        MatrixGraph graph = new MatrixGraph(list, adjacencyMatrix);

        assertAll(
                () -> assertTrue(graph.containsEdge("A", "B")),
                () -> assertFalse(graph.containsEdge("A", "C")),
                () -> assertFalse(graph.containsEdge("B", "C"))
        );
    }

    /**
     * Tests für Graph.add(..). Nutzt contains().
     */

    @Test
    public void testAdd_OneNodeToEmptyGraph() {
        MatrixGraph graph = new MatrixGraph();
        graph.add("100");

        assertEquals(1, graph.getAllNodes().size());
        assertEquals(new StringList("100"), graph.getAllNodes());
        boolean[][] expAdjMatrix = new boolean[1][1];
        assertArrayEquals(expAdjMatrix, graph.getAdjacencyMatrix(),
                          "\nExpected: " + Arrays.deepToString(expAdjMatrix) +
                                   "\n but got: " + Arrays.deepToString(graph.getAdjacencyMatrix()));
    }

    /**
     * Tests für Graph.addEdge(..). Die folgenden Tests nutzen die Methode getAllNodes()
     */

    @Test
    public void testAddEdge_NewEdge() {
        MatrixGraph graph = new MatrixGraph(new StringList("A", "B"), new boolean[2][2]);
        graph.addEdge("A", "B");

        assertEquals(2, graph.getAllNodes().size());
        boolean[][] expAdjMatrix = new boolean[][]{
                {false, true},
                {true, false}};
        assertArrayEquals(expAdjMatrix, graph.getAdjacencyMatrix(),
                          "\nExpected: " + Arrays.deepToString(expAdjMatrix) +
                                   "\n but got: " + Arrays.deepToString(graph.getAdjacencyMatrix()));
    }

    @Test
    public void testAddEdge_NoNodes() {
        MatrixGraph graph = new MatrixGraph();
        assertThrows(IllegalArgumentException.class,
                     () -> graph.addEdge("A", "B"));
    }

    /**
     * Tests für die Methode remove(). Nutzt die Methode Graph.getAllNodes(..)
     **/

    @Test
    public void testRemove_Empty() {
        MatrixGraph graph = new MatrixGraph();
        graph.remove("B");
        assertEquals(0, graph.getAllNodes().size());
    }

    @Test
    public void testRemove_nodeWithoutEdge() {
        MatrixGraph graph = new MatrixGraph(new StringList("B"), new boolean[1][1]);
        graph.remove("B");
        assertAll(
                () -> assertEquals(0, graph.getAllNodes().size()),
                () -> assertFalse(graph.getAllNodes().contains("B"))
        );
    }

    /**
     * Tests für die Methode removeEdge(). Nutzt die Methode Graph.getAllNodes(..)
     **/

    @Test
    public void testRemoveEdge_Empty() {
        MatrixGraph graph = new MatrixGraph();
        graph.removeEdge("B", "C");
        assertEquals(0, graph.getAllNodes().size());
    }

    @Test
    public void testRemoveEdge_NoMatchingEdge() {
        MatrixGraph graph = new MatrixGraph(new StringList("B"), new boolean[1][1]);

        graph.removeEdge("B", "B");
        boolean[][] expAdjMatrix = new boolean[][]{{false}};

        assertArrayEquals(expAdjMatrix, graph.getAdjacencyMatrix(),
                          "\nExpected: " + Arrays.deepToString(expAdjMatrix) +
                                   "\n but got: " + Arrays.deepToString(graph.getAdjacencyMatrix()));
    }

    /**
     * Tests für die Methode getAllNeighbours()
     */

    @Test
    public void testGetAllNeighboursOf_Empty() {
        MatrixGraph graph = new MatrixGraph();

        assertNull(graph.getAllNeighboursOf("A"));
    }

    @Test
    public void testGetAllNeighboursOf_NoNeighbours() {
        MatrixGraph graph = new MatrixGraph(new StringList("A"), new boolean[1][1]);

        assertAll(
                () -> assertNotNull(graph.getAllNeighboursOf("A")),
                () -> assertEquals(new StringList(), graph.getAllNeighboursOf("A"))
        );
    }

    /**
     * Tests für die Methode isConnected()
     */

    @Test
    public void testIsConnected_Empty() {
        MatrixGraph graph = new MatrixGraph();

        assertTrue(graph.isConnected());
    }

    @Test
    public void testIsConnected_OneNode() {
        MatrixGraph graph = new MatrixGraph(new StringList("A"), new boolean[1][1]);

        assertTrue(graph.isConnected());
    }

    @Test
    public void testIsConnected_TwoNodes() {
        StringList list = new StringList("A", "B");

        boolean[][] adjacencyMatrix = new boolean[][]{
                {false, true},
                {true, false}};

        MatrixGraph graph = new MatrixGraph(list, adjacencyMatrix);

        assertTrue(graph.isConnected());
    }

    /**
     * Tests für Graph.equals(..)
     **/

    @Test
    public void testEquals_Null() {
        MatrixGraph graph = new MatrixGraph();
        assertNotEquals(graph, null);
    }

    @Test
    public void testEquals_Empty() {
        MatrixGraph graph  = new MatrixGraph();
        MatrixGraph graph2 = new MatrixGraph();

        assertEquals(graph, graph2);
    }

    @Test
    public void testEquals_SameNode() {
        MatrixGraph graph      = new MatrixGraph(new StringList("A"), new boolean[1][1]);
        MatrixGraph otherGraph = new MatrixGraph(new StringList("A"), new boolean[1][1]);

        assertEquals(graph, otherGraph, "The graphs should be equal because they contain only the same node");
    }

    @Test
    public void testEquals_Not_SameNodeDiffMatrix() {
        MatrixGraph graph      = new MatrixGraph(new StringList("A"), new boolean[][]{{false}});
        MatrixGraph otherGraph = new MatrixGraph(new StringList("A"), new boolean[][]{{true}});

        assertNotEquals(graph, otherGraph, "Graphs should not be equal, the adjacency matrix is different");
    }

    @Test
    public void testEquals_TwoSameNodes() {
        MatrixGraph graph      = new MatrixGraph(new StringList("A", "B"), new boolean[2][2]);
        MatrixGraph otherGraph = new MatrixGraph(new StringList("A", "B"), new boolean[2][2]);

        assertEquals(graph, otherGraph, "The graphs should be equal because they contain the same two nodes");
    }

    @Test
    public void testEquals_TwoSameNodesDiffOrder() {
        MatrixGraph graph      = new MatrixGraph(new StringList("B", "A"), new boolean[2][2]);
        MatrixGraph otherGraph = new MatrixGraph(new StringList("A", "B"), new boolean[2][2]);

        assertEquals(graph, otherGraph, "The graphs should be equal because they contain the same two nodes");
    }

    @Test
    public void testEquals_TwoSameNodesDiffOrderSameLoops() {
        boolean[][] adjMatrix = new boolean[][]{
                {true, false}, //loop für node B
                {false, false}};
        MatrixGraph graph = new MatrixGraph(new StringList("B", "A"), adjMatrix);
        boolean[][] otherAdjMatrix = new boolean[][]{
                {false, false},
                {false, true}}; //loop für node B
        MatrixGraph otherGraph = new MatrixGraph(new StringList("A", "B"), otherAdjMatrix);

        assertEquals(graph, otherGraph, "The graphs should be equal because they contain the same two nodes");
    }

    @Test
    public void testToString_Empty() {
        MatrixGraph graph = new MatrixGraph();
        assertEquals("", graph.toString());
    }

    @Test
    public void testToString_OneNode() {
        MatrixGraph graph = new MatrixGraph(new StringList("A"), new boolean[][]{{true}});
        String      str   = graph.toString();
        //removing all whitespace and any trailing line break, because we do not care about that
        str = str.replace(" ", "").strip();
        assertEquals("A->A", str);
    }

}
