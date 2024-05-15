package org.example.snake;

import javafx.scene.canvas.GraphicsContext;

import static org.example.snake.Game.*;

public class Updater {
    public static boolean update(GraphicsContext gc, int WIDTH, int HEIGHT, int TILE_SIZE, int foodT, int snakeL) {
        Game.Position foodValue;
        Game.Position head = snake.get(0);
        Game.Position newHead = new Game.Position(head.x, head.y);

        switch (direction) {
            case UP:
                newHead = new Game.Position(head.x, head.y - TILE_SIZE);
                break;
            case DOWN:
                newHead = new Game.Position(head.x, head.y + TILE_SIZE);
                break;
            case LEFT:
                newHead = new Game.Position(head.x - TILE_SIZE, head.y);
                break;
            case RIGHT:
                newHead = new Game.Position(head.x + TILE_SIZE, head.y);
        }

        if (newHead.x < 0 || newHead.x >= WIDTH || newHead.y < 0 || newHead.y >= HEIGHT) {
            // условие где змейка убивается о стену
            return true;
        }

        if (snake.contains(newHead)) {
            // условие где змейка убивается о себя
            return true;
        }

        snake.add(0, newHead);

        if (foods.contains(newHead)) {  // если поели
            foods.remove(newHead);

            while(true) {
                if (WIDTH * HEIGHT / (TILE_SIZE * TILE_SIZE) - snake.size() - foodT <= 0) {
                    break;
                }
                foodValue = Food.generateFoodPosition(WIDTH, HEIGHT, TILE_SIZE);
                if (!(foods.contains(foodValue) || snake.contains(foodValue))) {
                    foods.add(foodValue);
                    break;
                }
            }

        } else {
            snake.remove(snake.size() - 1); // тогда удаляем хвост
        }

        if (snake.size() >= snakeL) { // проверка на выигрыш
            Game game = new Game(gc, HEIGHT, WIDTH, foodT, snakeL);
            game.newGame(); // новая игра
        }

        return false;

    }
}
