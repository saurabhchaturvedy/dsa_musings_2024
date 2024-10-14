package Atlassian.PostKarat.SnakeGame.SnakeGameDN;

public interface SnakeGame {
    /**
     * Move the snake on the board based on user input.
     * @param direction Direction provided by the user.
     * @return true if the last move was successful.
     */
    boolean moveSnake(Direction direction);
    boolean isGameOver(Position position);
}
