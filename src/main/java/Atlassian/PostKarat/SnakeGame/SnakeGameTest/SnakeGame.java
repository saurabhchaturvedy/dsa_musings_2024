package Atlassian.PostKarat.SnakeGame.SnakeGameTest;

import java.util.List;

public interface SnakeGame {
    void setDirection(Direction snakeDirection);
    void moveSnake();
    boolean isGameOver();
    List<Cell> getSnakePositions();
}
