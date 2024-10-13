package Atlassian.PostKarat.SnakeGame.SnakeGameRND;

// Board.java
public class Board {
    private final int width;
    private final int height;
    private Snake snake;
    private Food food;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.food = new Food();
        this.snake = new Snake(new Cell(height / 2, width / 2)); // Start in the middle of the board
        placeFood();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void placeFood() {
        food.placeRandomly(snake, this);
    }

    public Food getFood() {
        return food;
    }

    public Snake getSnake() {
        return snake;
    }

    public boolean isOutOfBounds(Cell cell) {
        return cell.getRow() < 0 || cell.getRow() >= height ||
               cell.getCol() < 0 || cell.getCol() >= width;
    }
}
