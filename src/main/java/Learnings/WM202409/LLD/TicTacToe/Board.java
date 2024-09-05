package Learnings.WM202409.LLD.TicTacToe;

public class Board {


    char[][] grid;
    int size;


    Board(int size) {

        this.size = size;
        this.grid = new char[size][size];
        initializeBoard();
    }

    private void initializeBoard() {

        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {

                grid[i][j] = '-';
            }
        }
    }


    public void publishBoard() {
        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {

                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

    }


    public boolean isFull() {

        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {

                if (grid[i][j] == '-') return false;
            }
        }

        return true;
    }


    public boolean isValidMove(int row, int col) {

        return (row >= 0 && col >= 0 && row < size && col < size && grid[row][col] == '-');
    }


    public void placeMove(Move move, char symbol) {

        grid[move.getRow()][move.getCol()] = symbol;
    }


    public boolean checkWin(Move move, char symbol) {

        int row = move.getRow();
        int col = move.getCol();


        boolean win = true;

        for (int i = 0; i < size; i++) {

            if (grid[row][i] != symbol) {

                win = false;
                break;
            }
        }

        if (win) return true;


        win = true;

        for (int i = 0; i < size; i++) {

            if (grid[i][col] != symbol) {

                win = false;
                break;
            }
        }

        if (win) return true;


        if (row == col) {

            win = true;

            for (int i = 0; i < size; i++) {

                if (grid[i][i] != symbol) {

                    win = false;
                    break;
                }
            }

            if (win) return true;
        }


        if (row + col == size - 1) {

            win = true;

            for (int i = 0; i < size; i++) {

                if (grid[i][size - i - 1] != symbol) {

                    win = false;
                    break;
                }
            }

            if (win) return true;
        }

        return false;
    }
}
