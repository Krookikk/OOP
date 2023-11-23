package org.example;

public class Main {
    public static void main(String[] args) {
        var book = new Book("X");
        book.addGrade("Math", 1, 5);
        book.addGrade("Music", 1, 3);
        System.out.println(book.averageScore());
    }
}