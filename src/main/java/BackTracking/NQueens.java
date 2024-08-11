package BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {


    List<List<String>> result = new ArrayList<>();


    public List<List<String>> nQueens(int n) {

        char[][] board = new char[n][n];

        for (char[] ch : board) {
            Arrays.fill(ch, '.');
        }
        dfs(board, 0, 0, n);
        return result;
    }

    private void dfs(char[][] board, int row, int col, int n) {


        if (col == board[0].length) {

            col = 0;
            row++;
        }


        if (n == 0) {

            result.add(prepareStringList(board));
            return;
        }


        if (row == board.length) {
            return;
        }


        if (isValidPosition(board, row, col)) {

            board[row][col] = 'Q';
            n--;
            dfs(board, row, col + 1, n);
            board[row][col] = '.';
            n++;

        }

        dfs(board, row, col + 1, n);
    }

    private boolean isValidPosition(char[][] board, int row, int col) {

        int N = board.length;

        for (int i = 0; i < N; i++) {

            if (board[row][i] != '.') return false;
            if (board[i][col] != '.') return false;
        }


        int r = row;
        int c = col;


        while (r >= 0 && c >= 0) {

            if (board[r][c] != '.') {
                return false;
            }

            r--;
            c--;
        }


        r = row;
        c = col;

        while (r >= 0 && c < N) {

            if (board[r][c] != '.') {
                return false;
            }

            r--;
            c++;
        }

        r = row;
        c = col;

        while (r < N && c < N) {

            if (board[r][c] != '.') {

                return false;
            }

            r++;
            c++;

        }

        r = row;
        c = col;

        while (r < N && c >= 0) {

            if (board[r][c] != '.') {
                return false;
            }

            r++;
            c--;
        }

        return true;

    }

    private List<String> prepareStringList(char[][] board) {

        List<String> boardPosition = new ArrayList<>();
        StringBuilder sb;
        for (char[] c : board) {
             sb = new StringBuilder();
            for (char cx : c) {

                sb.append(cx);
            }

            boardPosition.add(sb.toString());
        }

        return boardPosition;
    }


    public static void main(String[] args) {


        NQueens nQueens = new NQueens();

        List<List<String>> lists = nQueens.nQueens(4);

        System.out.println(lists);
    }
}
