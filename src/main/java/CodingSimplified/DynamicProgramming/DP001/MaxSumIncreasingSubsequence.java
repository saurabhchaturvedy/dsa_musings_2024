package CodingSimplified.DynamicProgramming.DP001;

public class MaxSumIncreasingSubsequence {


    public static int maxSum(int[] nums) {


        int[] mis = new int[nums.length];

        for (int i = 0; i < mis.length; i++) {
            mis[i] = nums[i];
        }


        int maxSumIncreasingSubsequence = nums[0];

        for (int i = 1; i < mis.length; i++) {

            for (int j = 0; j < i; j++) {

                if (nums[i] > nums[j] && nums[i] + mis[j] > mis[i]) {

                    mis[i] = nums[i] + mis[j];

                    maxSumIncreasingSubsequence = Math.max(maxSumIncreasingSubsequence, mis[i]);
                }
            }
        }

        return maxSumIncreasingSubsequence;
    }
}
