package org.example;

public class Main {
    public static void main(String[] args) {
        var exBook = new Book("X");
        exBook.addGrade("Math", 1, 4);
        exBook.addGrade("Math", 2, 5);
        exBook.addGrade("Music", 2, 5);
        exBook.addGrade("Music", 1, 5);
        exBook.addGrade("Music", 1, 5);
        exBook.addGrade("Music", 1, 3);
        exBook.setFinalGrade(5);
        System.out.println(exBook.averageScore());
        System.out.println(exBook.redDiplom());
        System.out.println(exBook.incStipend(2));
        System.out.println(exBook.size());
    }
}