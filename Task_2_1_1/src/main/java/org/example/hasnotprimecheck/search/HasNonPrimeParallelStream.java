package org.example.hasnotprimecheck.search;

import java.util.Arrays;

public class HasNonPrimeParallelStream extends SearchingNonPrimeNumbers {
    int numThreads;
    int[] numbers;
    public HasNonPrimeParallelStream(int[] numbers, int numThreads) {
        this.numbers = numbers;
        this.numThreads = numThreads;
    }

    @Override
    public boolean hasNonPrime() {
        if (numThreads < 1) {
            numThreads = 1;
        }
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", Integer.toString(numThreads));
        boolean a = Arrays.stream(numbers).parallel().anyMatch(n -> !isPrime(n));
        System.clearProperty("java.util.concurrent.ForkJoinPool.common.parallelism");
        return a;
    }
}
