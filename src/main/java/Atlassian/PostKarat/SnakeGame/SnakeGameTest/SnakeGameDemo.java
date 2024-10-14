package Atlassian.PostKarat.SnakeGame.SnakeGameTest;

import java.util.List;

public class SnakeGameDemo {
    public static void main(String[] args) {
        // Create a Snake game with a board size of 5x5
        SnakeGame snakeGame = new SnakeGameImpl(5, 5);
        
        // Display initial snake positions
        System.out.println("Initial Snake Positions: " + snakeGame.getSnakePositions());

        // Simulate game moves
        snakeGame.setDirection(Direction.RIGHT);
        moveSnake(snakeGame, 10); // Move snake for 10 steps

     //   snakeGame.setDirection("DOWN");
    //    moveSnake(snakeGame, 10); // Move snake for another 10 steps

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

            // Introduce a slight pause to simulate time passing (not necessary for logic)
            try {
                Thread.sleep(500); // Sleep for 500 milliseconds (0.5 seconds)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
            }
        }
    }
}
