package ueb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test of class TourElement. Published with assignment.
 * @author #####
 */
public class PubTourElementTest3 {
    
//<editor-fold defaultstate="collapsed" desc="Helpers to compare doubles and create list">
    /**
     * Precision of comparison of doubles
     */
    private static final double EPSILON = 0.001;

    /**
     * Creates a ElementList with the given waypoints.
     * @param waypoints array of waypoints to use, the coordinates of the waypoints are also in an array
     * @return List of elements with the given waypoints
     * @pre at least one waypoint has to be in array
     */
    private TourElement createElementList(int[][] waypoints){
        assert waypoints.length > 0;
        int lastIndex = waypoints.length-1;
        Waypoint wp = new Waypoint(waypoints[lastIndex][0], waypoints[lastIndex][1]);
        TourElement elem = new TourElement(wp);
        for (int i = lastIndex-1; i >= 0 ; i--) {
            wp = new Waypoint(waypoints[i][0], waypoints[i][1]);
            elem = elem.addStart(wp);
        }
        return elem;
    }
//</editor-fold>

    /**
     * Test of constructor of class TourElement. If these fail, nothing else matters...
     */
    @Test
    public void test0Constructor() {
        TourElement elem = new TourElement(new Waypoint(1, 2));
        assertArrayEquals(new int[]{1, 2}, elem.getWaypoint().toArray());
    }

    @Test
    public void test0Constructor_Null() {
        assertThrows(IllegalArgumentException.class,
            () -> new TourElement(null));
    }

    /**
     * Test of getWaypointAt method, of class TourElement.
     * This test has to pass first
     * because this method is used of all tests that check the content of
     * the list after changing elements.
     */
    @Test
    public void test0GetAt_First() {
        TourElement elem = createElementList(new int[][] {{0, 0}, {1, 1}, {2, 2}});
        Waypoint expected = new Waypoint(0, 0);
        assertArrayEquals(expected.toArray(), elem.getAt(0).toArray());
    }

    @Test
    public void test0GetAt_Snd() {
        TourElement elem = createElementList(new int[][] {{0, 0}, {1, 1}, {2, 2}});
        Waypoint expected = new Waypoint(1, 1);
        assertArrayEquals(expected.toArray(), elem.getAt(1).toArray());
    }

    @Test
    public void test0GetAt_Last() {
        TourElement elem = createElementList(new int[][] {{0, 0}, {1, 1}, {2, 2}});
        Waypoint expected = new Waypoint(2, 2);
        assertArrayEquals(expected.toArray(), elem.getAt(2).toArray());
    }

    @Test
    public void test0GetAt_IndexNegative() {
        TourElement elem = createElementList(new int[][] {{0, 0}, {1, 1}, {2, 2}});
        assertNull(elem.getAt(-1));
    }

    @Test
    public void test0GetAt_IndexTooBig() {
        TourElement elem = createElementList(new int[][] {{0, 0}, {1, 1}, {2, 2}});
        assertNull(elem.getAt(3));
    }

    @Test
    public void test0GetAt_NotChangingList() {
        TourElement elem = createElementList(new int[][] {{0, 0}, {1, 1}, {2, 2}});
        Waypoint unused = elem.getAt(2);
        assertArrayEquals(new int[] {0, 0}, elem.getWaypoint().toArray());
        assertArrayEquals(new int[] {1, 1}, elem.getNext().getWaypoint().toArray());
        assertArrayEquals(new int[] {2, 2}, elem.getNext().getNext().getWaypoint().toArray());
        assertNull(elem.getNext().getNext().getNext());
    }

    /**
     * Test of addStart method, of class TourElement.
     * This test has to pass first 
     * because this method is used in <code>createElementList()</code> 
     * which is used in most other tests.
     */
    @Test
    public void test0AddStart() {
        TourElement elem = new TourElement(new Waypoint(2, 2));
        
        elem = elem.addStart(new Waypoint(1, 1));
        assertArrayEquals(new int[] {1, 1}, elem.getWaypoint().toArray());
        assertArrayEquals(new int[] {2, 2}, elem.getNext().getWaypoint().toArray());
        assertNull(elem.getNext().getNext());
    }

    @Test
    public void test0AddStart_Null() {
        TourElement elem = new TourElement(new Waypoint(2, 2));
        assertThrows(IllegalArgumentException.class,
                     () -> elem.addStart(null));
    }



    /**
     * Test to ensure calling of getNoOfWaypoints method does not change the list.
     */
    @Test
    public void testGetNoOfWaypoints_NotChangingList() {
        TourElement elem = createElementList(new int[][] {{0, 0}, {1, 1}, {2, 2}});
        int unused = elem.getNoOfWaypoints();
        assertArrayEquals(new int[] {0, 0}, elem.getWaypoint().toArray());
        assertArrayEquals(new int[] {1, 1}, elem.getNext().getWaypoint().toArray());
        assertArrayEquals(new int[] {2, 2}, elem.getNext().getNext().getWaypoint().toArray());
        assertNull(elem.getNext().getNext().getNext());
    }
    
    /**
     * Test of contains method, of class TourElement.
     */
    @Test
    public void testContains_Fst() {
        TourElement elem = createElementList(new int[][] {{0, 0}, {1, 1}, {2, 2}});
        assertTrue(elem.contains(new Waypoint(0, 0)));
    }
    
    @Test
    public void testContains_Snd() {
        TourElement elem = createElementList(new int[][] {{0, 0}, {1, 1}, {2, 2}});
        assertTrue(elem.contains(new Waypoint(1, 1)));
    }
    
    @Test
    public void testContains_Last() {
        TourElement elem = createElementList(new int[][] {{0, 0}, {1, 1}, {2, 2}});
        assertTrue(elem.contains(new Waypoint(2, 2)));
    }
    
    @Test
    public void testContains_Not() {
        TourElement elem = createElementList(new int[][] {{0, 0}, {1, 1}, {2, 2}});
        assertFalse(elem.contains(new Waypoint(3, 3)));
    }

    @Test
    public void testContains_null() {
        TourElement elem = createElementList(new int[][] {{0, 0}, {1, 1}, {2, 2}});
        assertFalse(elem.contains(null));
    }

    /**
     * Test of calcDistance method, of class TourElement.
     */

    @Test
    public void testCalcDistance_OneElement() {
        TourElement elem = createElementList(new int[][] {{5, 5}});
        assertEquals(0, elem.calcDistance(), EPSILON);
    }

    @Test
    public void testCalcDistance_TwoElementsDiagonal() {
        TourElement elem = createElementList(new int[][] {{0, 0}, {3, 4}});
        assertEquals(5, elem.calcDistance(), EPSILON);
    }

    @Test
    public void testCalcDistance_ThreeElementsOrthogonal() {
        TourElement elem = createElementList(new int[][] {{0, 0}, {0, 3}, {4, 3}});
        assertEquals(7, elem.calcDistance(), EPSILON);
    }

    /**
     * Test of append method, of class TourElement.
     */
    @Test
    public void testAppend() {
        TourElement elem = createElementList(new int[][] {{2, 2}});
        elem = elem.append(new Waypoint(3, 3));
        assertArrayEquals(new int[] {2, 2}, elem.getWaypoint().toArray());
        assertArrayEquals(new int[] {3, 3}, elem.getNext().getWaypoint().toArray());
        assertNull(elem.getNext().getNext());
    }
    
    @Test
    public void testAppend_AfterTwo() {
        TourElement elem = createElementList(new int[][] {{1, 1}, {2, 2}});
        elem = elem.append(new Waypoint(3, 3));
        assertArrayEquals(new int[] {1, 1}, elem.getWaypoint().toArray());
        assertArrayEquals(new int[] {2, 2}, elem.getNext().getWaypoint().toArray());
        assertArrayEquals(new int[] {3, 3}, elem.getNext().getNext().getWaypoint().toArray());
        assertNull(elem.getNext().getNext().getNext());
    }

    @Test
    public void testAppend_Null() {
        TourElement elem = createElementList(new int[][] {{2, 2}});
        assertThrows(IllegalArgumentException.class,
                     () -> elem.append(null));
    }

    /**
     * Test of insertAt method, of class TourElement.
     */
    @Test
    public void testInsertAt_BeforeFirst() {
        TourElement elem = createElementList(new int[][] {{1, 1}, {3, 3}});
        Waypoint wp = new Waypoint(0, 0);
        elem = elem.insertAt(0, wp);
        assertArrayEquals(new int[] {0, 0}, elem.getWaypoint().toArray());
        assertArrayEquals(new int[] {1, 1}, elem.getNext().getWaypoint().toArray());
        assertArrayEquals(new int[] {3, 3}, elem.getNext().getNext().getWaypoint().toArray());
        assertNull(elem.getNext().getNext().getNext());
    }
    
    @Test
    public void testInsertAt_Second() {
        TourElement elem = createElementList(new int[][] {{1, 1}, {3, 3}});
        Waypoint wp = new Waypoint(2, 2);
        elem = elem.insertAt(1, wp);
        assertArrayEquals(new int[] {1, 1}, elem.getWaypoint().toArray());
        assertArrayEquals(new int[] {2, 2}, elem.getNext().getWaypoint().toArray());
        assertArrayEquals(new int[] {3, 3}, elem.getNext().getNext().getWaypoint().toArray());
        assertNull(elem.getNext().getNext().getNext());
    }
    
    @Test
    public void testInsertAt_Last() {
        TourElement elem = createElementList(new int[][] {{1, 1}, {3, 3}});
        Waypoint wp = new Waypoint(4, 4);
        elem = elem.insertAt(2, wp);
        assertArrayEquals(new int[] {1, 1}, elem.getWaypoint().toArray());
        assertArrayEquals(new int[] {3, 3}, elem.getNext().getWaypoint().toArray());
        assertArrayEquals(new int[] {4, 4}, elem.getNext().getNext().getWaypoint().toArray());
        assertNull(elem.getNext().getNext().getNext());
    }

    @Test
    public void testInsertAt_null() {
        TourElement elem = createElementList(new int[][] {{1, 1}, {3, 3}});
        assertThrows(IllegalArgumentException.class,
                     ()-> elem.insertAt(2, null));
    }

    /**
     * Test of toString method, of class TourElement.
     */
    @Test
    public void testToString_OneElement() {
        TourElement elem = createElementList(new int[][] {{1, 1}});
        assertEquals("(1/1)", elem.toString());
    }
    
    @Test
    public void testToString_MoreElements() {
        TourElement elem = createElementList(new int[][] {{1, 1}, {2, 2}, {3, 3}});
        assertEquals("(1/1) -> (2/2) -> (3/3)", elem.toString());
    }
    
}
