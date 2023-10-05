package org.example;

public class Main {

    public static void main(final String[] args) {
        final int[] list = {5, 4, 3, 2, 1};
        int[] mas = Heapsort.heapsort(list);
        for (int i = 0; i < mas.length; i++) {
            System.out.println(mas[i]);
        }
    }
}
