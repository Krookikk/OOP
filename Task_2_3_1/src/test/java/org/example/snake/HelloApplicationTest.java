package org.example.snake;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class HelloApplicationTest {
    @Test
    public void testGenerateFood() {
        var a = HelloApplication.generateFoodPosition();
        assertTrue(a.x < HelloApplication.WIDTH && a.y < HelloApplication.HEIGHT && a.x >= 0 && a.y >= 0);
    }

}
