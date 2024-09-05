package Learnings.WM202409.LLD.NokiaSnakeGame;

public class SnakeGame {


    Snake snake;

    Board board;


    Direction direction;


    boolean isGameOver;


    SnakeGame(Snake snake, Board board) {

        this.snake = snake;
        this.board = board;
    }


    public Cell getNextCell(Cell nextCell) {

        int row = nextCell.getRow();
        int col = nextCell.getCol();

        if (direction == Direction.LEFT) {

            col--;
        }

        if (direction == Direction.RIGHT) {

            col++;
        }

        if (direction == Direction.UP) {

            row--;
        }

        if (direction == Direction.DOWN) {

            row++;
        }


        return board.getCells()[row][col];
    }


    public void play() {


        if (!isGameOver) {


            if (direction == Direction.NONE) {

                Cell nextCell = this.getNextCell(snake.getHead());

                if (snake.hasCollision(nextCell)) {

                    System.out.println(" collision has occured ... GAME OVER !!!");
                    direction = Direction.NONE;
                    isGameOver = true;
                } else {

                    snake.move(nextCell);

                    if (nextCell.cellType == CellType.FOOD) {

                        System.out.println(" Encountered a FOOD cell");
                        snake.grow();
                        board.generateFood();
                    }
                }
            }
        }
    }


    public static void main(String[] args) {


        System.out.println("Going to start game");

        Cell initPos = new Cell(0, 0);
        Snake initSnake = new Snake(initPos);
        Board board = new Board(10, 10);
        SnakeGame newGame = new SnakeGame(initSnake,board);
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
