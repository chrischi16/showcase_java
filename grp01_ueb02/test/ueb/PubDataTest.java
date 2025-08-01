package ueb;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Published test for the Data class, part of assignment 2.
 *
 * @author #####
 */
public class PubDataTest {

    @Test
    public void testIsValidStudentId() {
        assertAll(
                () -> assertTrue(Data.isValidStudentId(0)),
                () -> assertTrue(Data.isValidStudentId(2)),
                () -> assertTrue(Data.isValidStudentId(4)),
                () -> assertTrue(Data.isValidStudentId(5)),
                () -> assertTrue(Data.isValidStudentId(6))
        );
    }

    @Test
    public void testIsValidStudentId_IdTooBig() {
        assertFalse(Data.isValidStudentId(7),
                "zu große Id sollte nicht valide sein");
    }

    @Test
    public void testIsValidStudentId_negativeId() {
        assertFalse(Data.isValidStudentId(-1),
                "negative Id sollte nicht valide sein");
    }

    @Test
    public void testGetStudentName() {
        assertEquals("E. Herms", Data.getStudentName(0));
    }

    @Test
    public void testGetStudentName_Invalid() {
        assertThrows(IllegalArgumentException.class,
                     () -> Data.getStudentName(7));
    }

    @Test
    public void testIsValidExamResultId() {
        assertAll(
                () -> assertTrue(Data.isValidExamResult(0)),
                () -> assertTrue(Data.isValidExamResult(2)),
                () -> assertTrue(Data.isValidExamResult(4)),
                () -> assertTrue(Data.isValidExamResult(5))
        );
    }

    @Test
    public void testIsValidExamResultId_IdTooBig() {
        assertFalse(Data.isValidExamResult(6),
                    "zu große Id sollte nicht valide sein");
    }

    @Test
    public void testIsValidExamResultId_negativeId() {
        assertFalse(Data.isValidExamResult(-1),
                    "negative Id sollte nicht valide sein");
    }

    /**
     * Eine tiefe Kopie muss herausgegeben werden (nicht einfach die Referenz). Bei diesem Test wurde versucht, nur auf
     * Wissen aus der Vorlesung zurückzugreifen. Eine elegantere Weise ist möglich.
     */
    @Test
    public void testExamResult() {
        // erwartete Wahl
        int[][] expectedExam = {{-1, 30, 30, 30},
                {0, 30, 10, 4}, {2, 30, 14, 20}, {1, 20, 20, 10}, {4, 25, 25, 12}};

        // erwartete Wahl wird mit der Wahl Nr. 5 verglichen
        int[][] examCopy = Data.getExamResult(3);
        assertArrayEquals(expectedExam, examCopy);

        // Änderung der Werte außerhalb
        examCopy[0][0] = 5;
        examCopy[1][1] = 5;

        // erwartete Wahl wird erneut mit der Wahl Nr. 5 verglichen
        // Änderung darf keinen Einfluss auf die Werte dieses Datensatzes haben
        assertArrayEquals(expectedExam, Data.getExamResult(3));
    }

}