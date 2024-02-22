package org.example.hasnotprimecheck;

abstract class SearchingNonPrimeNumbers {
    
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

    public abstract boolean hasNonPrime(int[] numbers, int numThreads);
}
