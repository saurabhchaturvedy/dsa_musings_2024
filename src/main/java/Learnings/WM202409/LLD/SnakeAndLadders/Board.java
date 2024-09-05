package Learnings.WM202409.LLD.SnakeAndLadders;

public class Board {


    Cell[] cells;

    int size;


    Board(int size) {

        this.size = size;
        this.cells = new Cell[size + 1];
        initializeBoard();
    }

    private void initializeBoard() {

        for (int i = 1; i < size; i++) {
            cells[i] = new Cell(i, i, CellType.NORMAL);
        }

        cells[size] = new Cell(size, size, CellType.TARGET);


        addLadder(new Ladder(15, 75));
        addSnake(new Snake(65, 10));


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
