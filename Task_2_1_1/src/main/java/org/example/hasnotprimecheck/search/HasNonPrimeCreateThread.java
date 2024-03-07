package org.example.hasnotprimecheck.search;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.example.hasnotprimecheck.search.SearchingNonPrimeNumbers.isPrime;

public class HasNonPrimeCreateThread extends Thread {
    int start, end;
    AtomicBoolean hasNonPrime;
    int[] numbers;

    public HasNonPrimeCreateThread(int start, int end, AtomicBoolean hasNonPrime, int[] numbers) {
        this.start = start;
        this.end = end;
        this.hasNonPrime = hasNonPrime;
        this.numbers = numbers;
    }

    public void run() {
        for (int j = start; j < end; j++) {
            if (!isPrime(numbers[j]) || hasNonPrime.get()) {
                hasNonPrime.set(true);
                break;
            }
        }
    }
}
