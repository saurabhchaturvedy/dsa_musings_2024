package CodingSimplified.String.String002;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GetMostFrequentWord {


    public static String getMostFrequentWord(String str) {

        if (str == null) {
            return null;
        }

        Map<String, Integer> map = new HashMap<>();

        String[] st = str.split("\\W+");

        for (String s : st) {

            map.put(s, map.getOrDefault(s, 0) + 1);
        }


        int maxFrequency = 0;
        String freqWord = null;


        Set<Map.Entry<String, Integer>> entry = map.entrySet();

        for (Map.Entry<String, Integer> e : entry) {

            if (e.getValue() > maxFrequency) {

                maxFrequency = e.getValue();
                freqWord = e.getKey();
            }
        }

        return freqWord;
    }


    public static void main(String[] args) {


        String str = "Best item in category. Lenovo samsung lenovo? item";

        System.out.println(" most frequent word : " + getMostFrequentWord(str));
    }
}
