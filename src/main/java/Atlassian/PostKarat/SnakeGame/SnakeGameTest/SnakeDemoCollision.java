package Atlassian.PostKarat.SnakeGame.SnakeGameTest;

import java.util.List;

public class SnakeDemoCollision {
    public static void main(String[] args) {
        // Create a Snake game with a board size of 5x5
        SnakeGame snakeGame = new SnakeGameImpl(5, 5);
        
        // Display initial snake positions
        System.out.println("Initial Snake Positions: " + snakeGame.getSnakePositions());

        // Simulate game moves
        snakeGame.setDirection(Direction.DOWN);
        moveSnake(snakeGame, 1); // Move snake to the right for 3 steps

        snakeGame.setDirection(Direction.LEFT);
        moveSnake(snakeGame, 1); // Move snake down for 3 steps

        snakeGame.setDirection(Direction.UP);
        moveSnake(snakeGame, 1); // Move snake to the left for 3 steps

       snakeGame.setDirection(Direction.UP);
        moveSnake(snakeGame, 1); // Move snake up for 1 step to collide

        // Check if the game is over
        if (snakeGame.isGameOver()) {
            System.out.println("Game Over! The snake collided with itself.");

        } else {
            System.out.println("Current Snake Positions: " + snakeGame.getSnakePositions());
        }
    }

    private static void moveSnake(SnakeGame snakeGame, int steps) {
        for (int i = 0; i < steps; i++) {
            snakeGame.moveSnake();
            System.out.println("Step " + (i + 1) + ": " + snakeGame.getSnakePositions());
            System.out.println("Current Size of the Snake : "+snakeGame.getSnakePositions().size());

            // Introduce a slight pause to simulate time passing (not necessary for logic)
            try {
                Thread.sleep(500); // Sleep for 500 milliseconds (0.5 seconds)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
            }
        }
    }
}
