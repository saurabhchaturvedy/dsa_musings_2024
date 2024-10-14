package Atlassian.PostKarat.SnakeGame.SnakeGameSelfRender;

public interface SnakeGame {


    void setDirection(String direction);

    void moveSnake();

    boolean isGameOver();

    void render();
}
