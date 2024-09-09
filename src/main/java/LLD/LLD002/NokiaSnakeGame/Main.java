package LLD.LLD002.NokiaSnakeGame;

public class Main {


    public static void main(String[] args) {


        System.out.println("Going to start game");

        Cell initPos = new Cell(0, 0);
        Snake initSnake = new Snake(initPos);
        Board board = new Board(10, 10);
        SnakeGame newGame = new SnakeGame(board, initSnake);
        newGame.isGameOver = false;
        newGame.direction = Direction.RIGHT;


        for (int i = 0; i < 5; i++) {
            if (i == 2) newGame.board.generateFood();
            newGame.play();
            if (i == 3) newGame.direction = Direction.RIGHT;
            if (newGame.isGameOver == true) break;
        }

    }
}
