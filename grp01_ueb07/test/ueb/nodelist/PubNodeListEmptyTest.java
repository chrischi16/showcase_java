package ueb.nodelist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ueb.ListGraphNode;

/**
 * Tests for the empty list. Published with assignment.
 *
 * @author #####
 */
public class PubNodeListEmptyTest {

    /** Tests for the getters of the class NodeListEmpty **/

    @Test
    public void test0GetPayload_emptyList() {
        NodeListInterface list = new NodeListEmpty();
        assertThrows(AssertionError.class, () -> list.getPayload());
    }

    @Test
    public void test0GetNext_emptyList() {
        NodeListInterface list = new NodeListEmpty();
        assertThrows(AssertionError.class, () -> list.getNext());
    }

    /** Tests for the base methods size(), isEmpty(), getAt() of the class NodeListEmpty **/

    @Test
    public void test0Size_EmptyList() {
        NodeListInterface list = new NodeListEmpty();
        assertEquals(0, list.size());
    }

    @Test
    public void test0IsEmpty_EmptyList() {
        NodeListInterface list = new NodeListEmpty();
        assertTrue(list.isEmpty());
    }

    @Test
    public void test0GetAt_emptyList() {
        NodeListInterface list = new NodeListEmpty();
        assertNull(list.getAt(0));
    }

    /** Tests for the methods of the class NodeListEmpty **/

    /*
     * working with size() and getAt() ->
     * if one of the method has not been implemented properly yet, the results of these tests may be wrong
     */

    @Test
    public void testAppend_emptyList() {
        NodeListInterface list = new NodeListEmpty();
        list = list.append(new ListGraphNode("A"));

        final NodeListInterface finalList = list;
        assertAll(
                () -> assertFalse(finalList.isEmpty()),
                () -> assertEquals(1, finalList.size()),
                () -> assertEquals(new ListGraphNode("A"), finalList.getAt(0))
        );
    }

    @Test
    public void testInsertAtFront_emptyList() {
        NodeListInterface list = new NodeListEmpty();
        list = list.prepend(new ListGraphNode("A"));

        final NodeListInterface finalList = list;
        assertAll(
                () -> assertEquals(1, finalList.size()),
                () -> assertEquals(new ListGraphNode("A"), finalList.getAt(0))
        );
    }

    @Test
    public void testContains_emptyList() {
        NodeListInterface list = new NodeListEmpty();
        assertFalse(list.contains(new ListGraphNode("Z")));
    }

    @Test
    public void testRemove_emptyList() {
        NodeListInterface list = new NodeListEmpty();
        list = list.remove(new ListGraphNode("Z"));

        final NodeListInterface finalList = list;
        assertAll(
                () -> assertNotNull(finalList),
                () -> assertTrue(finalList.isEmpty()),
                () -> assertEquals(new NodeListEmpty(), finalList)
        );
    }

    @Test
    public void testToString_EmptyList() {
        NodeListInterface list = new NodeListEmpty();
        assertEquals("", list.toString());
    }

    @Test
    public void testEquals_Empty() {
        NodeListInterface list = new NodeListEmpty();
        NodeListInterface otherList = new NodeListEmpty();
        assertAll(
                () -> assertEquals(list, otherList),
                () -> assertEquals(otherList, list)
        );
    }

}