package Atlassian.PostKarat.SnakeGame.SnakeGameRender;

public class SnakeGameParallelDemo {
    public static void main(String[] args) {
        SnakeGame snakeGame = new SnakeGameImpl(5, 5);

        // Thread for game logic (moving the snake)
        Thread gameThread = new Thread(() -> {
            while (!snakeGame.isGameOver()) {
                snakeGame.moveSnake();
                try {
                    Thread.sleep(1000);  // Move the snake every second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Thread for rendering the game state
        Thread renderThread = new Thread(() -> {
            while (!snakeGame.isGameOver()) {
                snakeGame.render();
                try {
                    Thread.sleep(500);  // Render the game every half second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Start both threads
        gameThread.start();
        renderThread.start();

        try {
            gameThread.join();
            renderThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
