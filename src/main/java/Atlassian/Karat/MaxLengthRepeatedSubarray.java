package Atlassian.Karat;

public class MaxLengthRepeatedSubarray {


    public static int findLength(int[] nums1, int[] nums2) {


        int[][]dp = new int[nums1.length+1][nums2.length+1];

        int max=0;


        for(int i=1; i<=nums1.length; i++)
        {

            for(int j=1; j<=nums2.length; j++)
            {

                if(nums1[i-1]==nums2[j-1])
                {

                    dp[i][j] = dp[i-1][j-1] + 1;
                    max = Math.max(dp[i][j],max);
                }
            }
        }

        return max;
    }


    public static void main(String[] args) {

        int[]arr = {1,2,3,2,1};
        int[]arr2 = {3,2,1,4,7};

        int maxLength = findLength(arr,arr2);

        System.out.println(" max length is = "+maxLength);
    }
}
