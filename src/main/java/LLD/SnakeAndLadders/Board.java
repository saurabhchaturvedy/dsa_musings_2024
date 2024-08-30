package LLD.SnakeAndLadders;

public class Board {


    Cell[] cells;
    int size;


    Board(int size) {

        this.size = size;
        this.cells = new Cell[size + 1];
        initializeBoard();
    }

    private void initializeBoard() {

        for (int i = 1; i < cells.length; i++) {

            cells[i] = new Cell(i, i, CellType.NORMAL);
        }

        cells[size] = new Cell(size, size, CellType.TARGET);

        addSnake(new Snake(15, 75));
        addLadder(new Ladder(12, 56));

    }


    public void addSnake(Snake snake) {

        cells[snake.start] = snake;
    }


    public void addLadder(Ladder ladder) {

        cells[ladder.start] = ladder;
    }


    public Cell getCell(int pos) {

        return cells[pos];
    }
}
