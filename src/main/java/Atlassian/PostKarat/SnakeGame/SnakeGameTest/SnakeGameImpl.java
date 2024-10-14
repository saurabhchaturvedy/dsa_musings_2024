package Atlassian.PostKarat.SnakeGame.SnakeGameTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SnakeGameImpl implements SnakeGame {
    private List<Cell> snake;
    private Direction direction;
    private int steps;
    private boolean gameOver;
    private int boardWidth;
    private int boardHeight;

    public SnakeGameImpl(int boardWidth, int boardHeight) {
        this.snake = new ArrayList<>();
        this.snake.add(new Cell(0, 0));
        this.snake.add(new Cell(0, 1));
        this.snake.add(new Cell(0, 2));
        this.snake.add(new Cell(0,3));
        this.snake.add(new Cell(0,4));
        this.direction = Direction.RIGHT; // Initial direction
        this.steps = 0;
        this.gameOver = false;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }

    @Override
    public void setDirection(Direction snakeDirection) {
        this.direction = snakeDirection;
    }

    @Override
    public void moveSnake() {
        if (gameOver) return;

        // Get the head of the snake
        Cell head = snake.get(snake.size() - 1);
        int newX = head.getX();
        int newY = head.getY();

        // Determine new head position based on direction
        switch (direction.name()) {
            case "UP":
                newX--;
                break;
            case "DOWN":
                newX++;
                break;
            case "LEFT":
                newY--;
                break;
            case "RIGHT":
                newY++;
                break;
        }

        // Wrap around the board
        if (newX < 0) newX = boardHeight - 1;
        if (newX >= boardHeight) newX = 0;
        if (newY < 0) newY = boardWidth - 1;
        if (newY >= boardWidth) newY = 0;

        // Check for self-collision
        Cell newHead = new Cell(newX, newY);
        if (snake.contains(newHead)) {
            gameOver = true;
            return;
        }

        // Add the new head
        snake.add(newHead);
        steps++;

        // Check for growth
        if (steps % 5 == 0) {
            // Grow the snake by adding a segment at the end
            // No action needed for growth as the snake naturally extends
        } else {
            // Remove the tail segment
            snake.remove(0);
        }
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public List<Cell> getSnakePositions() {
        return snake;
    }
}
