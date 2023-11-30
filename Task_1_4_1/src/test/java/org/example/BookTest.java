package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;


public class BookTest {
    @Test
    public void TestNoDuplicate() {
        var exBook = new Book("X");
        exBook.addGrade("Math", 1, 4);
        exBook.addGrade("Math", 2, 5);
        exBook.addGrade("Music", 2, 5);
        exBook.addGrade("Music", 1, 5);
        exBook.addGrade("Music", 1, 5);
        exBook.addGrade("Music", 1, 3);
        exBook.setFinalGrade(5);
        assertEquals(exBook.size(), 4);
    }

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
        book.addGrade("Math", 1, 3);
        book.addGrade("Music", 2, 5);
        book.addGrade("Music", 1, 5);
        book.setFinalGrade(5);
        assertFalse(book.redDiplom());
    }

    @Test
    public void testRedDiplom2() {
        var book = new Book("X");
        book.addGrade("Math", 1, 5);
        book.addGrade("Music", 2, 5);
        book.addGrade("Music", 1, 4);
        book.setFinalGrade(5);
        assertFalse(book.redDiplom());
    }

    @Test
    public void testRedDiplom3() {
        var book = new Book("X");
        book.addGrade("Math", 1, 5);
        book.addGrade("Music", 2, 5);
        book.addGrade("Music", 1, 5);
        book.setFinalGrade(4);
        assertFalse(book.redDiplom());
    }

    @Test
    public void testIncStipend() {
        var exBook = new Book("X");
        exBook.addGrade("Math", 1, 5);
        exBook.addGrade("Math", 2, 5);
        exBook.addGrade("Music", 2, 4);
        exBook.addGrade("Music", 1, 5);
        exBook.addGrade("Physics", 2, 5);
        assertFalse(exBook.incStipend(2));
        assertTrue(exBook.incStipend(1));
    }

}
