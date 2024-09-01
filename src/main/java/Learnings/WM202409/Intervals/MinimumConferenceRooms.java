package Learnings.WM202409.Intervals;

import java.util.Arrays;
import java.util.Comparator;

public class MinimumConferenceRooms {


    public static int minimumConferenceRooms(int[][] intervals) {

        if (intervals.length <= 0) {
            return 0;
        }


        Arrays.sort(intervals, Comparator.comparingInt(x -> x[0]));

        int conferenceRooms = 1;

        int[] previousMeetingSchedule = intervals[0];

        for (int i = 1; i < intervals.length; i++) {

            if (intervals[i][0] > previousMeetingSchedule[0] && intervals[i][1] < previousMeetingSchedule[1]) {

                conferenceRooms++;
            }

            previousMeetingSchedule = intervals[i];
        }


        return conferenceRooms;
    }


    public static void main(String[] args) {


        int[][] meetings = {{0, 30}, {5, 10}, {15, 20}};


        int minConferenceRooms = minimumConferenceRooms(meetings);

        System.out.println(" min conference rooms = " + minConferenceRooms);


        int[][] meetings2 = {{7, 10}, {2, 4}};


        int minConferenceRooms2 = minimumConferenceRooms(meetings2);

        System.out.println(" min conference rooms = " + minConferenceRooms2);
    }
}
