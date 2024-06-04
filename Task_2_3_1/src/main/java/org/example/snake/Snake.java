package org.example.snake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import java.util.Scanner;

public class Snake extends Application {
    public static int TILE_SIZE_X = 20;
    public static int TILE_SIZE_Y = 20;
    public static int WIDTH;
    public static int HEIGHT;
    private int wid1;
    private int hei1;

    @Override
    public void start(Stage primaryStage) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the width of the field");
        WIDTH = scanner.nextInt() / TILE_SIZE_X * TILE_SIZE_X;
        WIDTH = Math.min(WIDTH, 1000);
        System.out.println("Write the height of the field");
        HEIGHT = scanner.nextInt() / TILE_SIZE_Y * TILE_SIZE_Y;
        HEIGHT = Math.min(HEIGHT, 800);

        System.out.println("Write down the amount of food");
        int foodT = scanner.nextInt();
        // сделала условие, что максимум может быть изначально сгенерировано еды не более четверти от поля
        foodT = Math.min(foodT, WIDTH * HEIGHT / (TILE_SIZE_Y * TILE_SIZE_X) / 4);

        System.out.println("Write the length of the snake to win");
        int snakeL = scanner.nextInt();
        // кол-во клеточек на поле это верхняя граница размера змейки для выигрыша
        snakeL = Math.min(Math.max(1, snakeL), WIDTH * HEIGHT / (TILE_SIZE_Y * TILE_SIZE_X));

        System.out.println("Write down the amount of walls");
        int wallT = scanner.nextInt();
        // сделала условие, что максимум может быть изначально сгенерировано стен не более четверти от поля
        wallT = Math.min(wallT, WIDTH * HEIGHT / (TILE_SIZE_Y * TILE_SIZE_X) / 4);

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Scene scene = GameScene.getScene(canvas);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake Game");
        primaryStage.show();

        wid1 = WIDTH / TILE_SIZE_X;
        hei1 = HEIGHT / TILE_SIZE_Y;

        primaryStage.widthProperty().addListener((a, oldW, newW) -> {
            WIDTH = newW.intValue();
            TILE_SIZE_X = WIDTH / wid1;
            WIDTH = (WIDTH / TILE_SIZE_X * TILE_SIZE_X);

        });

        primaryStage.heightProperty().addListener((a, oldH, newH) -> {
            HEIGHT = (newH.intValue());
            TILE_SIZE_Y = HEIGHT / hei1;
            HEIGHT = (HEIGHT / TILE_SIZE_Y * TILE_SIZE_Y);
        });

        Game game = new Game(gc, foodT, snakeL, wallT);
        game.startGame();



    }
}
