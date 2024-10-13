package Atlassian.PostKarat.SnakeGame.SnakeGameRND;// Food.java
import java.util.Random;

public class Food {
    private Cell position;

    public void placeRandomly(Snake snake, Board board) {
        Random rand = new Random();
        Cell newPosition;
        do {
            int row = rand.nextInt(board.getHeight());
            int col = rand.nextInt(board.getWidth());
            newPosition = new Cell(row, col);
        } while (snake.getBody().contains(newPosition)); // Ensure food doesn't overlap with snake
        position = newPosition;
    }

    public Cell getPosition() {
        return position;
    }
}
