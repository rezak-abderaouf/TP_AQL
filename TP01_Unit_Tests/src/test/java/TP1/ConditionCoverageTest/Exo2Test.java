package TP1.ConditionCoverageTest;

import TP1.Exo2Correction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Exo2Test {

    @Test
    public void testFirstStringNull() {
        assertThrows(NullPointerException.class, () -> {
            Exo2Correction.isAnagram(null, "test");
        });
    }

    @Test
    public void testSecondStringNull() {
        // Added to achieve 100% Condition Coverage for the OR (||) statement
        assertThrows(NullPointerException.class, () -> {
            Exo2Correction.isAnagram("test", null);
        });
    }

    @Test
    public void testDifferentLengths() {
        assertFalse(Exo2Correction.isAnagram("hello", "hi"));
    }

    @Test
    public void testValidAnagram() {
        assertTrue(Exo2Correction.isAnagram("chien", "niche"));
    }

    @Test
    public void testInvalidAnagramSameLength() {
        assertFalse(Exo2Correction.isAnagram("abc", "def"));
    }
}