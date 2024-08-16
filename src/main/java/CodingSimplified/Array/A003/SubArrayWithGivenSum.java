package CodingSimplified.Array.A003;

import java.util.HashMap;
import java.util.Map;

public class SubArrayWithGivenSum {


    public static void subarrayWithGivenSum(int[] arr, int sum) {

        if (arr.length == 0) return;


        int window_sum = 0;

        int start = 0;
        int end = 0;

        Map<Integer, Integer> windowSumMap = new HashMap<>();


        while (end < arr.length) {

            window_sum = window_sum + arr[end];

            if (window_sum == sum) {

                System.out.print(" start : " + start + " end : " + end);
                return;
            }


            int windowDiff = window_sum - sum;

            if (windowSumMap.containsKey(windowDiff)) {

                start = windowSumMap.get(windowDiff);
                System.out.print(" start : " + (start+1) + " end : " + end);
                return;

            }

            windowSumMap.put(window_sum, end);
            end++;
        }
    }


    public static void main(String[] args) {

        int[] arr = {4, 2, -5, 3, 1, 8};

        subarrayWithGivenSum(arr, -1);
    }
}
