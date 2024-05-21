package org.example.snake;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class FoodTest {
    @Test
    public void testGenerateFood() {
        var elements = new Game.Type[2][2];
        List<Game.Position> forRandom = new ArrayList<>();

        for (Game.Type[] element : elements) {
            Arrays.fill(element, Game.Type.NOTHING);
        }
        for (int i = 0; i < elements.length; i ++) {
            for (int j = 0; j < elements[i].length; j++) {
                forRandom.add(new Game.Position(i, j));
            }
        }

        Food.generateFoodPosition(elements, forRandom);
        assertEquals(forRandom.size(), 3);
    }

}
