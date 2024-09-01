package EricProgramming.Intervals;

import java.util.Arrays;
import java.util.Comparator;

public class MinimumArrowsBurstBalloons {


    public static int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        Arrays.sort(points, Comparator.comparingInt(x -> x[0]));

        int arrows = 1;
        int currentEnd = points[0][1];

        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > currentEnd) {
                arrows++;
                currentEnd = points[i][1];
            }
        }

        return arrows;
    }

    public static void main(String[] args) {


        int[][] arr = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};

        int min_arrows = findMinArrowShots(arr);

        System.out.println(" minimum arrrows to burst balloons = " + min_arrows);
    }
}
