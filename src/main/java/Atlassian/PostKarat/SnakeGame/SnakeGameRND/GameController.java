package Atlassian.PostKarat.SnakeGame.SnakeGameRND;

public class GameController {
    private Board board;
    private boolean isGameOver;

    public GameController(int boardWidth, int boardHeight) {
        board = new Board(boardWidth, boardHeight);
        board.initializeGame();
        isGameOver = false;
    }

    public void changeDirection(Direction direction) {
        board.getSnake().setDirection(direction);
    }

    public void updateGame() {
        if (isGameOver) return;

        Snake snake = board.getSnake();
        snake.move();

        // Check collisions
        if (snake.checkCollisionWithSelf() || board.isOutOfBounds(snake.getBody().getFirst())) {
            isGameOver = true;
            System.out.println("Game Over!");
            return;
        }

        // Check if food is consumed
        if (board.isFoodConsumed()) {
            snake.grow();
            board.spawnFood();
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }


    public Board getBoard() {
        return board;
    }
}
