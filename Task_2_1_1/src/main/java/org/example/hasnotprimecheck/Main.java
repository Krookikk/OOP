package org.example.hasnotprimecheck;


public class Main {
    public static void main(String[] args) {
        int[] numbers = {2, 3, 4};
        var b1 = new hasNonPrimeSequential();
        boolean a1 = b1.hasNonPrime(numbers, 1);
        System.out.println(a1);
    }
}