package ueb;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests of class Waypoint. Published with Assignment.
 * @author #####
 */
public class PubWaypointTest {

    /**
     * Precision of comparison of doubles, used when doubles are compared.
     */
    private static final double EPSILON = 0.001;

    
    /**
     * Test of constructor of class Waypoint.
     */
    @Test
    public void testConstructor() {
      Waypoint wp = new Waypoint(1, 2);
      assertAll(
              () -> assertEquals(1, wp.getX()),
              () -> assertEquals(2, wp.getY())
      );
    }

    /**
     * Test of calcDistance method, of class Waypoint.
     */
    @Test
    public void testCalcDistance() {
        Waypoint wp = new Waypoint(0, 0);
        Waypoint wp2 = new Waypoint(3, 4);
        assertEquals(5, wp.calcDistance(wp2), EPSILON);
    }
    
    @Test
    public void testCalcDistance_Diagonal() {
        Waypoint wp = new Waypoint(4, 4);
        Waypoint wp2 = new Waypoint(3, 3);
        assertEquals(1.414d, wp.calcDistance(wp2), EPSILON);
    }

    /**
     * Test of isEqual method, of class Waypoint.
     */
    @Test
    public void testIsEqual() {
        Waypoint wp = new Waypoint(1, 2);
        Waypoint wp2 = new Waypoint(1, 2);
        assertTrue(wp.isEqual(wp2));
    }
    
    @Test
    public void testIsEqual_Same() {
        Waypoint wp = new Waypoint(1, 2);
        assertTrue(wp.isEqual(wp));
    }
    
    @Test
    public void testIsEqual_Not() {
        Waypoint wp = new Waypoint(1, 2);
        Waypoint wp2 = new Waypoint(2, 1);
        assertFalse(wp.isEqual(wp2));
    }
    
    @Test
    public void testIsEqual_Null() {
        Waypoint wp = new Waypoint(1, 2);
        assertFalse(wp.isEqual(null));
    }

    /**
     * Test of toArray method, of class Waypoint.
     */
    @Test
    public void testToArray() {
        Waypoint wp = new Waypoint(1, 2);
        assertArrayEquals(new int[] {1, 2}, wp.toArray());
    }

    /**
     * Test of toString method, of class Waypoint.
     */
    @Test
    public void testToString() {
        Waypoint wp = new Waypoint(1, 2);
        assertEquals("(1/2)", wp.toString());
    }
    
}
