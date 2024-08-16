package CodingSimplified.Array.A004;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class QuadrupletSum {


    public static List<List<Integer>> quadrupletSum(int[] arr, int target) {

        if (arr.length == 0) return new LinkedList<>();


        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i < arr.length - 3; i++) {

            for (int j = i + 1; j < arr.length - 2; j++) {


                int sum = target - (arr[i] + arr[j]);

                int start = i + 1;
                int end = arr.length - 1;

                while (start < end) {

                    int t = arr[start] + arr[end];

                    if (t == sum) {

                        list.add(Arrays.asList(arr[start], arr[end], arr[i], arr[j]));
                        start++;
                        end--;
                    } else if (t < sum) {

                        start++;
                    } else {

                        end--;
                    }
                }
            }
        }

        return list;
    }


    public static void main(String[] args) {


        int arr[] = {1, 2, -3, 4, -2, -1, 3};

        List<List<Integer>> list = quadrupletSum(arr, 3);

        System.out.println(list);
    }
}
