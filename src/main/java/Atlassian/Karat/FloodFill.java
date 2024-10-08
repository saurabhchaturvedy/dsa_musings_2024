package Atlassian.Karat;

import java.util.Arrays;

public class FloodFill {


    public int[][] floodFill(int[][] image, int sr, int sc, int color) {

        if (color == image[sr][sc]) return image;
        dfs(image, sr, sc, color, image[sr][sc]);
        return image;
    }

    public void dfs(int[][] image, int sr, int sc, int newColor, int oldColor) {

        if (sr >= image.length || sc >= image[0].length || sr < 0 || sc < 0 || image[sr][sc] != oldColor) return;

        image[sr][sc] = newColor;

        dfs(image, sr + 1, sc, newColor, oldColor);
        dfs(image, sr - 1, sc, newColor, oldColor);
        dfs(image, sr, sc - 1, newColor, oldColor);
        dfs(image, sr, sc + 1, newColor, oldColor);

    }


    public static void main(String[] args) {


        int[][] image = {

                {1, 1, 1}, {1, 1, 0}, {1, 0, 1}};


        FloodFill floodFill = new FloodFill();

        image = floodFill.floodFill(image, 1, 1, 2);


        Arrays.stream(image).forEach(row -> {
            Arrays.stream(row).forEach(x -> {

                System.out.print(row + " ");
            });
        });

    }

}
