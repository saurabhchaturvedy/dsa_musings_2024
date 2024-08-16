package CodingSimplified.Array.A004;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TripletSum {


    public static List<List<Integer>> tripletSum(int[] arr, int target) {


        List<List<Integer>> list = new LinkedList<>();


        for (int i = 0; i < arr.length - 2; i++) {

            int sum = target - arr[i];

            int start = i + 1;
            int end = arr.length - 1;

            while (start < end) {

                int t = arr[start] + arr[end];

                if (t == sum) {
                    list.add(Arrays.asList(arr[start], arr[end], arr[i]));
                    start++;
                    end--;
                } else if (t < sum) {

                    start++;
                } else {

                    end--;
                }

            }
        }

        return list;
    }


    public static void main(String[] args) {


        int arr[] = {1, 2, -3, 4, -2, -1};

        List<List<Integer>> list = tripletSum(arr, 1);

        System.out.println(list);
    }
}
