package LLD.NokiaSnakeGame;

public class Board {


    Cell[][] cells;

    int row;
    int col;


    Board(int row, int col) {

        this.row = row;
        this.col = col;

        this.cells = new Cell[row][col];

        for (int i = 0; i < row; i++) {

            for (int j = 0; j < col; j++) {

                cells[i][j] = new Cell(i, j);
            }
        }
    }


    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public void generateFood() {

        System.out.println("************ going to generate food *********");

        int row = (int) (Math.random() * this.row);
        int col = (int) (Math.random() * this.col);

        cells[row][col].setCellType(CellType.FOOD);
        System.out.println(" **** Food is being generated at row = " + row + " col= " + col);
    }
}
