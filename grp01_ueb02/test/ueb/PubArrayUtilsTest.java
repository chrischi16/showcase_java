package ueb;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Published tests for the ArrayUtil-class of the assignment 2.
 *
 * @author #####
 */
public class PubArrayUtilsTest {

    @Test
    public void testSumUp_Empty() {
        assertEquals(0, ArrayUtils.sumUp(new int[]{}));
    }

    @Test
    public void testSumUp_OneElement() {
        assertEquals(1, ArrayUtils.sumUp(new int[]{1}));
    }

    @Test
    public void testSumUp_MultipleElements() {
        assertAll(
                () -> assertEquals(6, ArrayUtils.sumUp(new int[]{1, 2, 3})),
                () -> assertEquals(0, ArrayUtils.sumUp(new int[]{1, 2, -3}))
        );
    }

    @Test
    public void testRemoveValueAt_OnElement() {
        assertArrayEquals(new int[]{}, ArrayUtils.removeValueAt(0, new int[]{1}));
    }

    @Test
    public void testRemoveValueAt_FirstElement() {
        assertArrayEquals(new int[]{2, 3}, ArrayUtils.removeValueAt(0, new int[]{1, 2, 3}));
    }

    @Test
    public void testRemoveValueAt_MiddleElement() {
        assertArrayEquals(new int[]{1, 3}, ArrayUtils.removeValueAt(1, new int[]{1, 2, 3}));
    }

    @Test
    public void testRemoveValueAt_LastElement() {
        assertArrayEquals(new int[]{1, 2}, ArrayUtils.removeValueAt(2, new int[]{1, 2, 3}));
    }

    @Test
    public void testRemoveValueAt_InvalidIdx() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> ArrayUtils.removeValueAt(3, new int[]{1, 2, 3}));
    }

    @Test
    public void testRemoveValueAt_InvalidIdxBecauseEmpty() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> ArrayUtils.removeValueAt(0, new int[]{}));
    }


    @Test
    public void testDeepCopyTwoDim_DifferentArrayLengths() {
        int[][] arr = new int[][]{{1, 2, 3}, {1, 2}, {1}, {}};

        int[][] result = ArrayUtils.deepCopyTwoDim(arr);

        int[][] expected = new int[][]{{1, 2, 3}, {1, 2}, {1}, {}};

        assertArrayEquals(expected, result,
                          "expected " + Arrays.deepToString(expected) +
                                  "\n but got " + Arrays.deepToString(result));
    }

    @Test
    public void testDeepCopyTwoDim_NotSame() {
        int[][] arr = new int[][]{{1, 2, 3}, {1, 2}, {1}, {}};
        int[][] arrCopy = ArrayUtils.deepCopyTwoDim(arr);
        assertNotSame(arr, arrCopy);
    }

    @Test
    public void testDeepCopyTwoDim_DeepArraysNotSame() {
        int[][] arr = new int[][]{{1, 2, 3}, {1, 2}, {1}, {}};
        int[][] fstCall = ArrayUtils.deepCopyTwoDim(arr);
        int[][] sndCall = ArrayUtils.deepCopyTwoDim(arr);

        assertNotSame(fstCall, sndCall,
                      "Root: Every call must create a new instance");

        for (int i = 0; i < fstCall.length; ++i) {
            assertNotSame(fstCall[i], sndCall[i],
                          "First level: Every call must create a new instance");
        }
    }

    @Test
    public void testDeepCopyTwoDim_ParamMustntChange() {
        int[][] arr = new int[][]{{1, 2, 3}, {1, 2}, {1}, {}};
        int[][] ignored = ArrayUtils.deepCopyTwoDim(arr);
        int[][] expected = new int[][]{{1, 2, 3}, {1, 2}, {1}, {}};
        assertArrayEquals(expected, arr,
                          "param mustn't change\n" +
                                  "expected " + Arrays.deepToString(expected) +
                                  "\n but got " + Arrays.deepToString(arr));
    }

    @Test
    public void testDeepCopyTwoDim_ActualDeepCopy() {
        int[][] arr = new int[][]{{1, 2, 3}, {1, 2}, {1}, {}};
        int[][] arrCopy = ArrayUtils.deepCopyTwoDim(arr);
        arr[0][0] = 5;
        assertArrayEquals(new int[][]{{1, 2, 3}, {1, 2}, {1}, {}}, arrCopy,
                          "you created a shallow copy instead of a deep copy");
    }


    @Test
    public void testGetIdxOfMaxValue_AtFirstPos() {
        int[][] pairs = new int[][]{{1, 6}, {1, 3}, {2, 1}, {3, 4}};
        assertEquals(0, ArrayUtils.getIdxOfMaxValue(pairs));
    }

    @Test
    public void testGetIdxOfMaxValue_InTheMiddle() {
        int[][] pairs = new int[][]{{1, 2}, {1, 3}, {2, 6}, {3, 4}};
        assertEquals(2, ArrayUtils.getIdxOfMaxValue(pairs));
    }

    @Test
    public void testGetIdxOfMaxValue_IgnoreIndices() {
        int[][] pairs  = new int[][]{{44, 1}, {55, 9}, {99, 1}, {66, 1}};
        int[][] pairs2 = new int[][]{{44, 1}, {99, 1}, {55, 9}, {66, 1}};

        assertAll(
                () -> assertEquals(1, ArrayUtils.getIdxOfMaxValue(pairs),
                                   "highest value is 9, idx 99 should be ignored"),
                () -> assertEquals(2, ArrayUtils.getIdxOfMaxValue(pairs2),
                                   "highest value is 9, idx 99 should be ignored")
        );
    }

    @Test
    public void testGetIdxOfMaxValue_AtTheEnd() {
        int[][] pairs = new int[][]{{1, 2}, {1, 3}, {2, 1}, {3, 4}};
        assertEquals(3, ArrayUtils.getIdxOfMaxValue(pairs));
    }

    @Test
    public void testGetIdxOfMaxValue_OnlyOnePair() {
        int[][] pairs = new int[][]{{1, 2}};
        assertEquals(0, ArrayUtils.getIdxOfMaxValue(pairs));
    }

    @Test
    public void testGetIdxOfMaxValue_SameValueTwice() {
        int[][] pairs = new int[][]{{1, 2}, {1, 3}, {2, 1}, {3, 3}};
        assertEquals(1, ArrayUtils.getIdxOfMaxValue(pairs));
    }

    @Test
    public void testGetIdxOfMaxValue_EmptyArray() {
        int[][] pairs = new int[][]{};
        assertEquals(-1, ArrayUtils.getIdxOfMaxValue(pairs));
    }

    @Test
    public void testGetIdxOfMaxValue_ParamMustntChange() {
        int[][] pairs = new int[][]{{1, 2}, {6, 3}, {2, 1}};
        int ignored = ArrayUtils.getIdxOfMaxValue(pairs);
        int[][] expected = new int[][]{{1, 2}, {6, 3}, {2, 1}};
        assertArrayEquals(expected, pairs,
                          "param mustn't change\n" +
                                  "expected " + Arrays.deepToString(expected) +
                                  "\n but got " + Arrays.deepToString(pairs));
    }


    @Test
    public void testSortPairs() {
        int[][] pairs = new int[][]{{1, 2}, {1, 3}, {2, 1}, {3, 4}};
        int[][] result = ArrayUtils.sortPairs(pairs);
        int[][] expected = new int[][]{{3, 4}, {1, 3}, {1, 2}, {2, 1}};
        assertArrayEquals(expected, result,
                          "expected " + Arrays.deepToString(expected) +
                                  "\n but got " + Arrays.deepToString(result));
    }

    @Test //new
    public void testSortPairs_differentIndexOrder() {
        int[][] pairs = new int[][]{{111, 2}, {11, 3}, {22, 1}, {33, 4}};
        int[][] result = ArrayUtils.sortPairs(pairs);
        int[][] expected = new int[][]{{33, 4}, {11, 3}, {111, 2}, {22, 1}};
        assertArrayEquals(expected, result,
                          "expected " + Arrays.deepToString(expected) +
                                  "\n but got " + Arrays.deepToString(result));
    }

    @Test
    public void testSortPairs_SameValueTwice() {
        int[][] pairs = new int[][]{{1, 2}, {1, 3}, {2, 3}, {3, 4}};
        int[][] result = ArrayUtils.sortPairs(pairs);
        int[][] expected = new int[][]{{3, 4}, {1, 3}, {2, 3}, {1, 2}};
        assertArrayEquals(expected, result,
                          "expected " + Arrays.deepToString(expected) +
                                  "\n but got " + Arrays.deepToString(result));
    }

    @Test
    public void testSortPairs_OnlyOnePair() {
        int[][] pairs = new int[][]{{1, 2}};
        int[][] result = ArrayUtils.sortPairs(pairs);
        int[][] expected = new int[][]{{1, 2}};
        assertArrayEquals(expected, result,
                          "expected " + Arrays.deepToString(expected) +
                                  "\n but got " + Arrays.deepToString(result));
    }

    @Test
    public void testSortPairs_Empty() {
        int[][] pairs = new int[][]{};
        int[][] result = ArrayUtils.sortPairs(pairs);
        int[][] expected = new int[][]{};
        assertArrayEquals(expected, result,
                          "expected " + Arrays.deepToString(expected) +
                                  "\n but got " + Arrays.deepToString(result));
    }

    @Test
    public void testSortPairs_ParamMustntChange() {
        int[][] pairs = new int[][]{{1, 2}, {6, 3}, {2, 1}};
        int[][] ignored = ArrayUtils.sortPairs(pairs);  //Variable zum Verdeutlichen zugefügt
        int[][] expected = new int[][]{{1, 2}, {6, 3}, {2, 1}};
        assertArrayEquals(expected, pairs,
                          "param mustn't change\n" +
                                  "expected " + Arrays.deepToString(expected) +
                                  "\n but got " + Arrays.deepToString(pairs));
    }

}