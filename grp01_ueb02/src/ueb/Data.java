package ueb;

/**
 * Describes exam results for a group of students. You need to ensure that gathered data can not be changed during
 * runtime under any circumstances. Every exam consists of one ore more parts. Every part has a maximum number of points
 * that could be reached.
 *
 * Beschreibt die Prüfungsergebnisse für eine Gruppe von Studenten.
 * Jede Prüfung besteht aus mehreren Teilen. Jeder Teil hat eine maximal zu erreichende Punktanzahl.
 * Es muss sichergestellt werden, dass herausgegebene Daten unter keinen Umständen von außen geändert werden können.
 *
 * @author #####
 */
public class Data {

    /**
     * Namen der Studenten.
     */
    private static final String[] STUDENTS = {
            "E. Herms",
            "D. Propper",
            "G. Kayak",
            "E. Cola",
            "M. Riema",
            "N. Can't",
            "L. Newman"
    };

    /**
     * An welcher Stelle im Array die id gespeichert wird.
     */
    public static final int IDX_ID = 0;
    /**
     * An welcher Stelle im Array die Punkte gespeichert werden.
     */
    public static final int MAX_PNTS_IDX = 0;
    /**
     * Die id der MAX-POINTS.
     */
    public static final int MAX_PNTS_ID = -1;

    /**
     * Ergebnisse mehrerer Prüfungen.
     * Jede Prüfung hat als ersten Eintrag die maximale(n) Punktanzahl(en) für jede enthaltene Aufgabe,
     * alle weiteren Einträge enthalten die id des Studenten und seine in jeder Aufgabe erreichten Punkte.
     */
    private static final int[][][] EXAMS = {
            {//kein Student hat teilgenommen, daher gibt es nur die max-Punkte // index 0
                    {MAX_PNTS_ID, 100}
            },
            {//nur eine Aufgabe, nur ein Teilnehmer // index 1
                    {MAX_PNTS_ID, 100},
                    {4, 90}
            },
            {//nur eine Aufgabe, mehrere Studenten haben teilgenommen, nach id sortiert // index 2
                    {MAX_PNTS_ID, 100},
                    {0, 90},
                    {1, 75},
                    {2, 60},
                    {3, 50},
                    {4, 25}
            },
            {//mehrere Aufgaben, nicht alle Studenten, letzte Aufgabe ist schlimmste // index 3
                    {MAX_PNTS_ID, 30, 30, 30},
                    {0, 30, 10, 4},
                    {2, 30, 14, 20},
                    {1, 20, 20, 10},
                    {4, 25, 25, 12}
            },
            {//mehrere Aufgaben, mehrere Studenten, id 1 fehlt // index 4
                    {MAX_PNTS_ID, 30, 30, 20, 20},
                    {0, 14, 15, 5, 0},
                    {2, 13, 15, 5, 0},
                    {3, 14, 15, 0, 5},
                    {4, 13, 15, 0, 5}
            },
            {//mehrere Aufgaben, fast alle Studenten, einer davon ohne Punkte // index 5
                    {MAX_PNTS_ID, 50, 50},
                    {0, 10, 3},
                    {1, 15, 30},
                    {2, 42, 15},
                    {3, 13, 42},
                    {4, 30, 20},
                    {5, 0, 0}
            },

    };

    /**
     * Prüft, ob die gegebene id valide ist
     *
     * @param studentId id des Studenten
     * @return true, wenn gegebne id valide ist (= innerhalb der Grenzen des zugehörigen Arrays)
     */
    public static boolean isValidStudentId(int studentId) {

        return STUDENTS.length > studentId && studentId >= 0;
    }

    /**
     * Liefert den Namen des Studenten zur gegebenen id.
     *
     * @param studentId gegebene id, ist diese nicht valide, wird eine IllegalArgumentException ausgelöst
     * @return der Name des Studenten
     */
    public static String getStudentName(int studentId) {
        if (!isValidStudentId(studentId)) {
            throw new IllegalArgumentException("Fehler - Die Studenten ID ist falsch. (" + studentId+")");
        }
        return STUDENTS[studentId];
    }


    /**
     * Prüft, ob es Ergebnisse für die ExamensId gibt.
     *
     * @param examResultId id der Prüfung
     * @return true, wenn die id valide ist (= innerhalb der Grenzen des zugehörigen Arrays)
     */
    public static boolean isValidExamResult(int examResultId) {


        return examResultId >= 0 && examResultId < EXAMS.length;
    }

    /**
     * Liefert alles, was zur Prüfung mit der angegebenen id gehört. Das Ergebnisarray <b>muss</b> eine tiefe Kopie
     * dieser Prüfungsergebnisse sein, so dass Änderungen, die außerhalb an Werten dieses Arrays gemacht werden, nicht
     * in dem Original durchgeführt werden.
     *
     * @param examResultId die id der Prüfung. Ist sie nicht valide, wird eine IllegalArgumentException ausgelöst.
     * @return eine tiefe Kopie der Daten einer Prüfung
     */
    public static int[][] getExamResult(int examResultId) {
        if (!isValidExamResult(examResultId)) {
            throw new IllegalArgumentException("Fehler - Prüfungs ID nicht valide.");
        }
        return ArrayUtils.deepCopyTwoDim(EXAMS[examResultId]);
    }

}
