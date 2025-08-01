package ueb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ueb.nodelist.NodeList;

/**
 * Public tests for the ListGraph class published with assignment 07. These tests rely on methods for the list. Tests
 * that start with "test0" have to run before the other tests.
 *
 * @author #####
 */
public class PubListGraphTest extends PubGraphTest {

    @Override
    Graph createStringGraph() {
        return new ListGraph();
    }

    /**
     * Tests for the method getAllNodes() in the class Graph
     */

    @Test
    public void test0GetAllNodes_Empty() {
        Graph graph = new ListGraph();
        assertEquals(0, graph.getAllNodes().size());
    }

    @Test
    public void test0GetAllNodes_OneNode() {
        Graph graph = new ListGraph(new NodeList("A"));
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
        Graph graph = new ListGraph();
        assertFalse(graph.contains("A"));
    }

    @Test
    public void test0Contains_OneNode() {
        Graph graph = new ListGraph(new NodeList("A"));
        assertTrue(graph.contains("A"));
    }

    @Test
    public void test0Contains_Not_OneNode() {
        Graph graph = new ListGraph(new NodeList("A"));
        assertFalse(graph.contains("B"));
    }

    /**
     * Tests for Graph.containsEdge(..)
     */

    @Test
    public void test0ContainsEdge_Empty() {
        Graph graph = new ListGraph();
        assertFalse(graph.containsEdge("A", "B"));
    }

    @Test
    public void test0ContainsEdge_EdgeExists() {
        NodeList nodes = new NodeList();
        nodes.append(new ListGraphNode("A", "B"));
        nodes.append(new ListGraphNode("B", "A"));

        Graph graph = new ListGraph(nodes);

        assertAll(
                () -> assertTrue(graph.containsEdge("A", "B")),
                () -> assertTrue(graph.containsEdge("B", "A"))
        );
    }

    @Test
    public void test0ContainsEdge_Not_NodeExists() {
        Graph graph = new ListGraph(new NodeList("A", "B"));

        assertAll(
                () -> assertFalse(graph.containsEdge("A", "B")),
                () -> assertFalse(graph.containsEdge("B", "A"))
        );
    }

    @Test
    public void test0ContainsEdge_OnlyOneNodeLoop() {
        NodeList nodes = new NodeList();
        nodes.append(new ListGraphNode("A", "A"));

        Graph graph = new ListGraph(nodes);

        assertTrue(graph.containsEdge("A", "A"));
    }

    @Test
    public void test0ContainsEdge_Not_OnlyOneNodeExists() {
        Graph graph = new ListGraph(new NodeList("A"));

        assertAll(
                () -> assertFalse(graph.containsEdge("A", "B")),
                () -> assertFalse(graph.containsEdge("B", "A"))
        );
    }

    @Test
    public void test0ContainsEdge_ThreeNodesMatchingAndNotMatchingEdge() {
        NodeList nodes = new NodeList();
        nodes.append(new ListGraphNode("A", "B"));
        nodes.append(new ListGraphNode("B", "A"));
        nodes.append(new ListGraphNode("C"));

        Graph graph = new ListGraph(nodes);

        assertAll(
                () -> assertTrue(graph.containsEdge("A", "B")),
                () -> assertFalse(graph.containsEdge("A", "C")),
                () -> assertFalse(graph.containsEdge("B", "C"))
        );
    }
}
