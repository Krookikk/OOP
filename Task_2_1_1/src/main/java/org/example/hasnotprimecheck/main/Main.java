package org.example.hasnotprimecheck.main;


import org.example.hasnotprimecheck.search.HasNonPrimeSequential;

public class Main {
    public static void main(String[] args) {
        int[] numbers = {2, 3, 4};
        var b1 = new HasNonPrimeSequential(numbers);
        boolean a1 = b1.hasNonPrime();
        System.out.println(a1);
    }
}