package ueb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ReqTourTest {

    /**
     * Helper method to create a tour with the given waypoints.
     *
     * @param waypoints array of waypoints to use, the coordinates of the
     *                  waypoints are also in an array
     * @return tour with the given waypoints
     */
    private Tour createTour(int[][] waypoints) {
        Tour tour = new Tour();
        for (int i = waypoints.length - 1; i >= 0; i--) {
            Waypoint wp = new Waypoint(waypoints[i][0], waypoints[i][1]);
            tour.addStart(wp);
        }
        return tour;
    }

    // TODO testGetStart (1,2 und kein Wegpunkt)

    /**
     * Methode "GetStart" testen
     */


    @Test
    public void testGetStart_OneElement () {
        Tour tour = createTour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        assertArrayEquals(new int[]{0, 0}, tour.getAt(0).toArray());
    }

    @Test
    public void testGetStart_TwoElements () {
        Tour tour = createTour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        assertArrayEquals(new int[]{0, 0}, tour.getAt(0).toArray());
    }

    @Test
    public void testGetStart_NoElements () {
        Tour tour = createTour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        assertArrayEquals(new int[]{0, 0}, tour.getAt(0).toArray());
    }

    // TODO testIsEmpty (1,2 und kein Wegpunkt)

    /**
     * Methode "IsEmpty" testen
     */


    @Test
    public void testIsEmpty_OneElement () {
        Tour tour = createTour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        assertArrayEquals(new int[]{0, 0}, tour.getAt(0).toArray());
    }

    @Test
    public void testIsEmpty_TwoElements () {
        Tour tour = createTour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        assertArrayEquals(new int[]{0, 0}, tour.getAt(0).toArray());
    }

    @Test
    public void testIsEmpty_NoElements () {
        Tour tour = createTour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        assertArrayEquals(new int[]{0, 0}, tour.getAt(0).toArray());
    }

    // TODO testGetNoOfWaypoints (1,2 und kein Wegpunkt)

    /**
     * Methode "GetGetNoOfWaypoints" testen
     */


    @Test
    public void testGetGetNoOfWaypoints_OneElement () {
        Tour tour = createTour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        assertArrayEquals(new int[]{0, 0}, tour.getAt(0).toArray());
    }

    @Test
    public void testGetGetNoOfWaypoints_TwoElements () {
        Tour tour = createTour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        assertArrayEquals(new int[]{0, 0}, tour.getAt(0).toArray());
    }

    @Test
    public void testGetGetNoOfWaypoints_NoElements () {
        Tour tour = createTour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        assertArrayEquals(new int[]{0, 0}, tour.getAt(0).toArray());
    }


}
