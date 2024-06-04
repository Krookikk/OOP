package org.example.snake;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;

public class GameScene {
    public static Scene getScene(Canvas canvas) {
        BorderPane root = new BorderPane(canvas);
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(event -> {
            if (Game.isOneEvent()) { // чтобы змейка в противоположную сторону не полетела
                if (event.getCode() == KeyCode.UP && Game.getDirection() != Game.Direction.DOWN) {
                    Game.setDirection(Game.Direction.UP);
                } else if (event.getCode() == KeyCode.DOWN && Game.getDirection() != Game.Direction.UP) {
                    Game.setDirection(Game.Direction.DOWN);
                } else if (event.getCode() == KeyCode.LEFT && Game.getDirection() != Game.Direction.RIGHT) {
                    Game.setDirection(Game.Direction.LEFT);
                } else if (event.getCode() == KeyCode.RIGHT && Game.getDirection() != Game.Direction.LEFT) {
                    Game.setDirection(Game.Direction.RIGHT);
                }
                Game.setOneEvent(false);
            }
        });
        return scene;
    }
}
