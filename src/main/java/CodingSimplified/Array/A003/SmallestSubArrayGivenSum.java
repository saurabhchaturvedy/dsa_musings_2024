package CodingSimplified.Array.A003;

import java.util.HashMap;
import java.util.Map;

public class SmallestSubArrayGivenSum {


    public static int smallestSubArrayGivenSum(int[] arr, int sum) {

        if (arr.length == 0) return -1;


        int window_sum = 0;

        int start = 0;
        int end = 0;

        Map<Integer, Integer> windowSumMap = new HashMap<>();

        int smallest = Integer.MAX_VALUE;

        while (end < arr.length) {

            window_sum = window_sum + arr[end];

            if (window_sum == sum) {

                //System.out.print(" start : " + start + " end : " + end);
                smallest = Math.min(smallest, end + 1);
            }


            int windowDiff = window_sum - sum;

            if (windowSumMap.containsKey(windowDiff)) {

                start = windowSumMap.get(windowDiff);
                //System.out.print(" start : " + (start + 1) + " end : " + end);
                smallest = Math.min(smallest, end - start);

            }

            windowSumMap.put(window_sum, end);
            end++;
        }

        return smallest;
    }

    public static void main(String[] args) {


        int arr[] = {3, 2, 5, 5, 3, 2, 10};

        int sum = 10;

        int smallestSubArrayGivenSum = smallestSubArrayGivenSum(arr, sum);

        System.out.println(" smallest subarray = " + smallestSubArrayGivenSum);

    }
}
