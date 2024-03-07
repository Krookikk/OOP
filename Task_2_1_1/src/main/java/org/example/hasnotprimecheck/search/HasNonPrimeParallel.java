package org.example.hasnotprimecheck.search;

import java.util.concurrent.atomic.AtomicBoolean;

public class HasNonPrimeParallel extends SearchingNonPrimeNumbers {
    int numThreads;
    int[] numbers;
    public HasNonPrimeParallel(int[] numbers, int numThreads) {
        this.numbers = numbers;
        this.numThreads = numThreads;
    }

    private static Thread[] createThreads(int[] numbers, int numThreads, AtomicBoolean hasNonPrime) {
        int blockSize = numbers.length / numThreads;
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            int start = i * blockSize;
            int end = (i == numThreads - 1) ? numbers.length : (i + 1) * blockSize;
            // blockSize >= последний промежуток < 2*blockSize

            threads[i] = new HasNonPrimeCreateThread(start, end, hasNonPrime, numbers);
        }
        return threads;
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

    @Override
    public boolean hasNonPrime() {
        if (numThreads < 1) {
            numThreads = 1;
        }

        AtomicBoolean hasNonPrime = new AtomicBoolean(false);
        Thread[] threads = createThreads(numbers, numThreads, hasNonPrime);

        startThreads(threads);
        waitThreads(threads);

        return hasNonPrime.get();
    }
}
