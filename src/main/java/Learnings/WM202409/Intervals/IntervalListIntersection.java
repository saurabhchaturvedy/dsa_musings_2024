package Learnings.WM202409.Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntervalListIntersection {


    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {


        List<int[]> result = new ArrayList<>();

        if (firstList.length == 0 || secondList.length == 0) {
            return result.toArray(new int[result.size()][]);
        }

        int i = 0;
        int j = 0;

        while (i < firstList.length && j < secondList.length) {


            int startFirstList = firstList[i][0];
            int endFirstList = firstList[i][1];

            int startSecondList = secondList[j][0];
            int endSecondList = secondList[j][1];


            int start = Math.max(startFirstList, startSecondList);
            int end = Math.min(endFirstList, endSecondList);

            if (start <= end) {

                result.add(new int[]{start, end});
            }

            if (endFirstList < endSecondList) {

                i++;
            } else {

                j++;
            }
        }

        return result.toArray(new int[result.size()][]);
    }


    public static void main(String[] args) {


        int[][] firstList = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] secondList = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};


        int[][] intersections = intervalIntersection(firstList, secondList);

        Arrays.stream(intersections).forEach(row -> {

            Arrays.stream(row).forEach(x -> {
                System.out.print(x + " ");

            });
            System.out.println();
        });

    }
}
