package ueb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReqTourElementTest {


    /**
     * Tests für die Methode {@link TourElement#concat(TourElement)}. Testet die Fälle:
     * <p>
     * <l>
     *     - Eine Liste mit 1 Element an eine 3er Liste hängen
     *     <p>
     *     - Eine Liste mit 2 Elementen an eine 3er Liste hängen
     * </l>
     */

    @Test
    void test_concat_OneElementToThreeElements() {
        TourElement oneElement = new TourElement(new Waypoint(0,0));
        TourElement threeElements =
                new TourElement(new Waypoint(1,1),
                        new TourElement(new Waypoint(0,0),
                                new TourElement(new Waypoint(3,3))));
        TourElement result = threeElements.concat(oneElement);
        TourElement expected =
<<<<<<< .mine
                new TourElement(new Waypoint(0,0),
                        new TourElement(new Waypoint(1,1),
                                new TourElement(new Waypoint(0,0),
                                        new TourElement(new Waypoint(3,3)))));
        assertTrue(expected.isEqual(result),
                "\nExpected: "+expected+"\n but got: "+ result);
||||||| .r70
                new TourElement(new Waypoint(0,0),
                        new TourElement(new Waypoint(1,1),
                                new TourElement(new Waypoint(0,0),
                                        new TourElement(new Waypoint(3,3)))));
        assertTrue(true,"text");
=======
                new TourElement(new Waypoint(1,1),
                        new TourElement(new Waypoint(0,0),
                                new TourElement(new Waypoint(3,3),
                                        new TourElement(new Waypoint(0,0)))));
        assertTrue(expected.isEqual(result),
                "\nExpected: "+expected+"\n but got: "+result);
>>>>>>> .r80

    }

    @Test
    void test_concat_TwoElementsToThreeElements() {
        TourElement twoElements =
                new TourElement(new Waypoint(0,0),
                    new TourElement(new Waypoint(1,1)));
        TourElement threeElements =
                new TourElement(new Waypoint(1,1),
                        new TourElement(new Waypoint(0,0),
                                new TourElement(new Waypoint(3,3))));
        TourElement result = threeElements.concat(twoElements);
        TourElement expected =
<<<<<<< .mine
                new TourElement(new Waypoint(1,1),
                        new TourElement(new Waypoint(0,0),
                                new TourElement(new Waypoint(3,3),
                                        new TourElement(new Waypoint(0,0),
                                                new TourElement(new Waypoint(1,1))))));
        assertTrue(expected.isEqual(result),
                "\nExpected: "+expected+"\n but got: "+ result);
||||||| .r70
                new TourElement(new Waypoint(1,1));
                    new TourElement(new Waypoint(0,0),
                            new TourElement(new Waypoint(1,1),
                                    new TourElement(new Waypoint(0,0),
                                            new TourElement(new Waypoint(3,3)))));
        assertTrue(true,"text");
=======
                new TourElement(new Waypoint(1,1),
                    new TourElement(new Waypoint(0,0),
                            new TourElement(new Waypoint(3,3),
                                    new TourElement(new Waypoint(0,0),
                                            new TourElement(new Waypoint(1,1))))));
        assertTrue(expected.isEqual(result),
                "\nExpected: "+expected+"\n but got: "+result);
>>>>>>> .r80
    }

    /**
     * Tests für die Methode {@link TourElement#getIdxOfClosestWaypoint(Waypoint)}. Testet die Fälle:
     * <p>
     * <l>
     *     - ein Wegpunkt dicht am ersten
     *     <p>
     *     - ein Wegpunkt gleich dem zweiten
     *     <p>
     *     - ein Wegpunkt gleich dem letzten
     * </l>
     */


    @Test
    void test_getIdxOfClosestWaypoint_CloseToFirst() {
        TourElement tourElement =
                new TourElement(new Waypoint(2,2),
                     new TourElement(new Waypoint(7,7),
                           new TourElement(new Waypoint(3,3),
                                  new TourElement(new Waypoint(5,5),
                                          new TourElement(new Waypoint(12,12))))));
        Waypoint baseWaypoint = new Waypoint(1,1);
        int result = tourElement.getIdxOfClosestWaypoint(baseWaypoint);
        int expected = 0;
        assertEquals(expected, result,
                "\nExpected: "+expected+"\n but got: "+result);
    }

    @Test
    void test_getIdxOfClosestWaypoint_CloseSameToSecond() {
        TourElement tourElement =
                new TourElement(new Waypoint(2,2),
                        new TourElement(new Waypoint(7,7),
                                new TourElement(new Waypoint(3,3),
                                        new TourElement(new Waypoint(5,5),
                                                new TourElement(new Waypoint(12,12))))));
        Waypoint baseWaypoint = new Waypoint(7,8);
        int result = tourElement.getIdxOfClosestWaypoint(baseWaypoint);
        int expected = 1;
        assertEquals(expected, result,
                "\nExpected: "+expected+"\n but got: "+result);
    }

    @Test
    void test_getIdxOfClosestWaypoint_CloseSameToLast() {
        TourElement tourElement =
                new TourElement(new Waypoint(2,2),
                        new TourElement(new Waypoint(7,7),
                                new TourElement(new Waypoint(3,3),
                                        new TourElement(new Waypoint(5,5),
                                                new TourElement(new Waypoint(12,12))))));
        Waypoint baseWaypoint = new Waypoint(12,11);
        int result = tourElement.getIdxOfClosestWaypoint(baseWaypoint);
        int expected = 4;
        assertEquals(expected, result,
                "\nExpected: "+expected+"\n but got: "+result);
    }

}