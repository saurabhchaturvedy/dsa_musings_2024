package Atlassian.PostKarat.SnakeGame.SnakeGameSelfRender;


import Atlassian.PostKarat.SnakeGame.SnakeGameSelf.Direction;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class SnakeGameImpl implements SnakeGame {


    List<Cell> snake;

    String direction;

    boolean isGameOver;

    int boardWidth;

    int boardHeight;

    Cell foodPosition;

    Random random;

    ReentrantLock lock;


    SnakeGameImpl(int width, int height) {

        this.boardWidth = width;

        this.boardHeight = height;

        this.direction = "RIGHT";

        this.snake = new LinkedList<>();

        this.random = new Random();

        this.lock = new ReentrantLock();

        initializeSnake();
        placeFood();
    }

    private void initializeSnake() {

        int centerX = boardWidth / 2;
        int centerY = boardHeight / 2;

        for (int i = 0; i < 3; i++) {
            this.snake.add(new Cell(centerX - i, centerY));
        }
    }


    @Override
    public void setDirection(String direction) {


        lock.lock();

        try {


            if (isValidDirectionChange(direction)) {

                this.direction = direction;
            }

        } finally {
            lock.unlock();
        }

    }

    @Override
    public void moveSnake() {


        lock.lock();

        try {


            if (isGameOver) {

                return;
            }


            Cell newHead = getNextPosition();


            if (snake.contains(newHead)) {

                isGameOver = true;
                return;
            }


            snake.addFirst(newHead);


            if (snake.equals(foodPosition)) {


                placeFood();
            } else {


                snake.removeLast();
            }


        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isGameOver() {

        lock.lock();
        try {

            return isGameOver;
        } finally {
            lock.unlock();
        }

    }

    @Override
    public void render() {

        lock.lock();

        try {

            System.out.println(" Snake Positions : " + snake);
            System.out.println(" Food position : " + foodPosition);

        } finally {
            lock.unlock();
        }
    }


    public boolean isValidDirectionChange(String direction) {


        if (this.direction.equals("UP") && direction.equals("DOWN") ||
                this.direction.equals("DOWN") && direction.equals("UP")
                || this.direction.equals("LEFT") && direction.equals("RIGHT") || this.direction.equals("RIGHT") && direction.equals("LEFT")
        ) {

            return false;
        }

        return true;
    }


    public Cell getNextPosition() {


        Cell cell = snake.getFirst();

        int x = cell.getX();
        int y = cell.getY();

        switch (direction) {

            case "UP":
                x = (x - 1 + boardWidth) % boardWidth;
                break;

            case "DOWN":
                x = (x + 1) % boardWidth;
                break;

            case "LEFT":
                y = (y - 1 + boardHeight) % boardHeight;
                break;

            case "RIGHT":
                y = (y + 1) % boardHeight;
                break;

        }


        return new Cell(x, y);
    }


    public void placeFood() {

        lock.lock();

        try {

            while (true) {
                int x = random.nextInt(boardWidth);
                int y = random.nextInt(boardHeight);

                Cell newFood = new Cell(x, y);

                if (!snake.contains(newFood)) {

                    foodPosition = newFood;
                    break;

                }
            }
        } finally {
            lock.unlock();
        }
    }
}
