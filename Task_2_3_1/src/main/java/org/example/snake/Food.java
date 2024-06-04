package org.example.snake;

import java.util.List;
import java.util.Random;

public class Food {
    public static void generateFoodPosition(Game.Type[][] elements, List<Game.Position> forRandom) {
        Random rand = new Random();
        while (true) {
            int index = rand.nextInt(forRandom.size());
            Game.Position coordinate = forRandom.get(index);
            if (elements[coordinate.x][coordinate.y] == Game.Type.NOTHING) {
                elements[coordinate.x][coordinate.y] = Game.Type.FOOD;
                forRandom.remove(index);
                return;
            } else {
                // Если координата занята, удаляем её из списка доступных координат
                forRandom.remove(index);
            }
        }
    }
}
