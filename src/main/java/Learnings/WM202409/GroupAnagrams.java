package Learnings.WM202409;

import java.util.*;

public class GroupAnagrams {


    public static List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> anagrams = new ArrayList<>();

        if (strs.length == 0) {
            return anagrams;
        }

        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {

            char[] ch = s.toCharArray();

            Arrays.sort(ch);

            String sorted = String.valueOf(ch);

            if (map.containsKey(sorted)) {

                List<String> list = map.get(sorted);
                list.add(s);
                map.put(sorted, list);

            } else {

                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(sorted, list);

            }

        }

        for (List<String> lst : map.values()) {

            anagrams.add(lst);
        }

        return anagrams;

    }


    public static void main(String[] args) {

        String[] arr = {"eat", "tea", "tan", "ate", "nat", "bat"};


        System.out.println(groupAnagrams(arr));
    }
}
