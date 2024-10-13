package Atlassian.PostKarat.SnakeGame.SnakeGameRND;

import java.util.LinkedList;

public class Snake {
    private LinkedList<Point> body;  // Represents the snake's body as a list of points
    private Direction currentDirection;

    public Snake(Point start) {
        body = new LinkedList<>();
        body.add(start);  // Snake starts with one segment
        currentDirection = Direction.RIGHT;
    }

    public LinkedList<Point> getBody() {
        return body;
    }

    public void setDirection(Direction newDirection) {
        if (!currentDirection.isOpposite(newDirection)) {
            currentDirection = newDirection;
        }
    }

    public void move() {
        Point head = body.getFirst();
        Point newHead = head.move(currentDirection);

        // Add new head position
        body.addFirst(newHead);

        // Remove tail unless growing (which would be handled in GameController)
        body.removeLast();
    }

    public void grow() {
        // Add a new segment without removing the tail
        Point head = body.getFirst();
        Point newHead = head.move(currentDirection);
        body.addFirst(newHead);
    }

    public boolean checkCollisionWithSelf() {
        Point head = body.getFirst();
        return body.subList(1, body.size()).contains(head);  // Check if the head collides with any body segment
    }
}
