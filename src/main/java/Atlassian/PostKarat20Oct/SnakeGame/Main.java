package Atlassian.PostKarat20Oct.SnakeGame;

public class Main {


    public static void main(String[] args) {


        SnakeGameImpl snakeGame = new SnakeGameImpl(5, 5);

       // System.out.println(" Snake Directions : " + snakeGame.getSnakeDirections());

        snakeGame.publishBoard();

        System.out.println(" ******** Start Game *******");
       // moveSnake(snakeGame, Direction.UP, 2);
        moveSnake(snakeGame, Direction.RIGHT, 2);
        moveSnake(snakeGame, Direction.DOWN, 3);
        moveSnake(snakeGame, Direction.LEFT, 3);


        if (snakeGame.isGameOver()) {

            System.out.println("Snake has bit itself !!!");
            System.out.println(" ******** Game Over !! *******");
        } else {

            snakeGame.publishBoard();
        }
    }


    public static void moveSnake(SnakeGameImpl snakeGame, Direction direction, int steps) {

        for (int i = 0; i < steps; i++) {


            snakeGame.moveSnake(direction);
            snakeGame.publishBoard();
            System.out.println(" Direction = " + direction.name() + " : " + snakeGame.getSnakeDirections());
            System.out.println(" Total steps = "+snakeGame.steps);
            System.out.println(" Snake Size = " + snakeGame.getSnakeDirections().size());
        }
    }
}
