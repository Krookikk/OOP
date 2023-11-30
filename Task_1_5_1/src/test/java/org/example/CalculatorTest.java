package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CalculatorTest {
    @Test
    public void test() {
        String str = "+ - 64 * 15 4 / 20 4";
        var answ = 9;
        assertEquals(answ, Calculator.calculator(str));
    }

    @Test
    public void testSqrt() {
        String str = "sqrt + - 64 * 15 4 / 20 4";
        var answ = 3;
        assertEquals(answ, Calculator.calculator(str));
    }

    @Test
    public void testlog() {
        String str = "log 50";
        var answ = 3.912023005428146;
        assertEquals(answ, Calculator.calculator(str));
    }

    @Test
    public void testPow() {
        String str = "pow + 1 5 4";
        var answ = 1296;
        assertEquals(answ, Calculator.calculator(str));
    }
}
