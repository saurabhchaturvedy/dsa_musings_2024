package Atlassian.PostKarat.SnakeGame.SnakeGameRND;

import java.util.Random;

public class Board {
    private int width;
    private int height;
    private Snake snake;
    private Food food;
    private Random random;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        random = new Random();
    }

    public void initializeGame() {
        // Initialize snake at the center of the board
        snake = new Snake(new Point(width / 2, height / 2));
        spawnFood();
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }

    public void spawnFood() {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        food = new Food(new Point(x, y));
    }

    public boolean isFoodConsumed() {
        return snake.getBody().getFirst().equals(food.getLocation());
    }

    public boolean isOutOfBounds(Point point) {
        return point.getX() < 0 || point.getX() >= width || point.getY() < 0 || point.getY() >= height;
    }
}
