package org.example.snake;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static org.example.snake.Game.*;
import static org.example.snake.Snake.*;

public class Renderer {
    public static void render(GraphicsContext gc) {
        gc.clearRect(0, 0, WIDTH, HEIGHT); // чистим поле, рисуем каждый раз заново
        for (int i = 0; i < elements.length; i ++) {
            for (int j = 0; j < elements[i].length; j ++) {
                Type type = elements[i][j];
                switch (type) {
                    case SNAKE:
                        gc.setFill(Color.GREEN);
                        break;
                    case WALL:
                        gc.setFill(Color.GRAY);
                        break;
                    case FOOD:
                        gc.setFill(Color.RED);
                        break;
                    case BOT:
                        gc.setFill(Color.YELLOW);
                        break;
                    case NOTHING:
                        gc.setFill(Color.WHITE);
                        break;
                }
                gc.fillRect(i * TILE_SIZE_X, j * TILE_SIZE_Y, TILE_SIZE_X, TILE_SIZE_Y);
            }
        }
    }
}
