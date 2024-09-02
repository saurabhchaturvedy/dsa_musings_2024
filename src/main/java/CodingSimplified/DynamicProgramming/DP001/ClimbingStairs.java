package CodingSimplified.DynamicProgramming.DP001;

public class ClimbingStairs {


    public static int climbStairsBottomUp(int n) {

        if (n == 1) return 1;

        int[] dp = new int[n + 1];

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {

            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }


    public static int climbStairsTopDown(int[] dp, int n) {

        if (n == 0) return 1;
        if (n == 1) return 1;
        if (n == 2) return 2;

        if (dp[n] == 0) {

            dp[n] = climbStairsTopDown(dp, n - 1) + climbStairsTopDown(dp, n - 2);
        }

        return dp[n];
    }


    public static void main(String[] args) {


        System.out.println(" no of ways to climb stairs = " + climbStairsBottomUp(4));

        int n = 3;

        int[] dp = new int[n + 1];

        System.out.println(" no of way to climb stairs - Top down = " + climbStairsTopDown(dp, n));
    }
}
