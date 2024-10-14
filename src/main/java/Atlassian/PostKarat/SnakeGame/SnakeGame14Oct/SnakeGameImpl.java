package Atlassian.PostKarat.SnakeGame.SnakeGame14Oct;

import java.util.HashSet;
import java.util.LinkedList;

class SnakeGameImpl implements SnakeGame {
    private LinkedList<Cell> snake;  // List of snake positions (head is at the front)
    private HashSet<Cell> bodySet;   // Fast lookup for body collisions
    private int rows, cols;          // Dimensions of the board
    private Direction currentDirection;
    private int steps;               // Steps taken since the game started
    private boolean gameOver;
    
    public SnakeGameImpl(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.snake = new LinkedList<>();
        this.bodySet = new HashSet<>();
        this.steps = 0;
        this.gameOver = false;
        
        // Initialize the snake in the middle of the board with length 3
        int startRow = rows / 2;
        int startCol = cols / 2;
        for (int i = 0; i < 3; i++) {
            Cell cell = new Cell(startRow, startCol - i);  // Horizontal snake
            snake.addLast(cell);
            bodySet.add(cell);
        }
        
        this.currentDirection = Direction.RIGHT;  // Default direction
    }
    
    @Override
    public void moveSnake(Direction direction) {
        if (gameOver) return;
        
        // Get the current head position
        Cell head = snake.getFirst();
        int newRow = head.row;
        int newCol = head.col;
        
        // Update direction based on input
        if (direction != null) {
            currentDirection = direction;
        }
        
        // Move the head based on the direction
        switch (currentDirection) {
            case UP:    newRow--; break;
            case DOWN:  newRow++; break;
            case LEFT:  newCol--; break;
            case RIGHT: newCol++; break;
        }
        
        // Wrap around the board if the snake hits the wall
        if (newRow < 0) newRow = rows - 1;
        if (newRow >= rows) newRow = 0;
        if (newCol < 0) newCol = cols - 1;
        if (newCol >= cols) newCol = 0;
        
        Cell newHead = new Cell(newRow, newCol);
        
        // Check if the snake hits itself
        if (bodySet.contains(newHead)) {
            gameOver = true;
            return;
        }
        
        // Add new head to the snake
        snake.addFirst(newHead);
        bodySet.add(newHead);
        
        // Check if the snake should grow (every 5 steps)
        steps++;
        if (steps % 5 != 0) {
            // Remove the tail if not growing
            Cell tail = snake.removeLast();
            bodySet.remove(tail);
        }
    }
    
    @Override
    public boolean isGameOver() {
        return gameOver;
    }
    
    // Helper class to represent the cells occupied by the snake
    class Cell {
        int row, col;
        
        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cell cell = (Cell) o;
            return row == cell.row && col == cell.col;
        }
        
        @Override
        public int hashCode() {
            return 31 * row + col;
        }
        
        @Override
        public String toString() {
            return "(" + row + ", " + col + ")";
        }
    }
}
