package Atlassian.PostKarat.SnakeGame.SnakeGameRender;

interface SnakeGame {
    void setDirection(String snakeDirection);
    boolean isGameOver();
    void moveSnake();
    void render();  // Called from a separate thread, multiple times per second
}