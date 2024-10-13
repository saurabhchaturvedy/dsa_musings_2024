package Atlassian.PostKarat.SnakeGame.SnakeGameRND;// Snake.java
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<Cell> body;
    private Direction direction;

    public Snake(Cell initialPosition) {
        this.body = new ArrayList<>();
        body.add(initialPosition);
        this.direction = Direction.RIGHT; // Default initial direction
    }

    public void move() {
        Cell head = getHead();
        int newRow = head.getRow();
        int newCol = head.getCol();

        // Determine new head position based on the current direction
        switch (direction) {
            case UP:
                newRow--;
                break;
            case DOWN:
                newRow++;
                break;
            case LEFT:
                newCol--;
                break;
            case RIGHT:
                newCol++;
                break;
        }

        // Add new head position and remove the tail
        Cell newHead = new Cell(newRow, newCol);
        body.add(0, newHead);
        if (body.size() > 3) { // Assume initial size is 3
            body.remove(body.size() - 1);
        }
    }

    public void grow() {
        // Do not remove the last segment to grow the snake
        body.add(body.get(body.size() - 1)); // Repeat last segment to grow
    }

    public boolean checkCollisionWithItself() {
        Cell head = getHead();
        return body.subList(1, body.size()).contains(head); // Check if head collides with body
    }

    public Cell getHead() {
        return body.get(0);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public List<Cell> getBody() {
        return body;
    }
}
