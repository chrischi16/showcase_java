package ueb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test of class Tour. Published with assignment.
 *
 * @author #####
 */
public class PubTourTest {


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

    /**
     * Test of getWaypointAt method, of class Tour. This test has to pass first
     * because this method is used of all tests that check the content of the
     * tour after changing elements.
     */
    @Test
    public void test0GetAt_First() {
        Tour tour = createTour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        assertArrayEquals(new int[]{0, 0}, tour.getAt(0).toArray());
    }

    @Test
    public void test0GetAt_Snd() {
        Tour tour = createTour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        assertArrayEquals(new int[]{1, 1}, tour.getAt(1).toArray());
    }

    @Test
    public void test0GetAt_Last() {
        Tour tour = createTour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        assertArrayEquals(new int[]{3, 3}, tour.getAt(3).toArray());
    }

    @Test
    public void test0GetAt_NegativeIndex() {
        Tour tour = createTour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        assertNull(tour.getAt(-1));
    }

    @Test
    public void test0GetAt_IndexTooBig() {
        Tour tour = createTour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        assertNull(tour.getAt(4));
    }

    @Test
    public void test0GetAt_EmptyTour() {
        Tour tour = new Tour();
        assertNull(tour.getAt(0));
    }

    @Test
    public void test0GetAt_NotChangingList() {
        Tour tour = createTour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        Waypoint unused = tour.getAt(2);
        assertAll(
                () -> assertArrayEquals(new int[]{0, 0}, tour.getAt(0).toArray()),
                () -> assertArrayEquals(new int[]{1, 1}, tour.getAt(1).toArray()),
                () -> assertArrayEquals(new int[]{2, 2}, tour.getAt(2).toArray()),
                () -> assertArrayEquals(new int[]{3, 3}, tour.getAt(3).toArray()),
                () -> assertNull(tour.getAt(4))
        );
    }

    /**
     * Test of addStart method, of class Tour. This test has to pass first
     * because this method is used in createTour() which is used in most other
     * tests.
     */
    @Test
    public void test0AddStart_ToEmptyTour() {
        Tour tour = new Tour();
        tour.addStart(new Waypoint(1, 1));
        assertAll(
                () -> assertArrayEquals(new int[]{1, 1}, tour.getAt(0).toArray()),
                () -> assertNull(tour.getAt(1))
        );
    }

    @Test
    public void test0AddStart() {
        Tour tour = new Tour();
        tour.addStart(new Waypoint(2, 2));
        tour.addStart(new Waypoint(1, 1));
        assertAll(
                () -> assertArrayEquals(new int[]{1, 1}, tour.getAt(0).toArray()),
                () -> assertArrayEquals(new int[]{2, 2}, tour.getAt(1).toArray()),
                () -> assertNull(tour.getAt(2))
        );
    }

    /**
     * Test of toString method, of class Tour.
     */
    @Test
    public void testToString() {
        Tour tour = createTour(new int[][]{{0, 0}, {1, 1}, {2, 2}});
        assertEquals("[(0/0) -> (1/1) -> (2/2)]", tour.toString());
    }

    @Test
    public void testToString_EmptyTour() {
        Tour tour = new Tour();
        assertEquals("[]", tour.toString());
    }

}
