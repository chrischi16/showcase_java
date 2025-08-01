package ueb.nodelist;

import org.junit.jupiter.api.Test;
import org.w3c.dom.NodeList;

import static org.junit.jupiter.api.Assertions.*;

import ueb.ListGraphNode;

/**
 * Tests for the empty node list. Published with assignment.
 *
 * @author #####
 */
public class PubNodeListTest {

    @Test
    public void test0Size_EmptyList() {
        NodeList list = new NodeList();
        assertEquals(0, list.size());
    }

    @Test
    public void test0IsEmpty_EmptyList() {
        NodeList list = new NodeList();
        assertTrue(list.isEmpty());
    }

    @Test
    public void test0GetAt_emptyList() {
        NodeList list = new NodeList();
        assertNull(list.getAt(0));
    }


    /** Tests for the methods of the class NodeList **/

    /*
     * working with size() and getAt() ->
     * if one of the method has not been implemented properly yet, the results of these tests may be wrong
     */
    @Test
    public void testAppend_emptyList() {
        NodeList list = new NodeList();
        list.append(new ListGraphNode("A"));

        assertAll(
                () -> assertFalse(list.isEmpty()),
                () -> assertEquals(1, list.size()),
                () -> assertEquals(new ListGraphNode("A"), list.getAt(0))
        );
    }

    @Test
    public void testPrepend_emptyList() {
        NodeList list = new NodeList();
        list.prepend(new ListGraphNode("A"));
        assertAll(
                () -> assertEquals(1, list.size()),
                () -> assertEquals(new ListGraphNode("A"), list.getAt(0))
        );
    }

    @Test
    public void testContains_emptyList() {
        NodeList list = new NodeList();
        assertFalse(list.contains(new ListGraphNode("Z")));
    }

    @Test
    public void testRemove_emptyList() {
        NodeList list = new NodeList();
        list.remove(new ListGraphNode("Z"));
        assertAll(
                () -> assertNotNull(list),
                () -> assertTrue(list.isEmpty()),
                () -> assertEquals(new NodeList(), list)
        );
    }

    @Test
    public void testToString_EmptyList() {
        NodeList list = new NodeList();
        assertEquals("", list.toString());
    }

    @Test
    public void testEquals_Empty() {
        NodeList list = new NodeList();
        NodeList otherList = new NodeList();
        assertAll(
                () -> assertEquals(list, otherList),
                () -> assertEquals(otherList, list)
        );
    }


}