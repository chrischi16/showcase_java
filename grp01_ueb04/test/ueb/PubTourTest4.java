package ueb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Public tests of class Tour.
 * @author #####
 */
public class PubTourTest4 {
    
    /**
     * Test of constructors, of class Tour.
     */
    @Test
    public void test0Tour() {
        //constructor is expected to exist
        Tour tour = new Tour();
        assertTrue(tour.isEmpty());
    }
    
    @Test
    public void test0Tour_intArr_Empty() {
        //constructor is expected to exist
        Tour tour = new Tour(new int[][]{});
        assertTrue(tour.isEmpty());
    }
    
    @Test
    public void test0Tour_intArr_OneElement() {
        Tour tour = new Tour(new int[][]{{0, 0}});
        assertTrue(new Waypoint(0,0).isEqual(tour.getAt(0)));
        assertNull(tour.getAt(1));
    }
    
    @Test
    public void test0Tour_intArr_ThreeElements() {
        Tour tour = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}});
        assertTrue(new Waypoint(0,0).isEqual(tour.getAt(0)));
        assertTrue(new Waypoint(1,1).isEqual(tour.getAt(1)));
        assertTrue(new Waypoint(2,2).isEqual(tour.getAt(2)));
        assertNull(tour.getAt(3));
    }

    /**
     * Test of isEqual method, of class Tour.
     */
    @Test
    public void test0IsEqual_ThreeElements() {
        Tour tourA = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}});
        Tour tourB = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}});
        assertTrue(tourA.isEqual(tourB));
    }
    @Test
    public void test0IsEqual_FirstLonger() {
        Tour tourA = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}});
        Tour tourB = new Tour(new int[][]{{0, 0}, {1, 1}});
        assertFalse(tourA.isEqual(tourB));
    }
    @Test
    public void test0IsEqual_FirstShorter() {
        Tour tourA = new Tour(new int[][]{{0, 0}, {1, 1}});
        Tour tourB = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}});
        assertFalse(tourA.isEqual(tourB));
    }
    @Test
    public void test0IsEqual_FirstDiffers() {
        Tour tourA = new Tour(new int[][]{{9, 9}, {1, 1}, {2, 2}});
        Tour tourB = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}});
        assertFalse(tourA.isEqual(tourB));
    }
    @Test
    public void test0IsEqual_LastDiffers() {
        Tour tourA = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}});
        Tour tourB = new Tour(new int[][]{{0, 0}, {1, 1}, {9, 9}});
        assertFalse(tourA.isEqual(tourB));
    }
    @Test
    public void test0IsEqual_FirstEmpty() {
        Tour tourA = new Tour();
        Tour tourB = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}});
        assertFalse(tourA.isEqual(tourB));
    }
    @Test
    public void test0IsEqual_SndEmpty() {
        Tour tourA = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}});
        Tour tourB = new Tour();
        assertFalse(tourA.isEqual(tourB));
    }
    @Test
    public void test0IsEqual_BothEmpty() {
        Tour tourA = new Tour();
        Tour tourB = new Tour();
        assertTrue(tourA.isEqual(tourB));
    }
    @Test
    public void test0IsEqual_Null() {
        Tour tourA = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}});
        Tour tourB = new Tour();
        assertFalse(tourA.isEqual(null));
        assertFalse(tourB.isEqual(null));
    }
    /**
     * Test of createConcatenatedTour method, of class Tour.
     */
    @Test
    public void testCreateConcatenatedTour_TwoPlusOne() {
        Tour tourA    = new Tour(new int[][]{{0, 0}, {1, 1}});
        Tour tourB    = new Tour(new int[][]{{2, 2}});
        Tour expected = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}}); 
        Tour result   = tourA.createConcatenatedTour(tourB);
        assertTrue(expected.isEqual(result),
                "\nExpected: " + expected.toString() + "\n but got: " + result.toString());
    }
   
    /**
     * Test of createTourWithOrder method, of class Tour.
     */
    @Test
    public void testCreateTourWithOrder_SameOrder() {
        Tour tour     = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        Tour expected = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        Tour result = tour.createTourWithOrder(new int[]{0, 1, 2, 3});
        assertTrue(expected.isEqual(result),
                   "\nExpected: " + expected.toString() + "\n but got: " + result.toString());
    }

    /**
     * Test of createPopularTour method, of class Tour.
     */
    @Test
    public void testCreatePopularTour_sameTours() {
        Tour tourA = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        Tour tourB = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        Tour expected = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        Tour result = tourA.createPopularTour(tourB);
        assertTrue(expected.isEqual(result),
                   "\nExpected: " + expected.toString() + "\n but got: " + result.toString());
    }

    /**
     * Test of createShortestTour method, of class Tour.
     */
    @Test
    public void testCreateShortestTour() {
        Tour tour = new Tour(new int[][]{{10, 10}, {1, 1}, {22, 22}, {3, 3}});
        Tour result = tour.createShortestTour(1);
        Tour expected = new Tour(new int[][]{{1, 1}, {3, 3}, {10, 10}, {22, 22}});
        assertTrue(expected.isEqual(result),
                   tour + ".createShortestTour(1)" +
                           "\nExpected: " + expected.toString() + "\n but got: " + result.toString());
    }

    /**
     * Test of createTourWithoutDuplicates method, of class Tour.
     */
    @Test
    public void testCreateTourWithoutDuplicates_DupAtEnd() {
        Tour tour = new Tour(new int[][]{{1, 1}, {2, 2}, {1, 1}});
        Tour result = tour.createTourWithoutDuplicates();
        Tour expected = new Tour(new int[][]{{1, 1}, {2, 2}});
        assertTrue(expected.isEqual(result),
                   "\nExpected: " + expected.toString() + "\n but got: " + result.toString());
    }

    /**
     * Test of createUnion method, of class Tour.
     */
    @Test
    public void testCreateUnion_OneAndOne() {
        Tour tourA = new Tour(new int[][]{{1, 1}});
        Tour tourB = new Tour(new int[][]{{12, 12}});
        Tour result = tourA.createUnion(tourB);
        Tour expected = new Tour(new int[][]{{1, 1}, {12, 12}});
        assertTrue(expected.isEqual(result),
                   "\nExpected: " + expected.toString() + "\n but got: " + result.toString());
    }
    
}
