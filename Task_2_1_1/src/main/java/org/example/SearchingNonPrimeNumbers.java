package org.example;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class SearchingNonPrimeNumbers {
    public static boolean hasNonPrimeSequential(int[] numbers) {
        for (int number : numbers) {
            if (!isPrime(number)) {
                return true;
            }
        }
        return false;
    }

    private static Thread[] createThreads(int[] numbers, int numThreads, AtomicBoolean hasNonPrime) {
        int blockSize = numbers.length / numThreads;
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            int start = i * blockSize;
            int end = (i == numThreads - 1) ? numbers.length : (i + 1) * blockSize;
            // blockSize >= последний промежуток < 2*blockSize

            threads[i] = createThread(start, end, hasNonPrime, numbers);
        }
        return threads;
    }

    private static Thread createThread(int start, int end, AtomicBoolean hasNonPrime, int[] numbers) {
        Thread thread = new Thread(() -> {
            for (int j = start; j < end; j++) {
                if (!isPrime(numbers[j]) || hasNonPrime.get()) {
                    hasNonPrime.set(true);
                    break;
                }
            }
        });
        return thread;
    }


    private static void startThreads(Thread[] threads) {
        for (Thread i : threads) {
            i.start();
        }
    }

    private static void waitThreads(Thread[] threads) {
        for (Thread i : threads) {
            try {
                i.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean hasNonPrimeParallel(int[] numbers, int numThreads) {
        if (numThreads < 1) {
            numThreads = 1;
        }

        AtomicBoolean hasNonPrime = new AtomicBoolean(false);
        Thread[] threads = createThreads(numbers, numThreads, hasNonPrime);

        startThreads(threads);
        waitThreads(threads);

        return hasNonPrime.get();
    }

    public static boolean hasNonPrimeParallelStream(int[] numbers, int numThreads) {
        if (numThreads < 1) {
            numThreads = 1;
        }
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", Integer.toString(numThreads));
        return Arrays.stream(numbers).parallel().anyMatch(n -> !isPrime(n));
    }


    // Проверка, является ли число простым
    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
