/**
 * This class generates primes upTo a user specified maximum.
 * The algorithm used is the sieve from Eratosthenes.
 * Given an array of integers starting by 2:
 * Find the first uncrossed integer, and cross out all its multiples
 * in the array.
 */
public class PrimeGenerator {

    private static boolean[] crossedOut;
    private static int[] result;


    public static int[] generatePrimes(int maxValue) {
        if (maxValue < 2) {
            return new int[0];
        } else {
            uncrossIntegersUpto(maxValue);
            crossOutMultiples();
            putUncrossedIntegersIntoResult();
            return result;
        }
    }

    private static void uncrossIntegersUpto(int maxValue) {
        crossedOut = new boolean[maxValue + 1];
        for (int i = 2; i < crossedOut.length; i++) {
            crossedOut[i] = false;
        }
    }

    private static void crossOutMultiples() {
        int limit = determineIterationLimit();
        for (int i = 2; i < crossedOut.length; i++) {
            if (notCrossed(i)) {
                crossOutMultiplesOf(i);
            }
        }

    }

    private static int determineIterationLimit() {
        // Every multiple int the array has a prime factor that
        // is less or equal to the sqrt of the array size
        // so we dont have to cross out any multiple of numbers
        // larger than the root
        double iterationLimit = Math.sqrt(crossedOut.length);
        return (int) iterationLimit;
    }

    private static void crossOutMultiplesOf(int i) {
        for (int multiple = 2 * i; multiple < crossedOut.length; multiple += i) {
            crossedOut[multiple] = true;
        }
    }

    private static boolean notCrossed(int i) {
        return crossedOut[i] == false;
    }

    private static void putUncrossedIntegersIntoResult() {
        result = new int[numberOfUncrossedIntegers()];
        for (int j = 0, i = 2; i < crossedOut.length; i++) {
            if (notCrossed(i)) {
                result[j++] = i;
            }
        }
    }

    private static int numberOfUncrossedIntegers() {
        int count = 0;
        for (int i = 2; i < crossedOut.length; i++) {
            if (notCrossed(i)) {
                count++;
            }
        }
        return count;
    }
}

