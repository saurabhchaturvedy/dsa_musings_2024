package Atlassian.KaratBank.Intervals;

import java.util.ArrayList;
import java.util.List;

public class IntervalListIntersections {


    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {

        List<int[]> result = new ArrayList<>();

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
}
