package Learnings.WM202409.Intervals;

import java.util.ArrayList;
import java.util.List;

public class InsertIntervals {


    public static int[][] insert(int[][] intervals, int[] newInterval) {


        int n = intervals.length;

        List<int[]> result = new ArrayList<>();

        int i = 0;
        while (i < n && intervals[i][1] < newInterval[0]) {

            result.add(intervals[i]);
            i++;
        }


        while (i < n && intervals[i][0] <= newInterval[1]) {

            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }

        result.add(newInterval);

        while (i < n) {

            result.add(intervals[i]);
            i++;
        }


        return result.toArray(new int[result.size()][]);

    }


    public static void main(String[] args) {


        int[][] arr = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval = {4, 8};

        int[][] result = insert(arr, newInterval);

        for (int[] x : result) {
            System.out.print("[");
            for (int m = 0; m < x.length; m++) {


                System.out.print(x[m]);
                if (m != x.length - 1) System.out.print(",");

            }
            System.out.print("]");
        }
    }
}
