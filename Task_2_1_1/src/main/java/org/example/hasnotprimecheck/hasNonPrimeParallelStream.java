package org.example.hasnotprimecheck;

import java.util.Arrays;

public class hasNonPrimeParallelStream extends SearchingNonPrimeNumbers {

    @Override
    public boolean hasNonPrime(int[] numbers, int numThreads) {
        if (numThreads < 1) {
            numThreads = 1;
        }
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", Integer.toString(numThreads));
        return Arrays.stream(numbers).parallel().anyMatch(n -> !isPrime(n));
    }
}
