package org.example.hasnotprimecheck;

public class hasNonPrimeSequential extends SearchingNonPrimeNumbers {

    public boolean hasNonPrime(int[] numbers, int numThreads) {
        for (int number : numbers) {
            if (!isPrime(number)) {
                return true;
            }
        }
        return false;
    }
}
