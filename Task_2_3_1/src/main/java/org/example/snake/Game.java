package org.example.snake;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private boolean gameOver;
    private final int WIDTH;
    private final int HEIGHT;
    private final int snakeL;
    private static final int SPEED = 5;
    private static final int TILE_SIZE = 20;
    public static List<Position> foods;
    public static List<Position> snake;
    private final GraphicsContext gc;
    private final int foodT;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
    static Direction direction;
    private static boolean oneEvent = true;


    public Game(GraphicsContext gc, int HEIGHT, int WIDTH, int foodT, int snakeL) {
        this.gc = gc;
        this.HEIGHT = HEIGHT;
        this.WIDTH = WIDTH;
        this.foodT = foodT;
        this.snakeL = snakeL;
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

    public static Scene getScene(Canvas canvas) {
        BorderPane root = new BorderPane(canvas);
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(event -> {
            if (oneEvent) { // чтобы змейка в противоположную сторону не полетела
                if (event.getCode() == KeyCode.UP && direction != Direction.DOWN) {
                    direction = Direction.UP;
                    oneEvent = false;
                } else if (event.getCode() == KeyCode.DOWN && direction != Direction.UP) {
                    direction = Direction.DOWN;
                    oneEvent = false;
                } else if (event.getCode() == KeyCode.LEFT && direction != Direction.RIGHT) {
                    direction = Direction.LEFT;
                    oneEvent = false;
                } else if (event.getCode() == KeyCode.RIGHT && direction != Direction.LEFT) {
                    direction = Direction.RIGHT;
                    oneEvent = false;
                }
            }
        });
        return scene;
    }

    public void newGame() {
        Position foodValue;
        snake = new ArrayList<>();
        foods = new ArrayList<>();
        snake.add(new Position(WIDTH / 20 / 2 * 20, HEIGHT / 20 / 2 * 20)); // змейку на середину поля
        for (int i = 0; i < foodT; i ++) { // генерим заданное кол-во еды
            foodValue = Food.generateFoodPosition(WIDTH, HEIGHT, TILE_SIZE);
            if (foods.contains(foodValue) || snake.contains(foodValue)) {
                i --;
                continue;
            }
            foods.add(foodValue);
        }
        direction = Direction.RIGHT;
        gameOver = false;

    }

    public void startGame() {
        newGame();
        Thread thread = new Thread(() -> {
            while (true) { // обновление пять раз в секунду
                if (!gameOver) {
                    gameOver = Updater.update(gc, WIDTH, HEIGHT, TILE_SIZE, foodT, snakeL); // обновляем состояния змейки
                    oneEvent = true; // флаг, о том, что мы поменяли направление
                    // для многоразовых нажатий
                    Renderer.render(gc, WIDTH, HEIGHT, TILE_SIZE); // рисуем нашу змейку
                } else {
                    break;
                }

                try {
                    Thread.sleep(1000 / SPEED);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread.start();
    }
}
