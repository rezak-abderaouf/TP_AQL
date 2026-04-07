package TP1;

public class Exo6Correction {

    public static String fizzBuzz(int n) {
        // THE BUG IS FIXED HERE (Changed <= 1 to <= 0)
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }
        if (n % 15 == 0) {
            return "FizzBuzz";
        }
        if (n % 3 == 0) {
            return "Fizz";
        }
        if (n % 5 == 0) {
            return "Buzz";
        }
        return String.valueOf(n);
    }
}