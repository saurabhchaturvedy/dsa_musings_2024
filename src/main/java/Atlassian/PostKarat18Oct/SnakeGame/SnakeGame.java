package Atlassian.PostKarat18Oct.SnakeGame;

public interface SnakeGame {


    void moveSnake(Direction direction);

    boolean isGameOver();

    void render();
}
