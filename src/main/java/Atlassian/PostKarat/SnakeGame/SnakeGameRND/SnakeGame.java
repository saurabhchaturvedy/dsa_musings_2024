package Atlassian.PostKarat.SnakeGame.SnakeGameRND;

import java.util.Scanner;

public class SnakeGame {
    public static void main(String[] args) {
        // Initialize the game controller with a 10x10 board
        GameController game = new GameController(10, 10);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Snake Game! Use W (up), A (left), S (down), D (right) to move.");

        while (!game.isGameOver()) {
            // Display the current snake and food positions
            displayGameBoard(game);

            // Get input for direction
            char input = scanner.nextLine().toUpperCase().charAt(0);
            switch (input) {
                case 'W':
                    game.changeDirection(Direction.UP);
                    break;
                case 'A':
                    game.changeDirection(Direction.LEFT);
                    break;
                case 'S':
                    game.changeDirection(Direction.DOWN);
                    break;
                case 'D':
                    game.changeDirection(Direction.RIGHT);
                    break;
                default:
                    System.out.println("Invalid input! Use W, A, S, D.");
                    continue;
            }

            // Update the game state
            game.updateGame();
        }

        System.out.println("Game Over! Thanks for playing.");
        scanner.close();
    }

    private static void displayGameBoard(GameController game) {
        Board board = game.getBoard();
        Snake snake = board.getSnake();
        Food food = board.getFood();

        // Create a 10x10 board filled with dots
        char[][] boardDisplay = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boardDisplay[i][j] = '.';
            }
        }

        // Display the snake on the board
        for (Point segment : snake.getBody()) {
            boardDisplay[segment.getY()][segment.getX()] = 'S';
        }

        // Display the food on the board
        Point foodLocation = food.getLocation();
        boardDisplay[foodLocation.getY()][foodLocation.getX()] = 'F';

        // Print the board to the console
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(boardDisplay[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
