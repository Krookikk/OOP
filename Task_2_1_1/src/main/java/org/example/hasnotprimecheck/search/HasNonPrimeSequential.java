package org.example.hasnotprimecheck.search;

public class HasNonPrimeSequential extends SearchingNonPrimeNumbers {
    int[] numbers;
    public HasNonPrimeSequential(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNonPrime() {
        for (int number : numbers) {
            if (!isPrime(number)) {
                return true;
            }
        }
        return false;
    }
}
