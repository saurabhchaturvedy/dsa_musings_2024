package Atlassian.PostKarat.SnakeGame.SnakeGameDN;

import java.util.ArrayList;
import java.util.List;

public class Game implements SnakeGame {
    private final IBoard board;
    private final ISnake snake;
    private boolean isFoodProduced;
    private int moveCount; // Counter for moves

    public Game(IBoard board, ISnake snake) {
        this.board = board;
        this.snake = snake;
        this.board.produceFood();
        isFoodProduced = true;
        moveCount = 0; // Initialize move counter
    }

    @Override
    public boolean moveSnake(Direction direction) {
        if (!isFoodProduced) {
            board.produceFood();
            isFoodProduced = true;
        }

        Position startPosition = snake.getHead();
        Position endPosition = snake.getTail();
        Position newStartPosition = new Position(startPosition.getX(), startPosition.getY());

        switch (direction) {
            case RIGHT:
                newStartPosition.setY(newStartPosition.getY() + 1);
                break;
            case LEFT:
                newStartPosition.setY(newStartPosition.getY() - 1);
                break;
            case UP:
                newStartPosition.setX(newStartPosition.getX() - 1);
                break;
            case DOWN:
                newStartPosition.setX(newStartPosition.getX() + 1);
                break;
        }

// Wrap Around Logic
        if (newStartPosition.getX() >= board.getSize()) {
            newStartPosition.setX(0); // Wrap around to the top
        } else if (newStartPosition.getX() < 0) {
            newStartPosition.setX(board.getSize()-1);  // Wrap around to the bottom
        }
        if (newStartPosition.getY() >= board.getSize()) {
            newStartPosition.setY(0); // Wrap around to the left
        } else if (newStartPosition.getY() < 0) {
            newStartPosition.setY(board.getSize()-1);// Wrap around to the right
        }

        if (isGameOver(newStartPosition)) {

            snake.moveHeadToNewPosition(newStartPosition, direction);

            // Increment move count
            moveCount++;

            // Check if the snake should grow
            if (moveCount % 5 == 0) {
                // Snake grows by one segment (do not delete the tail)
                snake.setSize(snake.getSize() + 1);
            } else {
                // If current position doesn't have food, delete the tail
                if (!board.isFoodAvailable(newStartPosition)) {
                    snake.deleteTail();
                    board.deletePosition(endPosition);
                } else {
                    // Food is consumed
                    isFoodProduced = false;
                }
            }

            // Add new snake head position on board
            board.addPosition(newStartPosition);
            // Display updated board
            board.displayBoard();


            // Display the number of moves taken
            displayMoveCount();
            displaySnakeSize();

            return true;
        } else {
            System.out.println("Game Over!");
            return false;
        }
    }

    private void displayMoveCount() {
        System.out.println("Number of moves taken by the snake: " + moveCount);
    }
    private void displaySnakeSize() {
        System.out.println("Current size of the snake: " + (snake.getSize()));
    }

    public boolean isGameOver(Position newHeadPosition) {
        if (newHeadPosition.getX() > board.getSize() - 1 || newHeadPosition.getX() < 0 ||
            newHeadPosition.getY() > board.getSize() - 1 || newHeadPosition.getY() < 0) {
            return false;
        }

        Position temp = snake.getHead();
        while (temp != null) {
            if (temp.getX() == newHeadPosition.getX() && temp.getY() == newHeadPosition.getY()) {
                return false;
            }
            temp = temp.getNext();
        }

        return true;
    }
}
