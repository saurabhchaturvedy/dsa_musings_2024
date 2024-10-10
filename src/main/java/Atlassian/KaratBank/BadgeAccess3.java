package Atlassian.KaratBank;

import java.util.*;

public class BadgeAccess3 {


    public static void findBadgeTapViolations(List<String[]> badgeLogs) {

        Map<String, List<Integer>> empCheckInsMap = new HashMap<>();

        for (String[] log : badgeLogs) {


            String name = log[0];
            Integer time = Integer.parseInt(log[1]);

            if (!empCheckInsMap.containsKey(name)) {

                empCheckInsMap.put(name, new ArrayList<>());
            }
            empCheckInsMap.get(name).add(time);
        }


        Map<String, List<Integer>> result = new HashMap<>();

        for (Map.Entry<String, List<Integer>> entry : empCheckInsMap.entrySet()) {

            String name = entry.getKey();
            List<Integer> times = entry.getValue();

            Collections.sort(times);

            for (int i = 0; i < times.size(); i++) {

                for (int j = i + 2; j < times.size(); j++) {

                    if (times.get(j) - times.get(i) <= 100) {

                        result.put(name, times.subList(i, j + 1));
                        break;
                    }
                }

                if (result.containsKey(name)) {
                    break;
                }
            }
        }


        for (Map.Entry<String, List<Integer>> entry : result.entrySet()) {

            String name = entry.getKey();
            List<Integer> times = entry.getValue();
            System.out.println(name + " : " + times);
        }
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


        findBadgeTapViolations(badgeTimes);
    }
}
