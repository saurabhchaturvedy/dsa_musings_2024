package CodingSimplified.Array.A002;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TargetSumExists {


    public boolean targetSumExistsBruteForce(int[] arr, int target) {

        if (arr.length == 0) return false;

        for (int i = 0; i < arr.length; i++) {

            for (int j = i + 1; j < arr.length; j++) {

                if (target == arr[i] + arr[j]) {

                    return true;
                }
            }
        }

        return false;
    }


    public boolean targetSumExistsSorted(int[] arr, int target) {


        Arrays.sort(arr);

        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {

            int sum = arr[end] + arr[end];

            if (sum == target) return true;

            if (sum > target) {
                end--;
            }

            if (sum < target) {
                start++;
            }
        }

        return false;
    }


    public boolean targetSumExistsSet(int[] arr, int target) {


        Set<Integer> set = new HashSet<>();


        for (int i = 0; i < arr.length; i++) {

            if (!set.contains(target - arr[i])) {

                set.add(arr[i]);
            } else {

                return true;
            }
        }


        return false;
    }


    public static void main(String[] args) {


        int[] arr = {12, 3, 5, 1, 9};


        TargetSumExists targetSumExists = new TargetSumExists();

        System.out.println(" target sum exists brute force = " + targetSumExists.targetSumExistsBruteForce(arr, 10));
        System.out.println(" target sum exists sorted = " + targetSumExists.targetSumExistsSorted(arr, 10));
        System.out.println(" target sum exists hash set = " + targetSumExists.targetSumExistsSet(arr, 10));
    }
}
