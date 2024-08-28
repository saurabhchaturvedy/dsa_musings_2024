package CodingSimplified.String.String002;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowString {

    public static String minWindow(String s, String t) {

        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return null;
        }

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {

            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }

        int start = 0;
        int end = 0;
        int min_substring_len = Integer.MAX_VALUE;
        int min_substring_start = 0;
        int matchCount = 0;

        while (end < s.length()) {

            char currentChar = s.charAt(end);

            if (map.containsKey(currentChar)) {

                map.put(currentChar, map.get(currentChar) - 1);

                if (map.get(currentChar) >= 0) {

                    matchCount++;
                }
            }

            while (matchCount == t.length()) {

                if (end - start + 1 < min_substring_len) {

                    min_substring_len = end - start + 1;
                    min_substring_start = start;
                }


                char charAtStart = s.charAt(start++);

                if (map.containsKey(charAtStart)) {

                    if (map.get(charAtStart) == 0) {
                        matchCount--;
                    }

                    map.put(charAtStart, map.get(charAtStart) + 1);
                }
            }


            end++;
        }


        if (min_substring_len > s.length()) {

            return "";
        }


        return s.substring(min_substring_start, min_substring_start + min_substring_len);
    }


    public static void main(String[] args) {


        String s = "ADOBECODEBANC";
        String t = "ABC";


        System.out.println(" minimum window substring = " + minWindow(s, t));
    }

}
