package CodingSimplified.Array.A004;

import java.util.*;

public class SortArrayFrequency {


    public List<Integer> sortArrayBasedOnFrequency(List<Integer> numList) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < numList.size(); i++) {

            map.put(numList.get(i), map.getOrDefault(numList.get(i), 0) + 1);
        }


        Collections.sort(numList, (n1, n2) -> {

            int freq1 = map.get(n1);
            int freq2 = map.get(n2);

            if (freq1 != freq2) {

                return freq2 - freq1;
            }

            return n1 - n2;
        });

        return numList;
    }


    public static void main(String[] args) {


        Integer[] arr = {10, 7, 10, 11, 10, 7, 6, 5};

        SortArrayFrequency sortArrayFrequency = new SortArrayFrequency();


        List<Integer> list = Arrays.asList(arr);
        List<Integer> integers = sortArrayFrequency.sortArrayBasedOnFrequency(list);

        System.out.println(integers);

    }
}
