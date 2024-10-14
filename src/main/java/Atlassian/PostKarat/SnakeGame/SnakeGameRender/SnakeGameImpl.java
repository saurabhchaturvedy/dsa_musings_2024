package Atlassian.PostKarat.SnakeGame.SnakeGameRender;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

class SnakeGameImpl implements SnakeGame {
    private List<Cell> snakePositions;
    private String currentDirection;
    private int width, height;
    private boolean gameOver;
    private Cell foodPosition;
    private Random random;
    private ReentrantLock lock;

    public SnakeGameImpl(int width, int height) {
        this.width = width;
        this.height = height;
        this.snakePositions = new LinkedList<>();
        this.currentDirection = "RIGHT"; // Default starting direction
        this.gameOver = false;
        this.random = new Random();
        this.lock = new ReentrantLock();
        
        // Initialize the snake with size 3
        initializeSnake();
        placeFood();  // Drop food at a random location
    }

    // Initialize snake at the center of the board
    private void initializeSnake() {
        int centerX = width / 2;
        int centerY = height / 2;
        for (int i = 0; i < 3; i++) {
            snakePositions.add(new Cell(centerX - i, centerY));
        }
    }

    @Override
    public void setDirection(String snakeDirection) {
        lock.lock();
        try {
            // Set the snake direction (make sure it can't reverse on itself)
            if (isValidDirectionChange(snakeDirection)) {
                this.currentDirection = snakeDirection;
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isGameOver() {
        lock.lock();
        try {
            return this.gameOver;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void moveSnake() {
        lock.lock();
        try {
            if (gameOver) {
                return;
            }

            // Calculate new head position based on current direction
            Cell newHead = getNextHeadPosition();
            
            // Check if the snake collides with itself
            if (snakePositions.contains(newHead)) {
                gameOver = true;
                return;
            }

            // Add new head and remove the tail
            snakePositions.add(0, newHead);
            
            // Check if the snake eats food
            if (newHead.equals(foodPosition)) {
                placeFood();  // Place new food after eating
            } else {
                snakePositions.remove(snakePositions.size() - 1);  // Normal movement, remove tail
            }

        } finally {
            lock.unlock();
        }
    }

    @Override
    public void render() {
        lock.lock();
        try {
            // Render the snake's positions and food
            System.out.println("Snake Positions: " + snakePositions);
            System.out.println("Food Position: " + foodPosition);
        } finally {
            lock.unlock();
        }
    }

    private boolean isValidDirectionChange(String newDirection) {
        // Ensure the snake cannot reverse on itself
        if (currentDirection.equals("UP") && newDirection.equals("DOWN") ||
            currentDirection.equals("DOWN") && newDirection.equals("UP") ||
            currentDirection.equals("LEFT") && newDirection.equals("RIGHT") ||
            currentDirection.equals("RIGHT") && newDirection.equals("LEFT")) {
            return false;
        }
        return true;
    }

    private Cell getNextHeadPosition() {
        Cell currentHead = snakePositions.get(0);
        int x = currentHead.getX();
        int y = currentHead.getY();

        switch (currentDirection) {
            case "LEFT":
                y = (y - 1 + height) % height;
                break;
            case "RIGHT":
                y = (y + 1) % height;
                break;
            case "UP":
                x = (x - 1 + width) % width;
                break;
            case "DOWN":
                x = (x + 1) % width;
                break;
        }
        return new Cell(x, y);
    }

    private void placeFood() {
        lock.lock();
        try {
            while (true) {
                int x = random.nextInt(width);
                int y = random.nextInt(height);
                Cell newFood = new Cell(x, y);
                if (!snakePositions.contains(newFood)) {
                    foodPosition = newFood;
                    break;
                }
            }
        } finally {
            lock.unlock();
        }
    }
}