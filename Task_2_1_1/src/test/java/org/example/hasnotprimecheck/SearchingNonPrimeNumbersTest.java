package org.example.hasnotprimecheck;

import org.example.hasnotprimecheck.graph.Graph;
import org.example.hasnotprimecheck.search.HasNonPrimeParallel;
import org.example.hasnotprimecheck.search.HasNonPrimeParallelStream;
import org.example.hasnotprimecheck.search.HasNonPrimeSequential;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchingNonPrimeNumbersTest {
    @Test
    public void test_mini() {
        int[] numbers = {2, 3, 4};
        var a1 = new HasNonPrimeSequential(numbers);
        boolean b1 = a1.hasNonPrime();
        assertTrue(b1);
    }

    @Test
    public void test_hasNonPrimeSequentialFalse() {
        int[] numbers = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053};
        var b1 = new HasNonPrimeSequential(numbers);
        boolean a1 = b1.hasNonPrime();
        assertFalse(a1);
    }

    @Test
    public void test_hasNonPrimeSequentialTrue() {
        int[] numbers = Graph.generatePrimes(1000);
        var b1 = new HasNonPrimeSequential(numbers);
        boolean a1 = b1.hasNonPrime();
        assertTrue(a1);
    }


    @Test
    public void test_hasNonPrimeParallelFalse() {
        int[] numbers = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053};
        var b1 = new HasNonPrimeParallel(numbers, 4);
        boolean a1 = b1.hasNonPrime();
        assertFalse(a1);
    }

    @Test
    public void test_hasNonPrimeParallelTrue() {
        int[] numbers = Graph.generatePrimes(1000);
        var b1 = new HasNonPrimeParallel(numbers, 4);
        boolean a1 = b1.hasNonPrime();
        assertTrue(a1);
    }

    @Test
    public void test_hasNonPrimeParallelStreamFalse() {
        int[] numbers = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053};
        var b1 = new HasNonPrimeParallelStream(numbers, 3);
        boolean a1 = b1.hasNonPrime();
        assertFalse(a1);
    }

    @Test
    public void test_hasNonPrimeParallelStreamTrue() {
        int[] numbers = Graph.generatePrimes(1000);
        var b1 = new HasNonPrimeParallelStream(numbers, 3);
        boolean a1 = b1.hasNonPrime();
        assertTrue(a1);
    }
}
