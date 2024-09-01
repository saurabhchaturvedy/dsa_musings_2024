package EricProgramming.Intervals;

import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingIntervals {


    public int eraseOverlapIntervals(int[][] intervals) {


        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

        int end = intervals[0][1];


        int overlaps = 0;


        for (int i = 1; i < intervals.length; i++) {

            if (intervals[i][0] >= end) {
                end = intervals[i][1];
            } else {
                overlaps++;
            }
        }


        return overlaps;
    }
}
