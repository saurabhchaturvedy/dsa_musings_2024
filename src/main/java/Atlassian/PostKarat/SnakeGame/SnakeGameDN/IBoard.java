package Atlassian.PostKarat.SnakeGame.SnakeGameDN;

public interface IBoard {
    int getSize();

    void displayBoard();

    /**
     * Add new head position when snake moves forward.
     * @param position New head position
     */
    void addPosition(Position position);

    /**
     * Delete last tail position when snake moves forward.
     * @param position Tail position
     */
    void deletePosition(Position position);

    void produceFood();

    boolean isFoodAvailable(Position position);
}