package Grid;

public class NumberOfIslands {


    public int numIslands(char[][] grid) {

        if (grid == null || grid.length == 0) {
            return 0;
        }


        int numOfIslands = 0;


        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                if(grid[i][j]=='1') {
                    numOfIslands++;
                    dfs(grid, i, j);
                }
            }
        }

        return numOfIslands;
    }

    private void dfs(char[][] grid, int row, int col) {


        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col]=='0') {
            return;
        }


        grid[row][col] = '0';

        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row - 1, col);
        dfs(grid, row, col + 1);


    }


    public static void main(String[] args) {



        char[][]grid = {

                {'1','1','0','0','0'},
                {'1','1','0','1','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };


        NumberOfIslands numberOfIslands = new NumberOfIslands();

        int numIslands = numberOfIslands.numIslands(grid);

        System.out.println(" Number of islands : "+numIslands);

    }
}
