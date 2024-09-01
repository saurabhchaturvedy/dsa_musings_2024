package Learnings.WM202409;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ZeroOneMatrix {


    public static int[][] updateMatrix(int[][] mat) {

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m = mat.length;
        int n = mat[0].length;
        int[][] result = new int[m][n];


        Queue<int[]> queue = new LinkedList<>();


        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                if (mat[i][j] == 0) {

                    result[i][j] = 0;
                    queue.offer(new int[]{i, j});
                } else {

                    result[i][j] = -1;
                }
            }
        }


        while (!queue.isEmpty()) {


            int[] coord = queue.poll();

            int x = coord[0];
            int y = coord[1];


            for (int[] dir : directions) {

                int new_x = x + dir[0];
                int new_y = y + dir[1];

                while (new_x >= 0 && new_x < m && new_y >= 0 && new_y < n && result[new_x][new_y] == -1) {

                    result[new_x][new_y] = result[x][y] + 1;
                    queue.offer(new int[]{new_x, new_y});
                }
            }
        }

        return result;
    }


    public static void main(String[] args) {


        int[][] matrix = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};


        int[][] result = updateMatrix(matrix);

        Arrays.stream(result).forEach(row -> {

            Arrays.stream(row).forEach(x -> {

                System.out.print(x + " ");
            });
            System.out.println();
        });
    }
}
