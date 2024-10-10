package Atlassian.KaratBank;

import java.util.*;

public class BadgeAccess {


    public static void badgeAccess(String[][] badgeAccessLog) {


        Set<String> enterWithoutExit = new HashSet<>();
        Set<String> exitWithoutEnter = new HashSet<>();

        Map<String, Boolean> employeeState = new HashMap<>();

        for (String[] list : badgeAccessLog) {

            String emp = list[0];
            String action = list[1];

            if (action.equals("enter")) {
                if (employeeState.getOrDefault(emp, false)) {

                    enterWithoutExit.add(emp);
                }

                employeeState.put(emp, true);
            } else {

                if (!employeeState.getOrDefault(emp, false)) {

                    exitWithoutEnter.add(emp);
                }

                employeeState.put(emp, false);

            }
        }


        for (Map.Entry<String, Boolean> entry : employeeState.entrySet()) {

            if (entry.getValue()) {

                enterWithoutExit.add(entry.getKey());
            }
        }

        System.out.println(" Entry without exit : " + enterWithoutExit);
        System.out.println(" Exit without entry " + exitWithoutEnter);
    }


    public static void main(String[] args) {


        String[][] badgeRecords = {
                {"Alice", "enter"},
                {"Bob", "enter"},
                {"Alice", "exit"},
                {"Alice", "enter"},
                {"Bob", "exit"},
                {"Alice", "enter"},
                {"Bob", "exit"},
                {"Bob", "exit"}};


        badgeAccess(badgeRecords);


        String[][] badge_records = {
                {"Martha", "exit"},
                {"Paul", "enter"},
                {"Martha", "enter"},
                {"Martha", "exit"},
                {"Jennifer", "enter"},
                {"Paul", "enter"},
                {"Curtis", "enter"},
                {"Paul", "exit"},
                {"Martha", "enter"},
                {"Martha", "exit"},
                {"Jennifer", "exit"},
        };


        badgeAccess(badge_records);
    }
}
