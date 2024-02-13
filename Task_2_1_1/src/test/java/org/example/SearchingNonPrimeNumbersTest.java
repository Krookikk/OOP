package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchingNonPrimeNumbersTest {
    @Test
    public void test_mini() {
        int[] numbers = {2, 3, 4};
        boolean a1 = SearchingNonPrimeNumbers.hasNonPrimeSequential(numbers);
        assertTrue(a1);
    }

    @Test
    public void test_hasNonPrimeSequential() {
        int[] numbers = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053};
        boolean a1 = SearchingNonPrimeNumbers.hasNonPrimeSequential(numbers);
        assertFalse(a1);
    }

    @Test
    public void test_hasNonPrimeParallel() {
        int[] numbers = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053};
        boolean a1 = SearchingNonPrimeNumbers.hasNonPrimeParallel(numbers, 4);
        assertFalse(a1);
    }

    @Test
    public void test_hasNonPrimeParallelStream() {
        int[] numbers = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053};
        boolean a1 = SearchingNonPrimeNumbers.hasNonPrimeParallelStream(numbers);
        assertFalse(a1);
    }

}
