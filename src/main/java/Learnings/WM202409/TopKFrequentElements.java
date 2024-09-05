package Learnings.WM202409;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentElements {


    public static int[] topKFrequentElements(int[] arr, int k) {

        Map<Integer, Integer> map = new HashMap<>();


        for (int num : arr) {

            map.put(num, map.getOrDefault(num, 0) + 1);
        }


        List<Integer>[] freq = new List[arr.length + 1];


        for (int num : map.keySet()) {

            int index = map.get(num);

            if (freq[index] == null) {

                freq[index] = new ArrayList<>();
            }

            freq[index].add(num);
        }


        int[] result = new int[k];
        int kk = 0;

        for (int i = freq.length - 1; i >= 0; i--) {

            if (freq[i] != null) {

                for (int x : freq[i]) {

                    result[kk++] = x;
                }
            }

            if (kk == k) break;
        }

        return result;
    }


    public static void main(String[] args) {


        int[] arr = {1, 1, 1, 2, 2, 3};

        System.out.println(" top K frequent elements : ");

        int[] topk = topKFrequentElements(arr, 2);


        for (int x : topk) {

            System.out.print(x + " ");
        }


    }
}
