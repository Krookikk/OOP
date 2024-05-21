package org.example.snake;

import javafx.scene.canvas.GraphicsContext;

import static org.example.snake.Game.*;
import static org.example.snake.Snake.*;

public class Updater {
    private static Game.Position directionHead(Game.Position head) {
        switch (Game.getDirection()) {
            case UP:
                return new Game.Position(head.x, head.y - 1);
            case DOWN:
                return new Game.Position(head.x, head.y + 1);
            case LEFT:
                return new Game.Position(head.x - 1, head.y);
            case RIGHT:
                return new Game.Position(head.x + 1, head.y);
        }
        return new Game.Position(head.x, head.y);
    }

    private static boolean editSnake(Game.Position newHead, int WIDTH, int HEIGHT, int foodT) {
//        Game.Position foodValue;
        snake.add(0, newHead);
        forRandom.remove(newHead);

        if (elements[newHead.x][newHead.y] == Type.FOOD) {  // если поели
            if (WIDTH * HEIGHT / (TILE_SIZE_X * TILE_SIZE_Y) - snake.size() - foodT > 0) { //есть место для еды
                Food.generateFoodPosition(elements, forRandom);
            }
        } else if (elements[newHead.x][newHead.y] == Type.NOTHING) {
            Position tail = snake.get(snake.size() - 1);
            forRandom.add(tail);
            elements[tail.x][tail.y] = Type.NOTHING;
            snake.remove(snake.size() - 1); // тогда удаляем хвост
        } else {
            return true;
        }

        elements[newHead.x][newHead.y] = Type.SNAKE; // значение новой головы меняем на значение змейки
        return false;
    }
    public static boolean update(GraphicsContext gc, int foodT, int snakeL, int wallT) {
        Game.Position head = snake.get(0);
        Game.Position newHead;
        newHead = directionHead(head);

        if (newHead.x < 0 || newHead.x >= WIDTH / TILE_SIZE_X || newHead.y < 0 || newHead.y >= HEIGHT / TILE_SIZE_Y) {
            // условие где змейка убивается о внешнюю стену, выход за массив
            return true;
        }

        if (editSnake(newHead, WIDTH, HEIGHT, foodT)) { // изменение змейки
            return true; // значит змейка умерла
        }

        if (snake.size() >= snakeL) { // проверка на выигрыш
            Game game = new Game(gc, foodT, snakeL, wallT);
            game.newGame(); // новая игра
        }
        return false;
    }
}
