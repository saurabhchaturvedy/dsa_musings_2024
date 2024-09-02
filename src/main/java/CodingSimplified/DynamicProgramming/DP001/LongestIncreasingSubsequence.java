package CodingSimplified.DynamicProgramming.DP001;

public class LongestIncreasingSubsequence {


    public static int lengthOfLIS(int[] nums) {

        int[] lis = new int[nums.length];

        for (int i = 0; i < lis.length; i++) {
            lis[i] = 1;
        }

        int maxLength = 1;

        for (int i = 1; i < lis.length; i++) {

            for (int j = 0; j < i; j++) {

                if (nums[i] > nums[j] && lis[j] + 1 > lis[i]) {

                    lis[i] = lis[j] + 1;
                    maxLength = Math.max(maxLength, lis[i]);
                }
            }
        }

        return maxLength;
    }


    public static void main(String[] args) {


        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};

        System.out.println(" length of longest increasing subsequence = " + lengthOfLIS(arr));
    }
}
