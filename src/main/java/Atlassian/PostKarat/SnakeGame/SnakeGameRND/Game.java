package Atlassian.PostKarat.SnakeGame.SnakeGameRND;// Game.java
import java.util.Scanner;

public class Game {
    private final Board board;
    private Status status;
    private final Scanner scanner;

    public Game(int width, int height) {
        this.board = new Board(width, height);
        this.status = Status.IN_PROGRESS;
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
        while (status == Status.IN_PROGRESS) {
            // Move the snake based on user input
            getUserInput();
            board.getSnake().move();
            checkGameOver();
            if (board.getSnake().getHead().equals(board.getFood().getPosition())) {
                board.getSnake().grow();
                board.placeFood();
            }
            render();
        }
    }

    private void getUserInput() {
        System.out.print("Enter direction (W/A/S/D): ");
        String input = scanner.nextLine().toUpperCase();
        switch (input) {
            case "W":
                board.getSnake().setDirection(Direction.UP);
                break;
            case "S":
                board.getSnake().setDirection(Direction.DOWN);
                break;
            case "A":
                board.getSnake().setDirection(Direction.LEFT);
                break;
            case "D":
                board.getSnake().setDirection(Direction.RIGHT);
                break;
            default:
                System.out.println("Invalid input! Please use W/A/S/D.");
        }
    }

    public void checkGameOver() {
        if (board.isOutOfBounds(board.getSnake().getHead()) ||
                board.getSnake().checkCollisionWithItself()) {
            status = Status.GAME_OVER;
            System.out.println("Game Over!");
        }
    }

    public void render() {
        // Simple console rendering of the board
        for (int row = 0; row < board.getHeight(); row++) {
            for (int col = 0; col < board.getWidth(); col++) {
                Cell cell = new Cell(row, col);
                if (board.getSnake().getBody().contains(cell)) {
                    System.out.print("S "); // Snake
                } else if (cell.equals(board.getFood().getPosition())) {
                    System.out.print("F "); // Food
                } else {
                    System.out.print(". "); // Empty Cell
                }
            }
            System.out.println();
        }
        System.out.println("Snake Length: " + board.getSnake().getBody().size());
    }
}
