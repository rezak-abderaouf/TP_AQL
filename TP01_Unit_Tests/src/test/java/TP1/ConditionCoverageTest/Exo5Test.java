package TP1.ConditionCoverageTest;

import TP1.Exo5Correction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Exo5Test {

    @Test
    public void testLessThanOne() {
        assertThrows(IllegalArgumentException.class, () -> {
            Exo5Correction.toRoman(0);
        });
    }

    @Test
    public void testGreaterThan3999() {
        assertThrows(IllegalArgumentException.class, () -> {
            Exo5Correction.toRoman(4000);
        });
    }

    @Test
    public void testValidRomanNumerals() {
        assertEquals("I", Exo5Correction.toRoman(1));
        assertEquals("IV", Exo5Correction.toRoman(4));
        assertEquals("M", Exo5Correction.toRoman(1000));
        assertEquals("MCMXCIV", Exo5Correction.toRoman(1994));
    }
}