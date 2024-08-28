package CodingSimplified.String.String002;

import java.util.HashMap;
import java.util.Map;

public class AnagramInString {


    public static boolean checkInclusion(String s1, String s2) {

        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {

            return false;

        }

        int matchCount = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {

            map.put(s1.charAt(i), map.getOrDefault(s1.charAt(i), 0) + 1);
        }

        int start = 0;
        int end = 0;

        while (end < s2.length()) {
            char currentChar = s2.charAt(end);

            if (map.containsKey(currentChar)) {

                map.put(currentChar, map.get(currentChar) - 1);
                if (map.get(currentChar) == 0) {
                    matchCount++;
                }
            }

            if (map.size() == matchCount) {

                return true;
            }

            if (end >= s1.length() - 1) {

                char charAtStart = s2.charAt(start++);
                if (map.containsKey(charAtStart)) {
                    if (map.get(charAtStart) == 0) {
                        matchCount--;
                    }

                    map.put(charAtStart, map.get(charAtStart) + 1);
                }

            }
            end++;
        }

        return false;

    }


    public static void main(String[] args) {


        String s1 = "ab";
        String s2 = "eidbaooo";

        System.out.println(" String has anagram " + s1 + " ? " + checkInclusion(s1, s2));
    }
}
