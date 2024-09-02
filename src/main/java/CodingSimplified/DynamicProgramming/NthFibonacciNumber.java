package CodingSimplified.DynamicProgramming;

public class NthFibonacciNumber {


    public static int fib(int n) {


        if (n < 2) {
            return n;
        }


        return fib(n - 1) + fib(n - 2);

    }


    public static int fibTopToBottom(int n) {

        int[] dp = new int[n + 1];
        return fib(dp, n);

    }

    public static int fib(int[] dp, int n) {

        if (n < 2) {
            return n;
        }

        if (dp[n] == 0) {

            dp[n] = fib(dp, n - 1) + fib(dp, n - 2);
        }

        return dp[n];
    }


    public static int fibBottomToTop(int n) {

        if (n < 2) {
            return n;
        }

        int[] dp = new int[n + 1];


        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {

            dp[i] = dp[i - 1] + dp[i - 2];
        }


        return dp[n];
    }


    public static void main(String[] args) {


        System.out.println(" nth fibonacci number is = " + fib(2));

        System.out.println("nth fibonacci number top to bottom : " + fibTopToBottom(2));
        System.out.println("nth fibonacci number bottom to top : " + fibBottomToTop(2));

    }


}
