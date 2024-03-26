package org.example.snake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelloApplication extends Application {
    public static final int WIDTH = 600; //
    public static final int HEIGHT = 480; // y
    private static final int TILE_SIZE = 20;
    private static final int SPEED = 5;
    private static boolean oneEvent = true;

    private List<Position> snake;
    private Position food;
    private Direction direction;
    private boolean gameOver;

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
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

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        BorderPane root = new BorderPane(canvas);
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(event -> {
            if (oneEvent) {
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

        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake Game");
        primaryStage.show();

        startGame(gc);
    }

    private void startGame(GraphicsContext gc) {
        snake = new ArrayList<>();
        snake.add(new Position(WIDTH / 2, HEIGHT / 2));
        food = generateFoodPosition();
        direction = Direction.RIGHT;
        gameOver = false;

        new AnimationTimer() {
            long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 1000000000 / SPEED) {
                    lastUpdate = now;
                    if (!gameOver) {
                        update();
                        render(gc);
                        oneEvent = true;
                    } else {
                        stop();
                    }
                }
            }
        }.start();
    }

    private void update() {
        Position head = snake.get(0);
        Position newHead;
        switch (direction) {
            case UP:
                newHead = new Position(head.x, head.y - TILE_SIZE);
                break;
            case DOWN:
                newHead = new Position(head.x, head.y + TILE_SIZE);
                break;
            case LEFT:
                newHead = new Position(head.x - TILE_SIZE, head.y);
                break;
            case RIGHT:
                newHead = new Position(head.x + TILE_SIZE, head.y);
                break;
            default:
                newHead = new Position(head.x, head.y);
        }

        if (newHead.x < 0 || newHead.x >= WIDTH || newHead.y < 0 || newHead.y >= HEIGHT) {
            gameOver = true;
            return;
        }

        if (snake.contains(newHead)) {
            gameOver = true;
            return;
        }

        snake.add(0, newHead);
        if (newHead.x == food.x && newHead.y == food.y) {
            food = generateFoodPosition();
        } else {
            snake.remove(snake.size() - 1);
        }

        oneEvent = true;
    }

    private void render(GraphicsContext gc) {
        gc.clearRect(0, 0, WIDTH, HEIGHT);

        gc.setFill(Color.RED);
        gc.fillRect(food.x, food.y, TILE_SIZE, TILE_SIZE);

        gc.setFill(Color.GREEN);
        for (Position pos : snake) {
            gc.fillRect(pos.x, pos.y, TILE_SIZE, TILE_SIZE);
        }
    }

    public static Position generateFoodPosition() {
        Random rand = new Random();
        int x = rand.nextInt(WIDTH / TILE_SIZE) * TILE_SIZE;
        int y = rand.nextInt(HEIGHT / TILE_SIZE) * TILE_SIZE;
        return new Position(x, y);
    }

}
