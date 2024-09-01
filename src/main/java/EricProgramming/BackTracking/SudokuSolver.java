package EricProgramming.BackTracking;

public class SudokuSolver {


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    int printTimes = 30;
    char[][] board;

    public void solveSudoku(char[][] board) {

        this.board = board;
        dfs(board, 0, 0);
    }

    private boolean dfs(char[][] board, int row, int col) {


        if (col == 9) {
            row = row + 1;
            col = 0;
        }

        if (row == 9) {
            return true;
        }

        if (board[row][col] != '.') {
            return dfs(board, row, col + 1);
        }


        for (char i = '1'; i <= '9'; i++) {

            if (!isValid(row, col, i))
                continue;

            board[row][col] = i;
            printBoard("Make a decision", row, col);
            if (dfs(board, row, col + 1))
                return true;
            board[row][col] = '.';
            printBoard("Unchoose a decision", row, col);
        }

        return false;
    }

    private boolean isValid(int row, int col, int num) {


        for (int i = 0; i < 9; i++) {

            if (board[row][i] == num) return false;
            if (board[i][col] == num) return false;
        }


        int[] rowStartEnd = findBoxStartEnd(row);
        int[] colStartEnd = findBoxStartEnd(col);

        for (int i = rowStartEnd[0]; i <=rowStartEnd[1]; i++) {
            for (int j = colStartEnd[0]; j <= colStartEnd[1]; j++) {

                if (board[i][j] == num)
                    return false;
            }
        }

        return true;
    }

    private int[] findBoxStartEnd(int coord) {

        int[] result = new int[2];

        if (coord < 3) {
            result[1] = 2;
        } else if (coord < 6) {

            result[0] = 3;
            result[1] = 5;
        } else if (coord < 9) {

            result[0] = 6;
            result[1] = 8;

        }

        return result;
    }






    private void printBoard(String message, int row, int col) {
        if (printTimes == 0)
            return;
        printTimes--;
        System.out.println(message);
        System.out.println(ANSI_RED + "------------------" + ANSI_RESET);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == row && j == col) {
                    System.out.print(ANSI_RED + "" + board[i][j] + " " + ANSI_RESET);
                } else {
                    System.out.print(board[i][j] + " ");
                }
                if (j == 2 || j == 5) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {

        char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};


        SudokuSolver sudokuSolver = new SudokuSolver();

        sudokuSolver.solveSudoku(board);
    }
}
