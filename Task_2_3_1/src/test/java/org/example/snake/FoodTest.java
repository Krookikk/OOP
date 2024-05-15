package org.example.snake;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class FoodTest {
    @Test
    public void testGenerateFood() {
        var a = Food.generateFoodPosition(20, 20, 5);
        assertTrue(a.x < 20 && a.y < 20 && a.x >= 0 && a.y >= 0);
    }

}
