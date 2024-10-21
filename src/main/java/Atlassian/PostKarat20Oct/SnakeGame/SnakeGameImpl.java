package Atlassian.PostKarat20Oct.SnakeGame;

import java.util.LinkedList;
import java.util.List;

public class SnakeGameImpl implements SnakeGame {


    List<Cell> snake;
    int steps;
    int boardWidth;
    int boardHeight;
    boolean isGameOver;

    SnakeGameImpl(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.steps = 0;
        this.isGameOver = false;
        this.snake = new LinkedList<>();
        this.snake.add(new Cell(0, 0));
        this.snake.add(new Cell(0, 1));
        this.snake.add(new Cell(0, 2));
    }


    @Override
    public void moveSnake(Direction direction) {

        if (this.isGameOver) {
            return;
        }

        Cell newHead = getNextCell(direction);

        if (snake.contains(newHead)) {

            isGameOver = true;
            return;
        }


        snake.add(newHead);
        steps++;

        if (steps % 5 == 0) {

        } else {

            this.snake.removeFirst();
        }

    }

    private Cell getNextCell(Direction direction) {

        Cell cell = this.snake.getLast();

        int x = cell.getX();
        int y = cell.getY();

        switch (direction) {

            case Direction.UP:
                x = (x - 1 + boardWidth) % boardWidth;
                break;
            case Direction.DOWN:
                x = (x + 1) % boardWidth;
                break;
            case Direction.LEFT:
                y = (y - 1 + boardHeight) % boardHeight;
                break;
            case Direction.RIGHT:
                y = (y + 1) % boardHeight;
                break;
        }


        return new Cell(x, y);
    }

    @Override
    public boolean isGameOver() {
        return this.isGameOver;
    }

    public List<Cell> getSnakeDirections() {
        return snake;
    }
}
