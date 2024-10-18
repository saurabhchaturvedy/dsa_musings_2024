package Atlassian.PostKarat18Oct.SnakeGame;

import java.util.Random;

public class Main {


    public static void main(String[] args) {


        SnakeGameImpl snakeGame = new SnakeGameImpl(5, 5);


        System.out.println(" Initial Snake Positions : " + snakeGame.getSnakeDirections());


        Thread gameThread = new Thread(() -> {


            while (!snakeGame.isGameOver()) {

                snakeGame.moveSnake(getRandomDirection());


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

//        moveSnake(snakeGame, Direction.LEFT, 1);
//        moveSnake(snakeGame, Direction.DOWN, 1);
//        moveSnake(snakeGame, Direction.DOWN, 1);

        if (snakeGame.isGameOver()) {

            System.out.println(" Game Over !!! Collision has occured");
        } else {

            System.out.println(" Initial Snake Positions : " + snakeGame.getSnakeDirections());
        }

    }


    public static Direction getRandomDirection() {
        Direction[] directions = Direction.values(); // Get all enum values
        Random random = new Random();
        return directions[random.nextInt(directions.length)]; // Select a random one
    }


    public static void moveSnake(SnakeGameImpl snakeGame, Direction direction, int steps) {

        for (int i = 0; i < steps; i++) {

            snakeGame.moveSnake(direction);
            System.out.println(" Direction : " + direction.name() + " => " + snakeGame.getSnakeDirections());
            System.out.println(" Snake Length: " + snakeGame.getSnakeDirections().size());
            System.out.println(" Current Food Position : " + snakeGame.getFoodCell());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
