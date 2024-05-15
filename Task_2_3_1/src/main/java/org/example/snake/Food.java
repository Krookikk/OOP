package org.example.snake;

import java.util.Random;

public class Food {
    public static Game.Position generateFoodPosition(int WIDTH, int HEIGHT, int TILE_SIZE) { // генерация еды
        Random rand = new Random();
        int x = rand.nextInt(WIDTH / TILE_SIZE) * TILE_SIZE; // чтобы рандомилась ровно в клетке
        int y = rand.nextInt(HEIGHT / TILE_SIZE) * TILE_SIZE;
        return new Game.Position(x, y);
    }
}
