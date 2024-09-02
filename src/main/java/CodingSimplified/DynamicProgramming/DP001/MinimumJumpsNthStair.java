package CodingSimplified.DynamicProgramming.DP001;

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


    public static int minJumpsClimbingStairsTopDown(int[] dp, int n) {

        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 1;

        if (dp[n] == 0) {

            dp[n] = 1 + Math.min(Math.min(minJumpsClimbingStairsTopDown(dp, n - 1), minJumpsClimbingStairsTopDown(dp, n - 2)), minJumpsClimbingStairsTopDown(dp, n - 3));
        }

        return dp[n];
    }


    public static void main(String[] args) {

        int n = 7;

        System.out.println(" min cost climbing stairs = " + minJumpsClimbingStairsBottomUp(n));

        int[]dp = new int[n+1];
        System.out.println(" min cost climbing stairs = " + minJumpsClimbingStairsTopDown(dp,n));
    }
}
