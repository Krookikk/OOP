package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    @Test
    public void testAverageScore() {
        var book = new Book("X");
        book.addGrade("Math", 1, 4);
        book.addGrade("Math", 2, 5);
        book.addGrade("Music", 2, 4);
        book.addGrade("Music", 1, 5);
        assertEquals(book.averageScore(), 4.5);
    }

    @Test
    public void testRedDiplom() {
        var book = new Book("X");
        book.addGrade("Math", 1, 5);
        book.addGrade("Music", 2, 3);
        book.addGrade("Music", 1, 5);
        book.setFinalGrade(5);
//        System.out.println(book.averageScore());
        assertFalse(book.redDiplom());
    }
}
