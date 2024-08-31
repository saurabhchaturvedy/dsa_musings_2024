package Learnings.WM202409.Intervals;

import java.util.Arrays;
import java.util.Stack;

public class MergeIntervals {


    public static int[][] merge(int[][] intervals) {


        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);


        Stack<int[]> stack = new Stack<>();


        stack.push(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {

            int[] prevInterval = stack.peek();

            if (prevInterval[1] >= intervals[i][0]) {

                prevInterval[1] = Math.max(prevInterval[1], intervals[i][1]);

            } else {

                stack.push(new int[]{intervals[i][0], intervals[i][1]});
            }

        }


        return stack.toArray(new int[stack.size()][2]);

    }


    public static void main(String[] args) {


        int[][] arr = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};


        int[][] result = merge(arr);

        for (int[] x : result) {
            System.out.print("[ ");
            for (int num : x) {


                System.out.print(num + " ");

            }
            System.out.print(" ] ");
            // System.out.println();
        }

    }
}
