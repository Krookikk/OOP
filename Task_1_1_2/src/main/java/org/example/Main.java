package org.example;

public class Main {
    public static void main(String[] args) {
        var n1 = new Polynomial(new int[] {8, -18, 0});
        //String answer = "- 3x^3 + 4x^2 + 2";
        var n2 = new Polynomial(new int[] {0});
        System.out.println(n1.toString());
    }
}