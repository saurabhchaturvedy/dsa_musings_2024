package Atlassian.PostKarat.SnakeGame.SnakeGameSelfRender;

public class Main {


    public static void main(String[] args) {


        SnakeGame snakeGame = new SnakeGameImpl(5, 5);


        Thread gameThread = new Thread(() -> {


            while (!snakeGame.isGameOver()) {

                snakeGame.moveSnake();


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }


        });


        Thread renderThread = new Thread(() -> {

            while (!snakeGame.isGameOver()) {

                snakeGame.render();

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });


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
