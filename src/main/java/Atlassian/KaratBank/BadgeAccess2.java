package Atlassian.KaratBank;

import java.util.*;

public class BadgeAccess2 {


    public static Map<String, List<Integer>> findBadgeViolations(String[][] badgeLog) {

        Map<String, List<Integer>> mapp = new HashMap<>();

        Map<String, List<Integer>> map = new HashMap<>();

        for (String[] log : badgeLog) {

            String name = log[0];
            Integer time = Integer.parseInt(log[1]);

            if (!map.containsKey(name)) {
                map.put(name, new ArrayList<>());
            }

            map.get(name).add(time);

        }

        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {

            List<Integer> slot = entry.getValue();
            Collections.sort(slot);

            if (violationOccured(slot)) {

                mapp.put(entry.getKey(), entry.getValue());
            }
        }
        return mapp;
    }

    private static boolean violationOccured(List<Integer> times) {


        for (int i = 0; i < times.size() - 2; i++) {


            boolean isViolated = isWithinOneHour(times.get(i), times.get(2));
            if ((isViolated)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isWithinOneHour(Integer time1, Integer time2) {


        return Math.abs(time1 - time2) <= 100;

    }


    public static void main(String[] args) {


        String[][] badge_records = {{"Paul", "1355"}, {"Jennifer", "1910"}, {"John", "830"}, {"Paul", "1315"}, {"John", "835"}, {"Paul", "1405"}, {"Paul", "1630"}, {"John", "855"},

                {"John", "915"}, {"John", "930"}, {"Jennifer", "1335"}, {"Jennifer", "730"}, {"John", "1630"}};


        System.out.println(findBadgeViolations(badge_records));

    }
}
