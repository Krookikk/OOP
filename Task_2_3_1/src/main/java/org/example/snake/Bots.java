package org.example.snake;

import java.util.List;
import java.util.Random;

import static org.example.snake.Game.*;
import static org.example.snake.Snake.*;
import static org.example.snake.Snake.TILE_SIZE_Y;

public class Bots {
    public static Game.Position generateBotPositionfirst(Game.Type[][] elements, List<Game.Position> forRandom) {
        Random rand = new Random();
        while (true) {
            int index = rand.nextInt(forRandom.size());
            Game.Position coordinate = forRandom.get(index);
            if (elements[coordinate.x][coordinate.y] == Game.Type.NOTHING) {
                elements[coordinate.x][coordinate.y] = Game.Type.BOT;
                forRandom.remove(index);
                return coordinate;
            } else {
                // Если координата занята, удаляем её из списка доступных координат
                forRandom.remove(index);
            }
        }
    }

    public static Game.Position directionBotHead(Game.Position head) {
        Random rand = new Random();

        int index = rand.nextInt(4);
        Game.Direction[] directions = Game.Direction.values();
        Game.Direction newDirections = directions[index];
        switch (newDirections) {
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

    public static boolean editBot(Game.Position newHeadBot, int WIDTH, int HEIGHT, int numBot, int foodT) {
        if (newHeadBot.x < 0 || newHeadBot.x >= WIDTH / TILE_SIZE_X || newHeadBot.y < 0 || newHeadBot.y >= HEIGHT / TILE_SIZE_Y) {
            // условие где бот убивается о внешнюю стену, выход за массив
            return false;
        }

        if (elements[newHeadBot.x][newHeadBot.y] == Game.Type.FOOD || elements[newHeadBot.x][newHeadBot.y] == Game.Type.NOTHING) {
            bots.get(numBot).add(0, newHeadBot);
            forRandom.remove(newHeadBot);

            if (elements[newHeadBot.x][newHeadBot.y] == Type.FOOD) {  // если поели
                if (WIDTH * HEIGHT / (TILE_SIZE_X * TILE_SIZE_Y) - snake.size() - foodT > 0) { //есть место для еды
                    Food.generateFoodPosition(elements, forRandom);
                }
            } else if (elements[newHeadBot.x][newHeadBot.y] == Type.NOTHING) {
                Position tail = bots.get(numBot).get(bots.get(numBot).size() - 1);
                forRandom.add(tail);
                elements[tail.x][tail.y] = Type.NOTHING;
                bots.get(numBot).remove(bots.get(numBot).size() - 1); // тогда удаляем хвост
            } else {
                return true;
            }

            elements[newHeadBot.x][newHeadBot.y] = Type.BOT; // значение новой головы меняем на значение бота
            return true;
        } else {
            return false;
        }


    }
}
