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

    public static boolean hasNonPrimeParallel(int[] numbers, int numThreads) {
        AtomicBoolean hasNonPrime = new AtomicBoolean(false);
        Thread[] threads = new Thread[numThreads];

        int blockSize = numbers.length / numThreads;
        for (int i = 0; i < numThreads; i++) {
            int start = i * blockSize;
            int end = (i == numThreads - 1) ? numbers.length : (i + 1) * blockSize;
            // blockSize >= последний промежуток < 2*blockSize

            threads[i] = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    if (!isPrime(numbers[j]) || hasNonPrime.get()) {
                        hasNonPrime.set(true);
                        break;
                    }
                }
            });
            threads[i].start();
        }

        for (Thread i : threads) {
            try {
                i.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return hasNonPrime.get();
    }

    public static boolean hasNonPrimeParallelStream(int[] numbers) {
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
