package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchingNonPrimeNumbersTest {
    @Test
    public void test() {
        int[] numbers = {2, 3, 4};
        boolean a1 = SearchingNonPrimeNumbers.hasNonPrimeSequential(numbers);
        assertTrue(a1);
    }
}
