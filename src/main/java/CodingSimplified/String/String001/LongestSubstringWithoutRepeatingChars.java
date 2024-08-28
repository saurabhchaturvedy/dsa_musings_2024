package CodingSimplified.String.String001;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingChars {


    public static int lengthOfLongestSubstring(String s) {


        if (s == null || s.length() == 0) {
            return 0;
        }


        int max_length = 0;
        int start = 0;
        int end = 0;

        Map<Character, Integer> map = new HashMap<>();

        while (end < s.length()) {

            char charAtStart = s.charAt(end);

            if (map.containsKey(charAtStart)) {

                start = Math.max(start, map.get(charAtStart) + 1);
            }

            map.put(charAtStart, end);
            max_length = Math.max(max_length, end - start + 1);
            end++;
        }


        return max_length;
    }


    public static void main(String[] args) {

        String str = "pwwkew";

        System.out.println(" longest substring length without repeating characters =" + lengthOfLongestSubstring(str));
    }
}
