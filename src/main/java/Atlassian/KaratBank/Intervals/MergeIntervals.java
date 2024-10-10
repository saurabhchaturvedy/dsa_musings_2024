package Atlassian.KaratBank.Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {


    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, Comparator.comparing(i -> i[0]));

        List<int[]> result = new ArrayList<>();

        int[] refInterval = intervals[0];

        result.add(refInterval);

        for (int[] interval : intervals) {

            if (interval[0] <= refInterval[1]) {

                refInterval[1] = Math.max(interval[1], refInterval[1]);
            } else {

                refInterval = interval;
                result.add(refInterval);
            }
        }

        return result.toArray(new int[result.size()][]);
    }
}
