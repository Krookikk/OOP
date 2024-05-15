package org.example.snake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import java.util.Scanner;

public class Snake extends Application {
    private static final int TILE_SIZE = 20;
    private int snakeL;
    private GraphicsContext gc;
    private int foodT;


    @Override
    public void start(Stage primaryStage) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the width of the field");
        int WIDTH = scanner.nextInt() / TILE_SIZE * TILE_SIZE;
        WIDTH = Math.min(WIDTH, 1000);
        System.out.println("Write the height of the field");
        int HEIGHT = scanner.nextInt() / TILE_SIZE * TILE_SIZE;
        HEIGHT = Math.min(HEIGHT, 800);

        System.out.println("Write down the amount of food");
        foodT = scanner.nextInt();
        // сделала условие, что максимум может быть изначально сгенерировано еды не более четверти от поля
        foodT = Math.min(foodT, WIDTH * HEIGHT / (TILE_SIZE * TILE_SIZE) / 4);

        System.out.println("Write the length of the snake to win");
        snakeL = scanner.nextInt();
        // кол-во клеточек на поле это верхняя граница размера змейки для выигрыша
        snakeL = Math.min(Math.max(1, snakeL), WIDTH * HEIGHT / (TILE_SIZE * TILE_SIZE));

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        Scene scene = Game.getScene(canvas);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake Game");
        primaryStage.show();

        Game game = new Game(gc, HEIGHT, WIDTH, foodT, snakeL);
        game.startGame();
    }


}
