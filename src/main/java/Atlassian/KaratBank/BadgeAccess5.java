package Atlassian.KaratBank;

import java.util.*;

public class BadgeAccess5 {




    public static Map<String, List<String>> findFrequentBadgers(List<String[]> badgeTimes) {
        // Map to store each employee's badge times
        Map<String, List<Integer>> badgeMap = new HashMap<>();

        // Populate the map with employee names and their badge times
        for (String[] entry : badgeTimes) {
            String name = entry[0];
            int time = Integer.parseInt(entry[1]);  // Convert time to an integer for easy comparison
            badgeMap.putIfAbsent(name, new ArrayList<>());
            badgeMap.get(name).add(time);
        }

        // Map to store the result
        Map<String, List<String>> result = new HashMap<>();

        // Iterate over each employee's badge times
        for (Map.Entry<String, List<Integer>> entry : badgeMap.entrySet()) {
            String name = entry.getKey();
            List<Integer> times = entry.getValue();

            // Sort the badge times for easier comparison
            Collections.sort(times);

            // Check if the employee badged 3 or more times in a one-hour window
            for (int i = 0; i < times.size() - 2; i++) {
                // If the time difference between the ith and (i+2)th entry is <= 100, it's within 1 hour
                if (times.get(i + 2) - times.get(i) <= 100) {
                    result.putIfAbsent(name, new ArrayList<>());
                    // Add these three times (or more if they keep badging in within the hour)
                    for (int j = i; j <= i + 2; j++) {
                        result.get(name).add(String.format("%04d", times.get(j)));  // Format to 4-digit time
                    }
                    // Break after finding the first valid one-hour window (optional, depending on requirements)
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

        // Get the result
        Map<String, List<String>> frequentBadgers = findFrequentBadgers(badgeTimes);

        // Print the result
        for (Map.Entry<String, List<String>> entry : frequentBadgers.entrySet()) {
            System.out.println(entry.getKey() + ": " + String.join(" ", entry.getValue()));
        }
    }
}
