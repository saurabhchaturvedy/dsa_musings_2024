package Atlassian.PostKarat18Oct.SnakeGame;

public class Main {


    public static void main(String[] args) {


        SnakeGameImpl snakeGame = new SnakeGameImpl(5, 5);


        System.out.println(" Initial Snake Positions : " + snakeGame.getSnakeDirections());


        moveSnake(snakeGame, Direction.LEFT, 1);
        moveSnake(snakeGame, Direction.DOWN, 1);
        moveSnake(snakeGame, Direction.DOWN, 1);

        if (snakeGame.isGameOver()) {

            System.out.println(" Game Over !!! Collision has occured");
        } else {

            System.out.println(" Initial Snake Positions : " + snakeGame.getSnakeDirections());
        }

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
