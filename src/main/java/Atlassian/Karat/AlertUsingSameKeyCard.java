package Atlassian.Karat;

import java.util.*;

public class AlertUsingSameKeyCard {


    public static List<String> alertNames(String[] keyName, String[] keyTime) {

        List<String> result = new ArrayList<>();
        Map<String, List<Time>> map = new HashMap<>();

        for (int i = 0; i < keyName.length; i++) {

            String name = keyName[i];
            Time time = new Time(keyTime[i]);

            if (!map.containsKey(name)) {

                map.put(name, new ArrayList<>());
            }

            map.get(name).add(time);
        }


        for (Map.Entry<String, List<Time>> entry : map.entrySet()) {


            List<Time> times = entry.getValue();

            Collections.sort(times, (a, b) -> {

                if (a.hour < b.hour) return -1;
                if (a.hour > b.hour) return 1;
                return a.minutes - b.minutes;
            });


            if (shouldAlert(times)) {

                result.add(entry.getKey());
            }
        }

        Collections.sort(result);

        return result;

    }


    public static boolean shouldAlert(List<Time> times) {


        for (int i = 0; i < times.size() - 2; i++) {

            Time a = times.get(i);
            Time b = times.get(i + 2);

            if (a.isWithinAnHour(b)) {
                return true;
            }
        }

        return false;
    }


    static class Time {

        int hour;
        int minutes;


        Time(String time) {

            String[] splitTime = time.split(":");
            this.hour = Integer.parseInt(splitTime[0]);
            this.minutes = Integer.parseInt(splitTime[1]);
        }


        public boolean isWithinAnHour(Time time) {

            if (this.hour == time.hour) return true;
            return this.hour + 1 == time.hour && this.minutes >= time.minutes;
        }
    }


    public static void main(String[] args) {


        String[] keyName = {"daniel", "daniel", "daniel", "luis", "luis", "luis", "luis"};
        String[] keyTime = {"10:00", "10:40", "11:00", "09:00", "11:00", "13:00", "15:00"};

        System.out.println(alertNames(keyName, keyTime));
    }
}
