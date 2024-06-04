package org.example.snake;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.snake.Snake.*;

public class Game {
    private boolean gameOver;
    private final int snakeL;
    private static final int SPEED = 4;
    public static final int countBots = 5;
    public static List<Game.Position> forRandom;
    public static Type[][] elements;

    public enum Type {
        SNAKE, WALL, FOOD, BOT, NOTHING
    }
    public static List<Position> snake;
    public static List<List<Position>> bots;
    private final GraphicsContext gc;
    private final int foodT;
    private final int wallT;
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
    static Direction direction;
    private static boolean oneEvent = true;

    public Game(GraphicsContext gc, int foodT, int snakeL, int wallT) {
        this.gc = gc;
        this.foodT = foodT;
        this.snakeL = snakeL;
        this.wallT = wallT;
    }

    public static boolean isOneEvent() {
        return oneEvent;
    }
    public static void setOneEvent(boolean oneEvent) {
        Game.oneEvent = oneEvent;
    }
    public static Direction getDirection() {
        return direction;
    }
    public static void setDirection(Direction direction) {
        Game.direction = direction;
    }

    public static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Position position = (Position) obj;
            return x == position.x && y == position.y;
        }
    }

    public void newGame() {
        forRandom = new ArrayList<>();
        elements = new Type[WIDTH / TILE_SIZE_X][HEIGHT / TILE_SIZE_Y];
        snake = new ArrayList<>();
        bots = new ArrayList<>();

        for (Type[] element : elements) {
            Arrays.fill(element, Type.NOTHING);
        }

        for (int i = 0; i < elements.length; i ++) {
            for (int j = 0; j < elements[i].length; j++) {
                forRandom.add(new Position(i, j));
            }
        }

        elements[WIDTH / TILE_SIZE_X / 2][HEIGHT / 2 / TILE_SIZE_Y] = Type.SNAKE; // змейку на середину поля
        snake.add(new Position(WIDTH / 2 / TILE_SIZE_X, HEIGHT / 2 / TILE_SIZE_Y));




        forRandom.remove(snake.get(0));
        for (int i = 0; i < wallT; i ++) { // генерим заданное кол-во стен
            Wall.generateWalls(elements, forRandom);
        }

        for (int i = 0; i < foodT; i ++) { // генерим заданное кол-во еды
            Food.generateFoodPosition(elements, forRandom);
        }

        for (int i = 0; i < countBots; i ++) { // генерим заданное кол-во ботов
            bots.add(new ArrayList<>());
            Position coordinate = Bots.generateBotPositionfirst(elements, forRandom);
            bots.get(i).add(coordinate);
        }


        direction = Direction.RIGHT;
        gameOver = false;
    }

    public void startGame() {
        newGame();

        Thread thread = new Thread(() -> {
            long time = 0;
            while (true) { // обновление пять раз в секунду
                if (System.currentTimeMillis() - time >= 1000 / SPEED) {
                    if (!gameOver) {
                        gameOver = Updater.update(gc, foodT, snakeL, wallT); // обновляем состояния змейки
                        oneEvent = true; // флаг, о том, что мы поменяли направление
                        // для многоразовых нажатий
                        Renderer.render(gc); // рисуем нашу змейку
                    } else {
                        break;
                    }
                    time = System.currentTimeMillis();
                }
            }
        });
        thread.start();
    }
}
