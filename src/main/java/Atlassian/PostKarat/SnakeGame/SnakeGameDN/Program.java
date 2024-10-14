package Atlassian.PostKarat.SnakeGame.SnakeGameDN;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        int boardSize = 10;
        ISnake snake = new Snake(boardSize, Direction.RIGHT, 3);
        IBoard board = new Board(boardSize, snake.getHead());
        SnakeGame game = new Game(board, snake);

        board.displayBoard();
        boolean wasSuccess = true;
        Scanner scanner = new Scanner(System.in);

        while (wasSuccess) {
            // Simulate reading a key press (using Scanner for console input)
            System.out.print("Press arrow keys (or 'E' to exit): ");
            String input = scanner.nextLine().toUpperCase();

            // Clear the console (not directly possible in Java, but we can simulate it)
            System.out.print("\033[H\033[2J");  
            System.out.flush();

            if (input.equals("E")) {
                break;
            }

            Direction direction;
            switch (input) {
                case "RIGHT":
                    direction = Direction.RIGHT;
                    break;
                case "LEFT":
                    direction = Direction.LEFT;
                    break;
                case "UP":
                    direction = Direction.UP;
                    break;
                case "DOWN":
                    direction = Direction.DOWN;
                    break;
                default:
                    direction = snake.getDirection(); // default to current direction if invalid input
            }

            wasSuccess = game.moveSnake(direction);
        }

        board.displayBoard();
        scanner.close();
    }
}
