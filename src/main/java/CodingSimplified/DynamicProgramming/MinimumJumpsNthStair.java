package CodingSimplified.DynamicProgramming;

public class MinimumJumpsNthStair {


    public static int minJumpsClimbingStairsBottomUp(int n) {

        int[] dp = new int[n + 1];

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {

            dp[i] = 1 + Math.min(Math.min(dp[i - 1], dp[i - 2]), dp[i - 3]);
        }


        return dp[n];
    }


    public static void main(String[] args) {

        int n = 7;

        System.out.println(" min cost climbing stairs = " + minJumpsClimbingStairsBottomUp(n));
    }
}
