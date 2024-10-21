package Atlassian.PostKarat20Oct.SnakeGame;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SnakeGameImpl implements SnakeGame {


    List<Cell> snake;
    int steps;
    int boardWidth;
    int boardHeight;
    boolean isGameOver;
    char[][] board;
    Cell foodPosition;
    Random random;

    SnakeGameImpl(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.steps = 0;
        this.isGameOver = false;
        this.snake = new LinkedList<>();
        this.snake.add(new Cell(0, 0));
        this.snake.add(new Cell(0, 1));
        this.snake.add(new Cell(0, 2));
        this.board = new char[boardHeight][boardHeight];
        this.random = new Random();
        placeFood(3, 2);
        initializeBoard();

    }

    public void initializeBoard() {

        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {

                Cell cell = new Cell(i, j);
                if (!snake.contains(cell)) {
                    if (foodPosition.x == cell.x && foodPosition.y == cell.y) {
                        board[i][j] = 'F';
                    } else {
                        board[i][j] = '-';
                    }
                } else {

                    if (snake.getLast().x == cell.x && snake.getLast().y == cell.y) {
                        board[i][j] = '$';
                    } else {

                        board[i][j] = 'X';
                    }

                }

            }

        }
    }


    public void publishBoard() {

        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {

                System.out.print(board[i][j]);

            }
            System.out.println();
        }
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

        if (steps % 5 == 0 || snake.contains(foodPosition)) {

            if (snake.contains(foodPosition)) {
                placeFood(0, 4);
            }

        } else {

            this.snake.removeFirst();
        }
        this.initializeBoard();
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


    public void placeFood(int xx, int yy) {

        while (true) {

            int x = random.nextInt(this.boardWidth);
            int y = random.nextInt(this.boardHeight);

            Cell newFood = new Cell(xx, yy);

            if (!snake.contains(newFood)) {

                foodPosition = newFood;
                break;
            }
        }
    }
}
