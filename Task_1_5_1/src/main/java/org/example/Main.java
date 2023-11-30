package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner x = new Scanner(System.in);
        String line = x.nextLine();
        System.out.println(Calculator.calculator(line));
    }
}