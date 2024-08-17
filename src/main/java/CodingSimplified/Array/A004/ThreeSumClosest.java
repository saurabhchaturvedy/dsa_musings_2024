package CodingSimplified.Array.A004;

import java.util.Arrays;

public class ThreeSumClosest {


    public static int threeSumClosest(int[] arr, int target) {

        if (arr.length == 0 || arr.length < 3) return -1;

        Arrays.sort(arr);
        int currentSum = arr[0] + arr[1] + arr[2];
        int minDifference = Integer.MAX_VALUE;


        for (int i = 0; i < arr.length - 2; i++) {


            int start = i + 1;
            int end = arr.length - 1;


            while (start < end) {


                int sum = arr[start] + arr[end] + arr[i];

                if (sum == target) {
                    return sum;

                } else if (sum < target) {

                    start++;
                } else {

                    end--;
                }


                int diffToTarget = Math.abs(sum - target);
                if (diffToTarget < minDifference) {

                    currentSum = sum;
                    minDifference = diffToTarget;
                }
            }

        }

        return currentSum;
    }


    public static void main(String[] args) {


        int[] arr = {-1, 2, 1, 4};

        ThreeSumClosest threeSumClosest = new ThreeSumClosest();


        int sumClosest = threeSumClosest(arr, 1);


        System.out.println(" closest sum is " + sumClosest);
    }
}
