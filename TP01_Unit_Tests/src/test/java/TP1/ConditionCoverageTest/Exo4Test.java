package TP1.ConditionCoverageTest;

import TP1.Exo4Correction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Exo4Test {

    @Test
    public void testAZero() {
        // Covers: if (a == 0)
        assertThrows(IllegalArgumentException.class, () -> {
            Exo4Correction.solve(0, 2, 1);
        });
    }

    @Test
    public void testDeltaNegative() {
        // Covers: if (delta < 0)
        // Equation: 1x^2 + 1x + 1 = 0 -> Delta = 1 - 4 = -3
        assertNull(Exo4Correction.solve(1, 1, 1));
    }

    @Test
    public void testDeltaZero() {
        // Covers: if (delta == 0)
        // Equation: 1x^2 + 2x + 1 = 0 -> Delta = 4 - 4 = 0
        // Root should be -1.0
        double[] expected = {-1.0};
        assertArrayEquals(expected, Exo4Correction.solve(1, 2, 1));
    }

    @Test
    public void testDeltaPositive() {
        // Covers: the final return statement
        // Equation: 1x^2 - 3x + 2 = 0 -> Delta = 9 - 8 = 1
        // Roots should be 2.0 and 1.0
        double[] expected = {2.0, 1.0};
        assertArrayEquals(expected, Exo4Correction.solve(1, -3, 2));
    }
}