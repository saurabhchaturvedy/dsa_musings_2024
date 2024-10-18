package Atlassian.PostKarat18Oct.SnakeGame;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SnakeGameImpl implements SnakeGame {


    LinkedList<Cell> snake;
    int steps;
    int boardWidth;
    int boardHeight;
    boolean isGameOver;
    Random random;
    Cell foodCell;


    SnakeGameImpl(int boardWidth, int boardHeight) {

        this.snake = new LinkedList<>();
        this.snake.add(new Cell(0, 0));
        this.snake.add(new Cell(0, 1));
        this.snake.add(new Cell(0, 2));

        this.steps = 0;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.random = new Random();

        this.isGameOver = false;
        placeFood();

        //  foodCell = new Cell(1, 3);


    }

    @Override
    public void moveSnake(Direction direction) {


        if (isGameOver) {
            return;
        }


        Cell newHead = getNextCell(direction);

        if (snake.contains(newHead)) {

            isGameOver = true;
            return;
        }


        snake.add(newHead);
        steps++;


        if (this.snake.getLast().equals(foodCell)) {


        } else {

            snake.removeFirst();
        }
    }


    public Cell getNextCell(Direction direction) {

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
            case Direction.RIGHT:
                y = (y + 1) % boardHeight;
                break;
            case Direction.LEFT:
                y = (y - 1 + boardHeight) % boardHeight;
                break;


        }

        return new Cell(x, y);
    }

    @Override
    public boolean isGameOver() {
        return this.isGameOver;
    }

    public List<Cell> getSnakeDirections() {
        return this.snake;
    }


    public void placeFood() {

        while (true) {

            int x = this.random.nextInt(boardWidth);
            int y = this.random.nextInt(boardHeight);

            Cell newFoodCell = new Cell(x, y);

            if (!snake.contains(newFoodCell)) {

                foodCell = newFoodCell;
                break;
            }
        }
    }

    public Cell getFoodCell() {
        return foodCell;
    }
}
