package ueb.nodelist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ueb.ListGraphNode;

/**
 * Tests for the filled listelement. Published with assignment.
 *
 * @author #####
 */
public class PubListElementTest {

    /**
     * Creates a list containing nodes. Uses method insertAtFront for this.
     *
     * @param values all String values that should be added to a list in the form of nodes
     * @return list of the given string values
     */
    private NodeListInterface createNodeList(String... values) {
        NodeListInterface list = new NodeListEmpty();
        for (int i = values.length - 1; i >= 0; i--) {
            list = list.prepend(new ListGraphNode(values[i]));
        }
        return list;
    }


    /**
     * Tests for the method remove() of the class ListElement (filled list)
     **/

    @Test
    public void testRemove_threeElementList_First() {
        NodeListInterface list = this.createNodeList("A", "B", "C");
        list = list.remove(new ListGraphNode("A"));

        final NodeListInterface finalList = list;
        assertAll(
                () -> assertFalse(finalList.isEmpty()),
                () -> assertEquals(2, finalList.size()),
                () -> assertEquals(new ListGraphNode("B"), finalList.getAt(0)),
                () -> assertEquals(new ListGraphNode("C"), finalList.getAt(1))
        );
    }

    /**
     * Tests for the method equals() of the class ListElement (filled list)
     **/

    @Test
    public void testEquals_SameElements() {
        NodeListInterface list = this.createNodeList("A", "B", "C");
        NodeListInterface otherList = this.createNodeList("A", "B", "C");
        assertAll(
                () -> assertEquals(list, otherList),
                () -> assertEquals(otherList, list)
        );
    }

    @Test
    public void testEquals_SameElements_OneLonger() {
        NodeListInterface list = this.createNodeList("A", "B");
        NodeListInterface otherList = this.createNodeList("A", "B", "C");
        assertAll(
                () -> assertNotEquals(list, otherList), //given list is longer
                () -> assertNotEquals(otherList, list)  //this list is longer
        );
    }

}