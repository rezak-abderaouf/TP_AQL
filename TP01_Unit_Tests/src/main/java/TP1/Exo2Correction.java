package TP1;

public class Exo2Correction {
    public static boolean isAnagram(String s1, String s2) {
        if (s1 == null || s2 == null) { // Condition to test
            throw new NullPointerException("Strings must not be null");
        }

        s1 = s1.replaceAll("\\s+", "").toLowerCase();
        s2 = s2.replaceAll("\\s+", "").toLowerCase();

        if (s1.length() != s2.length()) {
            return false;
        }

        int[] count = new int[26];
        // THE BUG IS FIXED HERE (< instead of <=):
        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }

        for (int c : count) {
            if (c != 0) {
                return false;
            }
        }
        return true;
    }
}