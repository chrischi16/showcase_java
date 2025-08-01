package ueb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for class ListGraphNode. Published with the assignment.
 */
public class PubListGraphNodeTest {

    @Test
    public void testEmptyNode(){
        ListGraphNode node = new ListGraphNode("A");
        assertAll(
                () -> assertEquals("A", node.getNode()),
                () -> assertEquals(new StringList(), node.getNeighbours())
        );
    }

    @Test
    public void testVarArgsConstructor(){
        ListGraphNode node = new ListGraphNode("A", "B", "C");
        assertAll(
                () -> assertEquals("A", node.getNode()),
                () -> assertEquals(new StringList("B", "C"), node.getNeighbours())
        );
    }

    @Test
    public void testAddNeighbour_NoNeighboursYet(){
        ListGraphNode node = new ListGraphNode("A");
        node.addNeighbour("B");
        assertEquals(new StringList("B"), node.getNeighbours());
    }

    @Test
    public void testRemoveNeighbour(){
        ListGraphNode node = new ListGraphNode("A", "B");
        node.removeNeighbour("B");
        assertEquals(new StringList(), node.getNeighbours());
    }
}
