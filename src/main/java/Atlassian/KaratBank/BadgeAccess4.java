package Atlassian.KaratBank;

import java.util.*;

public class BadgeAccess4 {



    public static void findFrequentBadgers(List<String[]> badgeTimes) {
        // Step 1: Group badge times by employee
        Map<String, List<Integer>> badgeMap = new HashMap<>();
        for (String[] badgeEntry : badgeTimes) {
            String name = badgeEntry[0];
            int time = Integer.parseInt(badgeEntry[1]);  // Convert time to integer

            badgeMap.putIfAbsent(name, new ArrayList<>());
            badgeMap.get(name).add(time);
        }

        Map<String, List<Integer>> result = new HashMap<>();

        // Step 2: Process each employee
        for (Map.Entry<String, List<Integer>> entry : badgeMap.entrySet()) {
            String name = entry.getKey();
            List<Integer> times = entry.getValue();

            // Step 3: Sort badge times for each employee
            Collections.sort(times);

            // Step 4: Sliding window to check one-hour period
            for (int i = 0; i < times.size(); i++) {
                int start = times.get(i);
                int windowEnd = i + 2; // Window needs at least 3 entries
                if (windowEnd < times.size() && times.get(windowEnd) - start <= 100) {
                    result.put(name, times.subList(i, windowEnd + 1));
                    break;  // Only capture the earliest one-hour period
                }
            }
        }

        // Print the result
        for (Map.Entry<String, List<Integer>> entry : result.entrySet()) {
            String name = entry.getKey();
            List<Integer> times = entry.getValue();
            System.out.println(name + ": " + times);
        }
    }


    public static List<String> getBadgeRecords(List<String[]> badgeTimes) {
        Map<String, List<Integer>> employeeBadgeMap = new HashMap<>();

        for (String[] badgeRecord : badgeTimes) {
            String employee = badgeRecord[0];
            int time = Integer.parseInt(badgeRecord[1]);
            employeeBadgeMap.putIfAbsent(employee, new ArrayList<>());
            employeeBadgeMap.get(employee).add(time);
        }

        List<String> result = new ArrayList<>();


        for (Map.Entry<String, List<Integer>> entry : employeeBadgeMap.entrySet()) {
            String employee = entry.getKey();
            List<Integer> accessTimes = entry.getValue();
            Collections.sort(accessTimes);

            for (int i = 0; i < accessTimes.size() - 2; i++) {
                int startTime = accessTimes.get(i);
                int endTime = startTime + 100;

                int count = 1;
                StringBuilder sb = new StringBuilder();
                sb.append(employee).append(": ").append(startTime);

                for (int j = i + 1; j < accessTimes.size(); j++) {
                    int currentTime = accessTimes.get(j);
                    if (currentTime <= endTime) {
                        count++;
                        sb.append(" ").append(currentTime);
                    } else {
                        break;
                    }
                }

                if (count >= 3) {
                    result.add(sb.toString());
                    break;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<String[]> badgeTimes = Arrays.asList(
                new String[]{"Paul", "1355"}, new String[]{"Jennifer", "1910"}, new String[]{"Jose", "835"},
                new String[]{"Jose", "830"}, new String[]{"Paul", "1315"}, new String[]{"Chloe", "0"},
                new String[]{"Chloe", "1910"}, new String[]{"Jose", "1615"}, new String[]{"Jose", "1640"},
                new String[]{"Paul", "1405"}, new String[]{"Jose", "855"}, new String[]{"Jose", "930"},
                new String[]{"Jose", "915"}, new String[]{"Jose", "730"}, new String[]{"Jose", "940"},
                new String[]{"Jennifer", "1335"}, new String[]{"Jennifer", "730"}, new String[]{"Jose", "1630"},
                new String[]{"Jennifer", "5"}, new String[]{"Chloe", "1909"}, new String[]{"Zhang", "1"},
                new String[]{"Zhang", "10"}, new String[]{"Zhang", "109"}, new String[]{"Zhang", "110"},
                new String[]{"Amos", "1"}, new String[]{"Amos", "2"}, new String[]{"Amos", "400"},
                new String[]{"Amos", "500"}, new String[]{"Amos", "503"}, new String[]{"Amos", "504"},
                new String[]{"Amos", "601"}, new String[]{"Amos", "602"}, new String[]{"Paul", "1416"}
        );

       // findFrequentBadgers(badgeTimes);

        List<String[]> badge_times = Arrays.asList(
                new String[]{"Paul", "1355"}, new String[]{"Jennifer", "1910"}, new String[]{"Jose", "835"},
                new String[]{"Jose", "830"}, new String[]{"Paul", "1315"}, new String[]{"Chloe", "0"},
                new String[]{"Chloe", "1910"}, new String[]{"Jose", "1615"}, new String[]{"Jose", "1640"},
                new String[]{"Paul", "1405"}, new String[]{"Jose", "855"}, new String[]{"Jose", "930"},
                new String[]{"Jose", "915"}, new String[]{"Jose", "730"}, new String[]{"Jose", "940"},
                new String[]{"Jennifer", "1335"}, new String[]{"Jennifer", "730"}, new String[]{"Jose", "1630"},
                new String[]{"Jennifer", "5"}, new String[]{"Chloe", "1909"}, new String[]{"Zhang", "1"},
                new String[]{"Zhang", "10"}, new String[]{"Zhang", "109"}, new String[]{"Zhang", "110"},
                new String[]{"Amos", "1"}, new String[]{"Amos", "2"}, new String[]{"Amos", "400"},
                new String[]{"Amos", "500"}, new String[]{"Amos", "503"}, new String[]{"Amos", "504"},
                new String[]{"Amos", "601"}, new String[]{"Amos", "602"}, new String[]{"Paul", "1416"}
        );


        List<String> result = getBadgeRecords(badge_times);


        for (String employee : result) {
            System.out.println(employee);
        }
    }
}
