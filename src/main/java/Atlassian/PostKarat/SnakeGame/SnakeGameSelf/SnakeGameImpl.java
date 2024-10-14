package Atlassian.PostKarat.SnakeGame.SnakeGameSelf;

import java.util.ArrayList;
import java.util.List;

public class SnakeGameImpl implements SnakeGame {


    List<Cell> snake;
    int steps;
    boolean isGameOver;
    int boardWidth;
    int boardHeight;


    SnakeGameImpl(int boardWidth, int boardHeight) {

        this.snake = new ArrayList<>();
        this.snake.add(new Cell(0, 0));
        this.snake.add(new Cell(0, 1));
        this.snake.add(new Cell(0, 2));
        this.snake.add(new Cell(0, 3));
        this.snake.add(new Cell(0, 4));

        this.steps = 0;
        this.isGameOver = false;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }


    @Override
    public void moveSnake(Direction direction) {


        if (isGameOver) {

            return;
        }


        Cell head = snake.getLast();

        int newX = head.getX();
        int newY = head.getY();

        switch (direction) {

            case Direction.UP:
                newX--;
                break;
            case Direction.DOWN:
                newX++;
                break;
            case Direction.LEFT:
                newY--;
                break;
            case Direction.RIGHT:
                newY++;
                break;
        }


        if (newX < 0) newX = boardHeight - 1;
        if (newX >= boardHeight) newX = 0;
        if (newY < 0) newY = boardWidth - 1;
        if (newY >= boardWidth) newY = 0;


        Cell newHead = new Cell(newX, newY);

        if (snake.contains(newHead)) {

            isGameOver = true;
            return;
        }


        snake.add(newHead);
        steps++;


        if (steps % 5 == 0) {


        } else {

            snake.removeFirst();
        }
    }

    @Override
    public boolean isGameOver() {
        return isGameOver;
    }


    public List<Cell> getSnakePositions() {
        return snake;
    }
}
