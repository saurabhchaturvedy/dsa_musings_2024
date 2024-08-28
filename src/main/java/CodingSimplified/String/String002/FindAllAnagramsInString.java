package CodingSimplified.String.String002;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllAnagramsInString {


    public static List<Integer> findAnagrams(String s, String p) {

        if (s == null || s.length() == 0 || p == null || p.length() == 0) {
            return new ArrayList<>();
        }


        List<Integer> anagramIndexes = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < p.length(); i++) {

            map.put(p.charAt(i), map.getOrDefault(p.charAt(i), 0) + 1);
        }

        int start = 0;
        int end = 0;
        int matchCount = 0;

        while (end < s.length()) {

            char currentChar = s.charAt(end);

            if (map.containsKey(currentChar)) {

                map.put(currentChar, map.get(currentChar) - 1);

                if (map.get(currentChar) == 0) {

                    matchCount++;
                }
            }


            if (map.size() == matchCount) {

                anagramIndexes.add(start);
            }


            if (end >= p.length() - 1) {

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

        return anagramIndexes;
    }


    public static void main(String[] args) {

        String s = "cbaebabacd";

        String p = "abc";


        System.out.println(" Anagrams in string are :: " + findAnagrams(s, p));
    }
}
