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
import java.util.Scanner;

public class HelloApplication extends Application {
    protected static int WIDTH = 500; //
    public static int HEIGHT = 480; // y
    private static final int TILE_SIZE = 20;
    private static final int SPEED = 5;
    private boolean oneEvent = true;

    private List<Position> snake;
    private int snakeL;
    private List<Position> food;
    private GraphicsContext gc;


    private int foodT;
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the width of the field");
        WIDTH = scanner.nextInt() / 20 * 20;
        WIDTH = Math.min(WIDTH, 1000);
        System.out.println("Write the height of the field");
        HEIGHT = scanner.nextInt() / 20 * 20;
        HEIGHT = Math.min(HEIGHT, 800);

        System.out.println("Write down the amount of food");
        foodT = scanner.nextInt();
        foodT = Math.min(foodT, WIDTH * HEIGHT / (TILE_SIZE * TILE_SIZE) / 4);

        System.out.println("Write the length of the snake to win");
        snakeL = scanner.nextInt();
        snakeL = Math.min(snakeL, WIDTH * HEIGHT / (TILE_SIZE * TILE_SIZE));

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        Scene scene = getScene(canvas);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake Game");
        primaryStage.show();

        startGame(gc);
    }

    private Scene getScene(Canvas canvas) {
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
        return scene;
    }

    private GraphicsContext newGame(GraphicsContext gc) {
        Position foodValue;
        snake = new ArrayList<>();
        food = new ArrayList<>();
        snake.add(new Position(WIDTH / 20 / 2 * 20, HEIGHT / 20 / 2 * 20));
        for (int i = 0; i < foodT; i ++) {
            foodValue = generateFoodPosition();
            if (food.contains(foodValue) || snake.contains(foodValue)) {
                i --;
                continue;
            }
            food.add(foodValue);
        }
        direction = Direction.RIGHT;
        gameOver = false;

        return gc;
    }

    private void startGame(GraphicsContext gc) {
        newGame(gc);
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
        Position foodValue;
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

        if (food.contains(newHead)) {
            food.remove(newHead);

            while(true) {
                if (WIDTH * HEIGHT / (TILE_SIZE * TILE_SIZE) - snake.size() - foodT <= 0) {
                    break;
                }
                foodValue = generateFoodPosition();
                if (!(food.contains(foodValue) || snake.contains(foodValue))) {
                    food.add(foodValue);
                    break;
                }
            }

        } else {
            snake.remove(snake.size() - 1);
        }

        if (snake.size() >= snakeL) {
            newGame(gc);
        }

        oneEvent = true;
    }

    private void render(GraphicsContext gc) {
        gc.clearRect(0, 0, WIDTH, HEIGHT);

        gc.setFill(Color.RED);
        for (Position pos : food) {
            gc.fillRect(pos.x, pos.y, TILE_SIZE, TILE_SIZE);
        }


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
