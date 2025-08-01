package ueb;

import org.junit.jupiter.api.Test;
import ueb.TourElement;
import ueb.Waypoint;

import static org.junit.jupiter.api.Assertions.*;

public class ReqTourElementTest {

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
     * Methode "HasNext" testen
     */

    @Test
    public void testHasNextOneElement () {
        TourElement elem = createElementList(new int[][] {{1, 1}});
        assertFalse(elem.hasNext());

    }

    @Test
    public void testHasNextTwoElements () {
        TourElement elem = createElementList(new int[][] {{1, 1}, {3, 3}});
        assertFalse(elem.contains(new Waypoint(2, 2)));
        assertTrue(elem.contains(new Waypoint(1, 1)));

    }

    /**
     * Methode "GetNoOfWaypoints" testen
     */

    @Test
    public void testGetNoOfWaypointsOneElement () {
        TourElement elem = createElementList(new int[][] {{1, 1}});
        assertEquals(1, elem.getNoOfWaypoints());
    }

    @Test
    public void testGetNoOfWaypointsTwoElements () {
        TourElement elem = createElementList(new int[][] {{1, 1}, {3, 3}});
        assertEquals(2, elem.getNoOfWaypoints());

    }

    /**
     * Methode "RemoveAt" testen
     */

    @Test
    public void testRemoveAtFirstElement () {
        TourElement elem = createElementList(new int[][] {{1, 1}, {3, 3}, {4, 4}});
        assertEquals(3, elem.getNoOfWaypoints());
        Waypoint wp = new Waypoint(4, 4);
        elem = elem.removeAt(0);
        assertArrayEquals(new int[] {3, 3}, elem.getWaypoint().toArray());
        assertArrayEquals(new int[] {4, 4}, elem.getNext().getWaypoint().toArray());
        assertNull(elem.getNext().getNext());
    }

    @Test
    public void testRemoveAtLastElement () {
        TourElement elem = createElementList(new int[][] {{1, 1}, {3, 3}});
        assertEquals(2, elem.getNoOfWaypoints());
        Waypoint wp = new Waypoint(4, 4);
        elem = elem.append(wp);
        elem = elem.removeAt(2);
        assertArrayEquals(new int[] {1, 1}, elem.getWaypoint().toArray());
        assertArrayEquals(new int[] {3, 3}, elem.getNext().getWaypoint().toArray());
        assertNull(elem.getNext().getNext());
    }

}
