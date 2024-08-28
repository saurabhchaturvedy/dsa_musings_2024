package CodingSimplified.String.String001;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringKDistinctCharacters {


    public static int longestSubstringKDistinctCharacters(String s, int k) {

        if (s == null || s.length() == 0 || k <= 0 || k > s.length()) {
            return 0;
        }


        int max_length = 0;
        int start = 0;
        int end = 0;

        Map<Character, Integer> map = new HashMap<>();


        while (end < s.length()) {

            char currentChar = s.charAt(end);
            map.put(currentChar, map.getOrDefault(currentChar, 0) + 1);


            while (map.size() > k) {
                char charAtStart = s.charAt(start);
                map.put(charAtStart, map.get(charAtStart) - 1);

                if (map.get(charAtStart) == 0) {

                    map.remove(charAtStart);
                }

                start++;

            }

            max_length = Math.max(max_length, end - start + 1);
            end++;
        }

        return max_length;
    }


    public static void main(String[] args) {


        String str = "bccbababd";
        String str2 = "eceba";

        System.out.println(" longest substring length with K distinct characters is = " + longestSubstringKDistinctCharacters(str, 2));
        System.out.println(" longest substring length with K distinct characters is = " + longestSubstringKDistinctCharacters(str2, 2));

    }
}
