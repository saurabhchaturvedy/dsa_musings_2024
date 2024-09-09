package LLD.LLD002.NokiaSnakeGame;

public class SnakeGame {




    Snake snake;
    Board board;

    Direction direction;
    boolean isGameOver;


    SnakeGame(Board board, Snake snake) {

        this.board = board;
        this.snake = snake;
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


        Cell cell = board.getCells()[row][col];

        return cell;
    }


    public void play() {


        if (!isGameOver) {


            if (direction == Direction.NONE) {

                Cell nextCell = this.getNextCell(snake.getHead());

                if (snake.hasCollision(nextCell)) {

                    System.out.println(" Snake had a collision !! GAME OVER");
                    direction = Direction.NONE;
                    isGameOver = true;
                } else {

                    snake.move(nextCell);

                    if (nextCell.cellType == CellType.FOOD) {

                        System.out.println(" Snake has grown after eating food");
                        snake.grow();
                        board.generateFood();
                    }
                }
            }
        }
    }
}
