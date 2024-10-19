package Atlassian.PostKarat18Oct.PostKaratSat19Oct;

import java.util.LinkedHashSet;
import java.util.Objects;

public class SnakeGame {


    private static class Pair {

        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;

        }

        public boolean equals(Object o) {

            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair p = (Pair) o;

            return this.x == p.x && this.y == p.y;
        }

        public int hashCode() {
            return Objects.hash(x, y);
        }
    }


    int width;
    int height;
    LinkedHashSet<Pair> snake;
    LinkedHashSet<Pair> food;
    int x;
    int y;


    SnakeGame(int x, int y, int[][] food) {

        this.width = x;
        this.height = y;
        this.snake = new LinkedHashSet<>();
        this.food = new LinkedHashSet<>();
        for (int i = 0; i < food.length; i++) {
            this.food.add(new Pair(food[i][0], food[i][1]));
        }
        this.snake.add(new Pair(0, 0));
        this.x = 0;
        this.y = 0;
    }


    public boolean validate(int x, int y) {

        if (snake.contains(new Pair(x, y))) return true;

        if (x < 0 || x >= width) return true;
        if (y < 0 || y >= height) return true;

        return false;
    }


    public void changeSnakeSize(int x, int y) {

        Pair foodd = this.food.stream().findFirst().get();

        if (foodd.equals(new Pair(x, y))) {

            snake.add(new Pair(x, y));
            food.remove(foodd);
        } else {

            snake.add(new Pair(x, y));

            Pair snake = this.snake.stream().findFirst().get();

            this.snake.remove(snake);
        }
    }


    public int move(Character c) {


        if (c.equals('U')) {
            if (validate(x - 1, y)) return -1;
            x--;
            changeSnakeSize(x, y);
        } else if (c.equals('D')) {

            if (validate(x + 1, y)) return -1;
            x++;
            changeSnakeSize(x, y);
        } else if (c.equals('R')) {

            if (validate(x, y + 1)) return -1;
            y++;
            changeSnakeSize(x, y);
        } else if (c.equals('L')) {

            if (validate(x, y - 1)) return -1;
            y--;
            changeSnakeSize(x, y);
        } else {

            System.out.println("Invalid input");
            return -1;
        }

        if (false) {

            for (Pair p : snake) {
                System.out.println(p.x + " " + p.y);
            }
        }

        return snake.size() - 1;
    }


    public static void main(String[] args) {


        SnakeGame snakeGame = new SnakeGame(2, 3, new int[][]{{1, 2}, {0, 1}});

        System.out.println(snakeGame.move('R'));
        System.out.println(snakeGame.move('D'));
        System.out.println(snakeGame.move('R'));
        System.out.println(snakeGame.move('U'));
        System.out.println(snakeGame.move('L'));
        System.out.println(snakeGame.move('U'));
    }
}
