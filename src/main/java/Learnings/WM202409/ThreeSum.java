package Learnings.WM202409;

import java.util.*;

public class ThreeSum {


    public static List<List<Integer>> threeSum(int[] nums) {

        Set<List<Integer>> result = new HashSet<>();

        if (nums.length == 0) return new ArrayList<>(result);
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {

            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {

                int sum = nums[j] + nums[k];

                if (sum == -nums[i]) {

                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                } else if (sum < -nums[i]) {

                    j++;
                } else {
                    k--;
                }
            }


        }

        return new ArrayList<>(result);
    }


    public static void main(String[] args) {

        int[] arr = {-1, 0, 1, 2, -1, -4};
        System.out.println(" three sum triplets : " + threeSum(arr));
    }
}
