package TP1.LineCoverageTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import TP1.Exo1Correction; // Import the corrected class

public class Exo1Test {

    @Test
    public void testNullString() {
        assertThrows(NullPointerException.class, () -> {
            Exo1Correction.isPalindrome(null);
        });
    }

    @Test
    public void testInvalidPalindrome() {
        assertFalse(Exo1Correction.isPalindrome("ab"));
    }

    @Test
    public void testValidPalindrome() {
        assertTrue(Exo1Correction.isPalindrome("aa"));
    }
}