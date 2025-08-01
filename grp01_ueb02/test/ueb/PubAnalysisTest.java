package ueb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PubAnalysisTest {

    @Test
    public void testCalcGrade_NotRoundingNecessary() {
        assertAll(
                () -> assertEquals(10, Analysis.calcGrade(10, 100)),
                () -> assertEquals(66, Analysis.calcGrade(66, 100))
        );
    }

    @Test
    public void testCalcGrade_RoundDown() {
        assertAll(
                () -> assertEquals(11, Analysis.calcGrade(10, 90)), //10:90 = 11,111...
                () -> assertEquals(22, Analysis.calcGrade(20, 90)),
                () -> assertEquals(33, Analysis.calcGrade(30, 90)),
                () -> assertEquals(44, Analysis.calcGrade(40, 90)),
                () -> assertEquals(67, Analysis.calcGrade(60, 90))
        ); //60:90 = 66,666...
    }

    @Test
    public void testCalcGrade_RoundUp() {
        assertAll(
                () -> assertEquals(56, Analysis.calcGrade(50, 90)), //10:90 = 11,111...
                () -> assertEquals(67, Analysis.calcGrade(60, 90)),
                () -> assertEquals(78, Analysis.calcGrade(70, 90)),
                () -> assertEquals(89, Analysis.calcGrade(80, 90)),
                () -> assertEquals(38, Analysis.calcGrade(33, 87)) //60:90 = 66,666...
        );
    }


    @Test
    public void testCalcAllGrades_Exam0() {
        int[][] result = Analysis.calcAllGrades(Data.getExamResult(0));
        assertEquals(0, result.length, "sinnvolle Mitteilung");
    }

    @Test
    public void testCalcMean_Exam0() {
        int result = Analysis.calcMean(Data.getExamResult(0));
        assertEquals(-1, result);
    }
}