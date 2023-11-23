package org.example;

public class Main {
    public static void main(String[] args) {
        var book = new Book("X");
        book.addGrade("Math", 1, 4);
        book.addGrade("Math", 2, 5);
        book.addGrade("Music", 2, 4);
        book.addGrade("Music", 1, 5);
        book.setFinalGrade(5);
        System.out.println(book.averageScore());
        System.out.println(book.redDiplom());
    }
}