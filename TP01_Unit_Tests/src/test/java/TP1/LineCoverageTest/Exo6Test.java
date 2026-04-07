package TP1.LineCoverageTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import TP1.Exo6Correction;

public class Exo6Test {

    @Test
    public void testNotPositive() {
        // Covers the exception throw for 0 or negative numbers
        assertThrows(IllegalArgumentException.class, () -> {
            Exo6Correction.fizzBuzz(0);
        });
    }

    @Test
    public void testFizzBuzz() {
        // Covers the "if (n % 15 == 0)" branch
        assertEquals("FizzBuzz", Exo6Correction.fizzBuzz(15));
    }

    @Test
    public void testFizz() {
        // Covers the "if (n % 3 == 0)" branch
        assertEquals("Fizz", Exo6Correction.fizzBuzz(3));
    }

    @Test
    public void testBuzz() {
        // Covers the "if (n % 5 == 0)" branch
        assertEquals("Buzz", Exo6Correction.fizzBuzz(5));
    }

    @Test
    public void testRegularNumber() {
        // Covers the final return statement at the bottom
        assertEquals("1", Exo6Correction.fizzBuzz(1));
        assertEquals("2", Exo6Correction.fizzBuzz(2));
    }
}