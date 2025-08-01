package ueb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests für eine leere StringList der Aufgabe 6. PubTests müssen zu Beginn der Abgabe erfolgreich sein, sonst kann kein
 * Testat für diese Aufgabe erworben werden.
 *
 * @author #####
 */
public class PubStringListTest {

    @Test
    public void testSize_EmptyList() {
        StringList list = new StringList();
        assertEquals(0, list.size());
    }

    @Test
    public void testIsEmpty_EmptyList() {
        StringList list = new StringList();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testGetPayloadHead_emptyList() {
        StringList list = new StringList();
        assertNull(list.getPayloadAtHead());
    }

    @Test
    public void testGetAt_emptyList() {
        StringList list = new StringList();
        assertNull(list.getAt(0));
    }

    @Test
    public void testGetIndexOf_emptyList() {
        StringList list = new StringList();
        assertNull(list.getIndexOf("1"));
    }

    /*
     * Nutzt size() und getAt() -> ist eine der Methoden noch nicht korrekt implementiert,
     * kann das Ergebnis des Tests falsch sein
     */
    @Test
    public void testAppend_emptyList() {
        StringList list = new StringList();
        list.append("A");

        assertAll(
                () -> assertFalse(list.isEmpty()),
                () -> assertEquals(1, list.size()),
                () -> assertEquals("A", list.getAt(0))
        );
    }

    @Test
    public void testContains_emptyList() {
        StringList list = new StringList();
        assertFalse(list.contains("Z"));
    }

    @Test
    public void testPrepend_emptyList() {
        StringList list = new StringList();
        list.prepend("A");
        assertAll(
                () -> assertNotNull(list),
                () -> assertFalse(list.isEmpty()),
                () -> assertEquals("A", list.getAt(0))
        );
    }

    @Test
    public void testRemove_emptyList() {
        StringList list = new StringList();
        list.remove("Z");
        assertAll(
                () -> assertNotNull(list),
                () -> assertTrue(list.isEmpty()),
                () -> assertEquals(new StringList(), list)
        );
    }

    @Test
    public void testCopy_emptyList() {
        StringList list = new StringList();
        StringList copy = list.copy();

        assertAll(
                () -> assertTrue(copy.isEmpty()),
                () -> assertTrue(list.isEmpty()),
                () -> assertNotSame(list, copy)
        );
    }

    @Test
    public void testToString_EmptyList() {
        //if this fails you have broken the code you were given
        StringList list = new StringList();
        assertEquals("", list.toString());
    }

    @Test
    public void testEquals_Empty() {
        StringList list      = new StringList();
        StringList otherList = new StringList();
        assertAll(
                () -> assertEquals(list, otherList),
                () -> assertEquals(otherList, list)
        );
    }


}