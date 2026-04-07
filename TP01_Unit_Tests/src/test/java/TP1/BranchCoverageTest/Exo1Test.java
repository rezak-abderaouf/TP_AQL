package TP1.BranchCoverageTest;

import TP1.Exo1Correction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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