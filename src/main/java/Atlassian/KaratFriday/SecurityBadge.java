package Atlassian.KaratFriday;

import java.util.*;

public class SecurityBadge {


    public static List<String> getSecurityBadgeViolations(List<String[]> badgeLogs) {


        Map<String, List<Integer>> empBadgeMap = new HashMap<>();

        for (String[] log : badgeLogs) {

            String name = log[0];
            Integer time = Integer.parseInt(log[1]);

            if (!empBadgeMap.containsKey(name)) {

                empBadgeMap.put(name, new ArrayList<>());
            }

            empBadgeMap.get(name).add(time);
        }


        List<String> result = new ArrayList<>();

        for (Map.Entry<String, List<Integer>> entry : empBadgeMap.entrySet()) {

            String empName = entry.getKey();
            List<Integer> times = entry.getValue();

            Collections.sort(times);
            for (int i = 0; i < times.size() - 2; i++) {

                int startTime = times.get(i);
                int endTime = startTime + 100;

                int count = 1;
                StringBuilder sb = new StringBuilder();
                sb.append(empName).append(":").append(startTime);

                for (int j = i + 1; j < times.size(); j++) {

                    int currentTime = times.get(j);

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

        List<String[]> badge_times = Arrays.asList(new String[]{"Paul", "1355"}, new String[]{"Jennifer", "1910"}, new String[]{"Jose", "835"}, new String[]{"Jose", "830"}, new String[]{"Paul", "1315"}, new String[]{"Chloe", "0"}, new String[]{"Chloe", "1910"}, new String[]{"Jose", "1615"}, new String[]{"Jose", "1640"}, new String[]{"Paul", "1405"}, new String[]{"Jose", "855"}, new String[]{"Jose", "930"}, new String[]{"Jose", "915"}, new String[]{"Jose", "730"}, new String[]{"Jose", "940"}, new String[]{"Jennifer", "1335"}, new String[]{"Jennifer", "730"}, new String[]{"Jose", "1630"}, new String[]{"Jennifer", "5"}, new String[]{"Chloe", "1909"}, new String[]{"Zhang", "1"}, new String[]{"Zhang", "10"}, new String[]{"Zhang", "109"}, new String[]{"Zhang", "110"}, new String[]{"Amos", "1"}, new String[]{"Amos", "2"}, new String[]{"Amos", "400"}, new String[]{"Amos", "500"}, new String[]{"Amos", "503"}, new String[]{"Amos", "504"}, new String[]{"Amos", "601"}, new String[]{"Amos", "602"}, new String[]{"Paul", "1416"});

        List<String> result = getSecurityBadgeViolations(badge_times);

        for (String s : result) {
            System.out.println(s);
        }
    }
}
