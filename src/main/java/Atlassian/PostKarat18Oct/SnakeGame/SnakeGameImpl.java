package Atlassian.PostKarat18Oct.SnakeGame;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class SnakeGameImpl implements SnakeGame {


    LinkedList<Cell> snake;
    int steps;
    Direction direction;
    int boardWidth;
    int boardHeight;
    boolean isGameOver;
    Random random;
    Cell foodCell;
    ReentrantLock lock;


    SnakeGameImpl(int boardWidth, int boardHeight) {

        this.snake = new LinkedList<>();
//        this.snake.add(new Cell(0, 0));
//        this.snake.add(new Cell(0, 1));
//        this.snake.add(new Cell(0, 2));

        this.steps = 0;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.random = new Random();
        this.direction = Direction.RIGHT;

        this.isGameOver = false;
        this.lock = new ReentrantLock();

        initializeSnake();
        placeFood();

        //  foodCell = new Cell(1, 3);


    }

    private void initializeSnake() {

        this.snake.add(new Cell(0, 0));
        this.snake.add(new Cell(0, 1));
        this.snake.add(new Cell(0, 2));
//        int x = boardWidth / 2;
//        int y = boardHeight / 2;
//
//        for (int i = 0; i < 3; i++) {
//            this.snake.add(new Cell(x - 1, y));
//        }
    }

    @Override
    public void moveSnake(Direction direction) {

        lock.lock();
        try {


            if (isGameOver) {
                return;
            }

            if (!isValidDirectionChange(direction)) {
                System.out.println("Invalid Direction, Snake cannot move into itself");
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
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void render() {

        lock.lock();
        try {


            System.out.println(" Direction : " + direction.name() + " => " + this.getSnakeDirections());
            System.out.println(" Snake Length: " + this.getSnakeDirections().size());
            System.out.println(" Current Food Position : " + this.getFoodCell());
        } finally {
            lock.unlock();
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

        lock.lock();
        try {


            return this.isGameOver;
        } finally {
            lock.unlock();
        }
    }

    public List<Cell> getSnakeDirections() {
        return this.snake;
    }


    public void placeFood() {

        lock.lock();
        try {


            while (true) {

                int x = this.random.nextInt(boardWidth);
                int y = this.random.nextInt(boardHeight);

                Cell newFoodCell = new Cell(x, y);

                if (!snake.contains(newFoodCell)) {

                    foodCell = newFoodCell;
                    break;
                }
            }
        } finally {
            lock.unlock();
        }
    }


    public boolean isValidDirectionChange(Direction direction) {


        if (this.direction.equals(Direction.UP) && direction.equals(Direction.DOWN) ||
                this.direction.equals(Direction.DOWN) && direction.equals(Direction.UP)
                || this.direction.equals(Direction.LEFT) && direction.equals(Direction.RIGHT) || this.direction.equals(Direction.RIGHT) && direction.equals(Direction.LEFT)
        ) {

            return false;
        }

        return true;
    }

    public Cell getFoodCell() {
        return foodCell;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
