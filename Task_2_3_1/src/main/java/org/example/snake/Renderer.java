package org.example.snake;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static org.example.snake.Game.foods;
import static org.example.snake.Game.snake;

public class Renderer {
    public static void render(GraphicsContext gc, int WIDTH, int HEIGHT, int TILE_SIZE) {
        gc.clearRect(0, 0, WIDTH, HEIGHT); // чистим поле, рисуем каждый раз заново

        gc.setFill(Color.RED);
        for (Game.Position pos : foods) {
            gc.fillRect(pos.x, pos.y, TILE_SIZE, TILE_SIZE);
        }

        gc.setFill(Color.GREEN);
        for (Game.Position pos : snake) {
            gc.fillRect(pos.x, pos.y, TILE_SIZE, TILE_SIZE);
        }
    }
}
