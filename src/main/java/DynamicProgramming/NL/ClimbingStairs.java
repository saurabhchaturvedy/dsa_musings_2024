package DynamicProgramming.NL;

public class ClimbingStairs {


    public static int climbStairs(int n) {

        if (n == 1) return 1;

        int[] dp = new int[n + 1];

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {

            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static void main(String[] args) {


        Long now = System.currentTimeMillis();
        System.out.println(" steps need to climb 3 stairs = " + climbStairs(3));
        System.out.println(" time elapsed : "+(System.currentTimeMillis()-now)/1000+" seconds ");
    }
}
