package Learnings.WM202409.Intervals;

import java.util.Arrays;
import java.util.Comparator;

public class MeetingRoom1 {


    public static boolean canAttendMeeting(int[][] meetings) {

        if (meetings.length == 0) return false;


        Arrays.sort(meetings, Comparator.comparingInt(i -> i[0]));

        int[] previousMeeting = meetings[0];

        for (int i = 1; i < meetings.length; i++) {
            if (meetings[i][0] < previousMeeting[1]) {
                System.out.println("conflict");
                return false;
            }

            previousMeeting = meetings[i];
        }

        return true;
    }


    public static void main(String[] args) {


        int[][] meetings = {{0, 30}, {5, 10}, {15, 20}};


        boolean canAttendMeetings = canAttendMeeting(meetings);

        System.out.println(" can all meetings be attended ? : " + canAttendMeetings);


        int[][] meetings2 = {{7, 10}, {2, 4}};


        boolean canAttendMeetings2 = canAttendMeeting(meetings2);

        System.out.println(" can all meetings be attended ? : " + canAttendMeetings2);


    }
}
