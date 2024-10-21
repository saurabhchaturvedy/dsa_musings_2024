package Atlassian.PostKarat20Oct.SnakeGame;

public class Main {


    public static void main(String[] args) {


        SnakeGameImpl snakeGame = new SnakeGameImpl(5, 5);

        System.out.println(" Snake Directions : " + snakeGame.getSnakeDirections());

        System.out.println(" ******** Start Game *******");
        moveSnake(snakeGame, Direction.RIGHT, 2);
        moveSnake(snakeGame, Direction.DOWN, 3);
        moveSnake(snakeGame, Direction.LEFT, 1);
        moveSnake(snakeGame, Direction.RIGHT, 1);


        if (snakeGame.isGameOver()) {

            System.out.println("Snake has bit itself !!!");
            System.out.println(" ******** Game Over !! *******");
        } else {

            System.out.println(" Snake Directions : " + snakeGame.getSnakeDirections());
        }
    }


    public static void moveSnake(SnakeGameImpl snakeGame, Direction direction, int steps) {

        for (int i = 0; i < steps; i++) {


            snakeGame.moveSnake(direction);
            System.out.println(" Direction = " + direction.name() + " : " + snakeGame.getSnakeDirections());
            System.out.println(" Snake Size = " + snakeGame.getSnakeDirections().size());
        }
    }
}
