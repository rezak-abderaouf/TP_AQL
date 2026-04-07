package TP1.LineCoverageTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import TP1.Exo3Correction;

public class Exo3Test {

    @Test
    public void testNullArray() {
        assertThrows(NullPointerException.class, () -> {
            Exo3Correction.binarySearch(null, 5); // Must call Exo3Correction
        });
    }

    @Test
    public void testElementInMiddle() {
        int[] arr = {1, 2, 3, 4, 5};
        assertEquals(2, Exo3Correction.binarySearch(arr, 3));
    }

    @Test
    public void testElementOnRight() {
        int[] arr = {1, 2, 3, 4, 5};
        assertEquals(4, Exo3Correction.binarySearch(arr, 5));
    }

    @Test
    public void testElementOnLeft() {
        int[] arr = {1, 2, 3, 4, 5};
        assertEquals(0, Exo3Correction.binarySearch(arr, 1));
    }

    @Test
    public void testElementNotFound() {
        int[] arr = {1, 2, 3, 4, 5};
        assertEquals(-1, Exo3Correction.binarySearch(arr, 10));
    }
}