package Learnings.WM202409.Intervals;

import java.util.*;

public class MergeIntervals {


    public static int[][] merge(int[][] intervals) {


        if (intervals.length <= 1) {

            return intervals;
        }


        Arrays.sort(intervals, Comparator.comparing(i -> i[0]));


        List<int[]> list = new ArrayList<>();


        int[] refInterval = intervals[0];

        list.add(refInterval);

        for (int[] interval : intervals) {


            if (interval[0] <= refInterval[1]) {

                refInterval[1] = Math.max(interval[1], refInterval[1]);
            } else {

                refInterval = interval;
                list.add(refInterval);
            }
        }

        return list.toArray(new int[list.size()][]);
    }


    public static void main(String[] args) {


        int[][] arr = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};


        int[][] result = merge(arr);

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
