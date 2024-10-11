package Atlassian.KaratFriday;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SecurityBadge0 {


    public static void badgeViolationsData(String[][] badgeData) {


        Set<String> entryWithoutExit = new HashSet<>();
        Set<String> exitWithoutEntry = new HashSet<>();

        Map<String, Boolean> empstate = new HashMap<>();

        for (String[] badge : badgeData) {

            String name = badge[0];
            String action = badge[1];


            if (action.equals("enter")) {

                if (empstate.getOrDefault(name, false)) {

                    entryWithoutExit.add(name);
                }

                empstate.put(name, true);
            } else {


                if (!empstate.getOrDefault(name, false)) {

                    exitWithoutEntry.add(name);
                }

                empstate.put(name, false);
            }

        }


        for (Map.Entry<String, Boolean> entry : empstate.entrySet()) {

            if (entry.getValue()) {

                entryWithoutExit.add(entry.getKey());
            }
        }

        System.out.print(exitWithoutEntry);
        System.out.println(" ");
        System.out.print(entryWithoutExit);
    }


    public static void main(String[] args) {


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


        badgeViolationsData(badge_records);

    }
}
