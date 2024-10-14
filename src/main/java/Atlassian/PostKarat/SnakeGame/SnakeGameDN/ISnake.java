package Atlassian.PostKarat.SnakeGame.SnakeGameDN;

public interface ISnake {
    Position getHead();
    Position getTail();
    int getSize();
    Direction getDirection();

    /**
     * Move the snake head to a new position.
     * @param newPosition New position.
     * @param direction New direction.
     */
    void moveHeadToNewPosition(Position newPosition, Direction direction);

    /**
     * Delete the tail of the snake and make the second last position the tail.
     */
    void deleteTail();

    void setSize(int size);
}
