package EricProgramming.Intervals;

import java.util.Arrays;

public class MinimumConferenceRooms {


    public static int minimumConferenceRooms(int[][] intervals) {

        if (intervals.length == 0 || intervals == null) {
            return 0;
        }


        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];

        for (int i = 0; i < intervals.length; i++) {

            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }


        Arrays.sort(start);
        Arrays.sort(end);


        int minConferenceRooms = 0;
        int minRooms = 0;

        int i = 0;
        int j = 0;

        while (i < intervals.length) {


            if (start[i] < end[j]) {

                minConferenceRooms++;
                i++;
            } else {

                minConferenceRooms--;
                j++;
            }

            minRooms = Math.max(minConferenceRooms, minRooms);
        }


        return minRooms;
    }


    public static void main(String[] args) {


        int[][] meetings = {{0, 30}, {5, 10}, {15, 20}};


        int minConferenceRooms = minimumConferenceRooms(meetings);

        System.out.println(" min conference rooms = " + minConferenceRooms);


        int[][] meetings2 = {{1, 5}, {2, 6}, {3, 7}, {4, 8}};


        int minConferenceRooms2 = minimumConferenceRooms(meetings2);

        System.out.println(" min conference rooms = " + minConferenceRooms2);
    }
}
