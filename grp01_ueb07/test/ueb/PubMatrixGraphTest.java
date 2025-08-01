package ueb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Public tests for the MatrixGraph class published with assignment 07. These tests rely on methods for the list. Tests
 * that start with "test0" have to run before the other tests. The exact same tests were already published for
 * assignment 6.
 *
 * @author #####
 */
public class PubMatrixGraphTest extends PubGraphTest {

    @Override
    Graph createStringGraph() {
        return new MatrixGraph();
    }

    /**
     * Tests for the method getAllNodes() in the class Graph
     */

    @Test
    public void test0GetAllNodes_Empty() {
        Graph graph = new MatrixGraph();
        assertEquals(0, graph.getAllNodes().size());
    }

    @Test
    public void test0GetAllNodes_OneNode() {
        Graph graph = new MatrixGraph(new StringList("A"), new boolean[1][1]);
        assertAll(
                () -> assertEquals(1, graph.getAllNodes().size()),
                () -> assertEquals("A", graph.getAllNodes().getAt(0))
        );
    }

    /**
     * Test for Graph.contains(..)
     */

    @Test
    public void test0Contains_Empty() {
        Graph graph = new MatrixGraph();
        assertFalse(graph.contains("A"));
    }

    @Test
    public void test0Contains_OneNode() {
        Graph graph = new MatrixGraph(new StringList("A"), new boolean[][]{});
        assertTrue(graph.contains("A"));
    }

    @Test
    public void test0Contains_Not_OneNode() {
        Graph graph = new MatrixGraph(new StringList("A"), new boolean[][]{});
        assertFalse(graph.contains("B"));
    }

    /**
     * Tests for Graph.containsEdge(..)
     */

    @Test
    public void test0ContainsEdge_Empty() {
        Graph graph = new MatrixGraph();
        assertFalse(graph.containsEdge("A", "B"));
    }

    @Test
    public void test0ContainsEdge_EdgeExists() {
        StringList nodeList = new StringList("A", "B");

        boolean[][] adjacencyMatrix = new boolean[][]{
                {false, true},
                {true, false}};

        Graph graph = new MatrixGraph(nodeList, adjacencyMatrix);

        assertAll(
                () -> assertTrue(graph.containsEdge("A", "B")),
                () -> assertTrue(graph.containsEdge("B", "A"))
        );
    }

    @Test
    public void test0ContainsEdge_Not_NodeExists() {
        StringList nodeList = new StringList("A", "B");
        boolean[][] adjacencyMatrix = new boolean[2][2]; //default values for boolean is false, initializing is not necessary
        Graph graph = new MatrixGraph(nodeList, adjacencyMatrix);

        assertAll(
                () -> assertFalse(graph.containsEdge("A", "B")),
                () -> assertFalse(graph.containsEdge("B", "A"))
        );
    }

    @Test
    public void test0ContainsEdge_OnlyOneNodeLoop() {
        StringList nodeList = new StringList("A");
        boolean[][] adjacencyMatrix = new boolean[][]{{true}};
        Graph graph = new MatrixGraph(nodeList, adjacencyMatrix);

        assertTrue(graph.containsEdge("A", "A"));
    }

    @Test
    public void test0ContainsEdge_Not_OnlyOneNodeExists() {
        StringList nodeList = new StringList("A");
        boolean[][] adjacencyMatrix = new boolean[1][1]; //default values for boolean is false, initializing is not necessary
        Graph graph = new MatrixGraph(nodeList, adjacencyMatrix);

        assertAll(
                () -> assertFalse(graph.containsEdge("A", "B")),
                () -> assertFalse(graph.containsEdge("B", "A"))
        );
    }

    @Test
    public void test0ContainsEdge_ThreeNodesMatchingAndNotMatchingEdge() {
        StringList nodeList = new StringList("A", "B", "C");
        boolean[][] adjacencyMatrix = new boolean[][]{
                {false, true, false},
                {true, false, false},
                {false, false, false}};
        Graph graph = new MatrixGraph(nodeList, adjacencyMatrix);

        assertAll(
                () -> assertTrue(graph.containsEdge("A", "B")),
                () -> assertFalse(graph.containsEdge("A", "C")),
                () -> assertFalse(graph.containsEdge("B", "C"))
        );
    }

}
