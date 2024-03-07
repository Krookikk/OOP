package org.example.hasnotprimecheck.search;

public abstract class SearchingNonPrimeNumbers {

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

    public abstract boolean hasNonPrime();
}
