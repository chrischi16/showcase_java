package ueb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReqTourTest {
    // - - - Vorgegebene Tests - - -

    /**
     * Tests für die Methode {@link Tour#copy()}. Testet die Fälle:
     * <p>
     * <l>
     *     - mit drei Wegpunkten
     *     - mit drei Wegpunkten + soll diese Tour nicht verändern
     *     - mit drei Wegpunkten + soll nicht die selbe Referenz haben wie diese Tour
     *     <p>
     *     - Tour mit 7 Elementen / Wegpunkten, mit doppelt vorkommenden elementen
     *     und unsortierter nicht beachteter reihenfolge
     * </l>
     */

    @Test
    void test_copyThreeWaypoints(){
        Tour tour = new Tour(new int[][]{{4,4},{4,4},{2,2}});
        Tour result = tour.copy();
        Tour expected = new Tour(new int[][]{{4,4},{4,4},{2,2}});
        assertTrue(expected.isEqual(result),
                "\nExpected: " + expected + "\n but got: " + result);
    }
    @Test
    void test_copyThreeWaypointsUnchanged(){
        Tour tour = new Tour(new int[][]{{4,4},{4,4},{2,2},{7,7},{10,10},{12,12},{12,12}});
        Tour result = tour.copy();
        Tour expected = new Tour(new int[][]{{4,4},{4,4},{2,2},{7,7},{10,10},{12,12},{12,12}});
        assertTrue(expected.isEqual(result),
                "\nExpected: " + expected + "\n but got: " + result);
        assertTrue(expected.isEqual(tour),
                "\nExpected: "+expected+"\n but got: "+tour);
    }

    @Test
    void test_copy() {
        Tour tour = new Tour(new int[][]{{4,4},{4,4},{2,2},{7,7},{10,10},{12,12},{12,12}});
        Tour result = tour.copy();
        Tour expected = new Tour(new int[][]{{4,4},{4,4},{2,2},{7,7},{10,10},{12,12},{12,12}});
        assertTrue(expected.isEqual(result),
                "\nExpected: " + expected + "\n but got: " + result);
        assertFalse(result.equals(tour),
                "\nExpected: false \n but got: true");
    }

    /**
     * Tests für die Methode {@link Tour#createConcatenatedTour(Tour)}. Testet die Fälle:
     * <p>
     * <l>
     *     - verbindet eine leere Tour mit einer Tour mit einem Wegpunkt
     *     - verbindet eine Tour mit einem Wegpunkt mit einer leeren Tour
     *     <p>
     *     - verbindet eine Tour mit 7 elementen mit einer Tour mit 6 elementen, mit jeweils mehreren wegpunkten die
     *     anderen gleichen, in unsortierter unbeachteter reihenfolge
     * </l>
     */

    @Test
    void test_createConcatenatedTour_EmptyWithWaypoint() {
        Tour tourEmpty = new Tour();
        Tour tourSingle = new Tour(new int[][]{{2, 2}});
        Tour result = tourEmpty.createConcatenatedTour(tourSingle);
        Tour expected = new Tour(new int[][]{{2, 2}});
        assertTrue(expected.isEqual(result),
                "\nExpected: " + expected + "\n but got: " + result);

    }
    @Test
    void test_createConcatenatedTour_WaypointWithEmpty() {
        Tour tourEmpty = new Tour(new int[][]{{2, 2}});
        Tour tourSingle = new Tour();
        Tour result = tourEmpty.createConcatenatedTour(tourSingle);
        Tour expected = new Tour(new int[][]{{2, 2}});
        assertTrue(expected.isEqual(result),
                "\nExpected: " + expected + "\n but got: " + result);

    }
    @Test
    void test_createConcatenatedTour_SevenElementsWithSixElementsUnsorted(){
        Tour tourA = new Tour(new int [][]{{10,10},{4,4},{12,12},{2,2},{7,7},{4,4},{12,12}});
        Tour tourB = new Tour(new int[][]{{7,7},{1,1},{14,14},{4,4},{2,2},{7,7}});
        Tour result = tourA.createConcatenatedTour(tourB);
        Tour expected = new Tour(
                new int[][]{{10,10},{4,4},{12,12},{2,2},{7,7},{4,4},{12,12},{7,7},{1,1},{14,14},{4,4},{2,2},{7,7}}
        );
        assertTrue(expected.isEqual(result),
                "\nExpected: "+expected+"\n but got: "+result);
    }

    /**
     * Tests für die Methode {@link Tour#createTourWithOrder(int[])}. Testet die Fälle:
     * <p>
     * <l>
     *     - mit den Indizes in umgekehrter Reihenfolge (3 bis 0)
     *     <p>
     *     - mit den Indizes von jedem zweiten Element
     *     <p>
     *     - mit mehr Indizes als Wegpunkte in der Tour
     * </l>
     */

    @Test
    void test_createTourWithOrder_Inverted() {
        Tour tour = new Tour(new int [][]{{10,10},{4,4},{12,12},{2,2},{7,7},{4,4},{12,12}});

        Tour result = tour.createTourWithOrder(new int[] {3,2,1,0});
        Tour expected = new Tour(
                new int[][]{{2,2},{12,12},{4,4},{10,10}}
        );
        assertTrue(expected.isEqual(result),
                "\nExpected: "+expected+"\n but got: "+result);
    }
    @Test
    void test_createTourWithOrder_EverySecondElement() {
        Tour tour = new Tour(new int[][]{{10, 10}, {4, 4}, {12, 12}, {2, 2}, {7, 7}, {4, 4}, {12, 12}});
        Tour result = tour.createTourWithOrder(new int[]{1, 3, 5});
        Tour expected = new Tour(
                new int[][]{{4, 4}, {2, 2}, {4, 4}}
        );
        assertTrue(expected.isEqual(result),
                "\nExpected: " + expected + "\n but got: " + result);
    }
    @Test
    void test_createTourWithOrder_MoreIndicesThenElements(){
        Tour tour = new Tour(new int [][]{{10,10},{4,4},{12,12},{2,2},{7,7},{4,4},{12,12}});
        Tour result = tour.createTourWithOrder(new int[] {6,2,3,6,2,3,2,0,1,3,3});
        Tour expected = new Tour(
                new int[][]{{12,12}, {12,12},{2,2},{12,12},{12,12},{2,2},{12,12},{10,10},{4,4},{2,2},{2,2}}
        );
        assertTrue(expected.isEqual(result),
                "\nExpected: "+expected+"\n but got: "+result);
    }

    /**
     * Tests für die Methode {@link Tour#createPopularTour(Tour)}. Testet die Fälle:
     * <p>
     * <l>
     *     - die übergebene enthält jeden zweiten Wegpunkt dieser Tour beginnend beim <b>ersten</b>
     *     <p>
     *     - die übergebene enthält jeden zweiten Wegpunkt dieser Tour beginnend beim <b>zweiten</b>
     * </l>
     */

    @Test
    void test_createPopularTour_EverySecondElementStartFirst() {
        Tour tourA = new Tour(new int [][]{{10,10},{4,4},{12,12},{2,2},{7,7},{4,4},{12,12}});
        Tour tourB = new Tour(new int[][]{{10,10},{12,12},{7,7},{12,12}});
        Tour result = tourA.createPopularTour(tourB);
        Tour expected = new Tour(new int[][]{{10,10},{12,12},{7,7},{12,12}});
        assertTrue(expected.isEqual(result),
                "\nExpected: "+expected+"\n but got: "+result);
    }
    @Test
    void test_createPopularTour_EverySecondElementStartSecond() {
        Tour tourA = new Tour(new int [][]{{10,10},{4,4},{12,12},{2,2},{7,7},{4,4},{12,12}});
        Tour tourB = new Tour(new int[][]{{4,4},{2,2},{4,4}});
        Tour result = tourA.createPopularTour(tourB);
        Tour expected = new Tour(new int[][]{{4,4},{2,2},{4,4}});
        assertTrue(expected.isEqual(result),
                "\nExpected: "+expected+"\n but got: "+result);
    }

    @Test
    void test_createPopularTour() {
        Tour tourA = new Tour(new int [][]{{10,10},{4,4},{12,12},{2,2},{7,7},{4,4},{12,12}});
        Tour tourB = new Tour(new int[][]{{7,7},{1,1},{14,14},{4,4},{2,2},{7,7}});
        Tour result = tourA.createPopularTour(tourB);
        Tour expected = new Tour(new int[][]{{4,4},{2,2},{7,7}});
        assertTrue(expected.isEqual(result),
                "\nExpected: "+expected+"\n but got: "+result);
    }

    /**
     * Tests für die Methode {@link Tour#createShortestTour(int)} . Testet die Fälle:
     * <p>
     * <l>
     *     - übergebene tour startet mit idx 1 als startpunkt und sortiert sich dann nach nähe des vorherigen wegpunktes
     * </l>
     */

    // - - - eigenständige Tests - - -

    @Test
    void test_createShortestTour() {
        Tour tour = new Tour(new int [][]{{10,10},{4,4},{12,12},{2,2},{7,7},{4,4},{12,12}});
        Tour result = tour.createShortestTour(1);
        Tour expected = new Tour(new int[][]{{4,4},{4,4},{2,2},{7,7},{10,10},{12,12},{12,12}});
        assertTrue(expected.isEqual(result),
                "\nExpected: " + expected.toString() + "\n but got: " + result.toString());
    }

    /**
     * Tests für die Methode {@link Tour#createTourWithoutDuplicates()}. Testet die Fälle:
     * <p>
     * <l>
     *     - übernimmt die Tour die unsortiert unbeachtet ist, jedoch 2 duplikate von wegpunkte hat
     * </l>
     */

    @Test
    void test_createTourWithoutDuplicates() {
        Tour tourA = new Tour(new int [][]{{10,10},{4,4},{12,12},{2,2},{7,7},{4,4},{12,12}});
        Tour result = tourA.createTourWithoutDuplicates();
        Tour expected = new Tour(new int[][]{{10,10},{4,4},{12,12},{2,2},{7,7}});
        assertTrue(expected.isEqual(result),
                "\nExpected: "+expected+"\n but got: "+result);
    }

    /**
     * Tests für die Methode {@link Tour#createUnion(Tour)} . Testet die Fälle:
     * <p>
     * <l>
     *     - vereinigt eine Tour mit zwei Duplikat Waypoints mit einer Tour mit einem Duplikat waypoint,
     *     die beide gemeinsam bezogen drei gemeinsame duplikat Waypoints besitzen
     *     <p>
     *     - vereint eine Tour mit zwei Duplikat Waypoints mit einer Leeren Tour
     *     <p>
     *     - vereint eine leere Tour mit einer Tour mit zwei Duplikat Waypoints
     *     <p>
     *     - vereint zwei Leere Touren
     * </l>
     */

    @Test
    void test_createUnion() {
        Tour tourA = new Tour(new int [][]{{10,10},{4,4},{12,12},{2,2},{7,7},{4,4},{12,12}});
        Tour tourB = new Tour(new int[][]{{7,7},{1,1},{14,14},{4,4},{2,2},{7,7}});
        Tour result = tourA.createUnion(tourB);
        Tour expected = new Tour(new int[][]{{10,10},{12,12},{14,14},{7,7},{4,4},{2,2},{1,1}});
        assertTrue(expected.isEqual(result),
                "\nExpected: "+expected+"\n but got: "+result);
    }
    @Test
    void test_createUnion_second_empty() {
        Tour tourA = new Tour(new int [][]{{10,10},{4,4},{12,12},{2,2},{7,7},{4,4},{12,12}});
        Tour tourB = new Tour();
        Tour result = tourA.createUnion(tourB);
        Tour expected = new Tour(new int[][]{{10,10},{12,12},{7,7},{4,4},{2,2}});
        assertTrue(expected.isEqual(result),
                "\nExpected: "+expected+"\n but got: "+result);
    }
    @Test
    void test_createUnion_first_empty() {
        Tour tourA = new Tour();
        Tour tourB = new Tour(new int [][]{{10,10},{4,4},{12,12},{2,2},{7,7},{4,4},{12,12}});
        Tour result = tourA.createUnion(tourB);
        Tour expected = new Tour(new int[][]{{10,10},{12,12},{7,7},{4,4},{2,2}});
        assertTrue(expected.isEqual(result),
                "\nExpected: "+expected+"\n but got: "+result);
    }
    @Test
    void test_createUnion_both_empty() {
        Tour tourA = new Tour();
        Tour tourB = new Tour();
        Tour result = tourA.createUnion(tourB);
        Tour expected = new Tour();
        assertTrue(expected.isEqual(result),
                "\nExpected: "+expected+"\n but got: "+result);
    }
}