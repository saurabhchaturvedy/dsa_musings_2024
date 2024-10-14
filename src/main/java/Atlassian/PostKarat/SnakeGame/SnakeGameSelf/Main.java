package Atlassian.PostKarat.SnakeGame.SnakeGameSelf;

public class Main {


    public static void main(String[] args) {


        SnakeGameImpl snakeGame = new SnakeGameImpl(5, 5);


        System.out.println(" Initial Snake Position : " + snakeGame.getSnakePositions());

        moveSnake(snakeGame, Direction.DOWN, 1);
        moveSnake(snakeGame, Direction.LEFT, 1);
      //  moveSnake(snakeGame, Direction.UP, 1);


        if (snakeGame.isGameOver()) {

            System.out.println("Game Over !! Collision has occured");
        } else {

            System.out.println(" Current Snake Positions : " + snakeGame.getSnakePositions());
        }

    }


    private static void moveSnake(SnakeGameImpl snakeGame, Direction direction, int steps) {

        for (int i = 0; i < steps; i++) {

            snakeGame.moveSnake(direction);
            System.out.println(" Direction : " + direction + " " + snakeGame.getSnakePositions());

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
