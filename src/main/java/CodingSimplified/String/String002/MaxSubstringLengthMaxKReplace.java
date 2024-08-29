package CodingSimplified.String.String002;

import java.util.HashMap;
import java.util.Map;

public class MaxSubstringLengthMaxKReplace {


    public static int maxSubstringLengthKReplace(String s, int k) {

        if (s == null) {
            return 0;
        }


        Map<Character, Integer> map = new HashMap<>();

        int start = 0;
        int end = 0;
        int max_length = 0;
        int max_freq = 0;


        while (end < s.length()) {

            char currentChar = s.charAt(end);

            map.put(currentChar, map.getOrDefault(currentChar, 0) + 1);
            max_freq = Math.max(max_freq, map.get(currentChar));

            if (end - start + 1 - max_freq > k) {
                char charAtStart = s.charAt(start);

                map.put(charAtStart, map.get(charAtStart) - 1);

                start++;

            }
            max_length = Math.max(max_length, end - start + 1);
            end++;
        }




        return max_length;
    }


    public static void main(String[] args) {


        String s = "bccbababd";
        int k = 2;

        System.out.println("max substring length = " + maxSubstringLengthKReplace(s, k));
    }
}
