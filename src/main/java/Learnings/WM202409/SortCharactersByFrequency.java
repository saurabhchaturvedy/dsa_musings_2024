package Learnings.WM202409;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortCharactersByFrequency {


    public static String sortCharsByFrequency(String s) {

        if (s == null) {
            return null;
        }


        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {

            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }


        List<Character>[] freq = new List[s.length() + 1];

        for (char c : map.keySet()) {


            int index = map.get(c);
            if (freq[index] == null) {

               freq[index] = new ArrayList<>();

            }
            freq[index].add(c);
        }


        StringBuilder sb = new StringBuilder();

        for (int i = freq.length - 1; i >= 0; i--) {

            if (freq[i] != null) {

                for (char c : freq[i]) {


                    for (int k = 0; k < map.get(c); k++) {

                        sb.append(c);
                    }
                }
            }
        }

        return sb.toString();
    }


    public static void main(String[] args) {


        System.out.println(" sorted chars by frequency : " + sortCharsByFrequency("tree"));
    }
}
