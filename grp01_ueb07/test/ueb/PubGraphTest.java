package ueb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Public tests for the Graph classes published with assignment 07. These tests rely on methods for the list. Tests that
 * start with "test1" have to run before the other tests (but before that the tests that start with "test0" have to run
 * - those are specific for the graph implementations).
 *
 * @author #####
 */
public abstract class PubGraphTest {

    abstract Graph createStringGraph();

    /**
     * Tests for Graph.add(). Use contains() and containsEdge().
     */

    @Test
    public void test1Add_OneNodeToEmptyGraph() {
        Graph graph = createStringGraph();
        graph.add("100");

        StringList allNodes = graph.getAllNodes();
        assertAll(
                () -> assertEquals(1, allNodes.size()),
                () -> assertTrue(allNodes.contains("100")),
                () -> assertFalse(graph.containsEdge("100", "100"))
        );
    }

    @Test
    public void test1Add_TwoNodes() {
        Graph graph = createStringGraph();
        graph.add("A");
        graph.add("B");

        StringList allNodes = graph.getAllNodes();
        assertAll(
                () -> assertEquals(2, allNodes.size()),
                () -> assertTrue(allNodes.contains("A")),
                () -> assertTrue(allNodes.contains("B")),
                () -> assertFalse(graph.containsEdge("A", "A")),
                () -> assertFalse(graph.containsEdge("A", "B")),
                () -> assertFalse(graph.containsEdge("B", "A")),
                () -> assertFalse(graph.containsEdge("B", "B"))
        );
    }

    /**
     * Tests for Graph.addEdge(). The following tests are using the methods containsEdge() & getAllNodes()
     */

    @Test
    public void test1AddEdge_NewEdge() {
        Graph graph = createStringGraph();
        graph.add("A");
        graph.add("B");

        graph.addEdge("A", "B");

        assertAll(
                () -> assertEquals(2, graph.getAllNodes().size()),
                () -> assertTrue(graph.containsEdge("A", "B")),
                () -> assertTrue(graph.containsEdge("B", "A")),
                      //graph should not contain any loop,
                () -> assertFalse(graph.containsEdge("A", "A")),
                () -> assertFalse(graph.containsEdge("B", "B"))
        );
    }

    @Test
    public void testAddEdge_Loop() {
        Graph graph = createStringGraph();
        graph.add("A");

        graph.addEdge("A", "A");

        assertAll(
                () -> assertEquals(1, graph.getAllNodes().size()),
                () -> assertTrue(graph.containsEdge("A", "A"))
        );
    }

    @Test
    public void testAddEdge_NoNodes() {
        Graph graph = createStringGraph();
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge("A", "B"));
    }

    /**
     * Tests for the method remove() of class Graph. Use method Graph.contains()
     **/

    @Test
    public void testRemove_Empty() {
        Graph graph = createStringGraph();
        graph.remove("B");
        assertEquals(0, graph.getAllNodes().size());
    }

    @Test
    public void testRemove_nodeWithoutEdge() {
        Graph graph = createStringGraph();
        graph.add("B");
        assertTrue(graph.contains("B"));

        graph.remove("B");
        assertAll(
                () -> assertEquals(0, graph.getAllNodes().size()),
                () -> assertFalse(graph.contains("B"))
        );
    }

    /**
     * Tests for the method removeEdge() of class Graph. Use Graph.containsEdge()
     **/

    @Test
    public void testRemoveEdge_Empty() {
        Graph graph = createStringGraph();
        graph.removeEdge("B", "C");
        assertEquals(0, graph.getAllNodes().size());

    }

    @Test
    public void testRemoveEdge_NoMatchingEdge() {
        Graph graph = createStringGraph();
        graph.add("B");
        assertFalse(graph.containsEdge("B", "B"));

        graph.removeEdge("B", "B");
        assertFalse(graph.containsEdge("B", "B"));
    }

    /**
     * Tests for the method getAllNeighbours() in the class Graph
     */

    @Test
    public void testGetAllNeighboursOf_Empty() {
        Graph graph = createStringGraph();

        assertNull(graph.getAllNeighboursOf("A"));
    }

    @Test
    public void testGetAllNeighboursOf_NoNeighbours() {
        Graph graph = createStringGraph();
        graph.add("A");

        assertAll(
                () -> assertNotNull(graph.getAllNeighboursOf("A")),
                () -> assertEquals(new StringList(), graph.getAllNeighboursOf("A"))
        );
    }

    /**
     * Tests for the method isConnected
     */

    @Test
    public void testIsConnected_Empty() {
        Graph graph = createStringGraph();

        assertTrue(graph.isConnected());
    }

    @Test
    public void testIsConnected_OneNode() {
        Graph graph = createStringGraph();
        graph.add("A");

        assertTrue(graph.isConnected());
    }

    /**
     * Tests for Graph.equal(..)
     **/

    @Test
    public void testEquals_Null() {
        Graph graph = createStringGraph();
        assertNotEquals(graph, null);
    }

    @Test
    public void testEquals_Empty() {
        Graph graph = createStringGraph();
        Graph graph2 = createStringGraph();

        assertEquals(graph, graph2);
    }

    @Test
    public void testEquals_SameNode() {
        Graph graph = createStringGraph();
        graph.add("A");
        Graph otherGraph = createStringGraph();
        otherGraph.add("A");

        assertEquals(graph, otherGraph,  "The graphs should be equal because they contain only the same node");
    }

    @Test
    public void testToString_Empty() {
        Graph graph = createStringGraph();
        assertEquals("", graph.toString());
    }

    @Test
    public void testToString_OneNodeConnectedToItself() {
        Graph graph = createStringGraph();
        graph.add("A");
        graph.addEdge("A", "A");
        String str = graph.toString();
        //removing all withspace and any trailing line break, because we do not care about that
        str = str.replace(" ", "").strip();
        assertEquals("A->A", str);
    }

}
