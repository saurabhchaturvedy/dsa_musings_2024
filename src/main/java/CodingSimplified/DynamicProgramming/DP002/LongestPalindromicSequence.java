package CodingSimplified.DynamicProgramming.DP002;

public class LongestPalindromicSequence {


    public static int longestPalindromeSubseq(String s) {


        if (s == null) {
            return 0;
        }

        return lps(s, 0, s.length() - 1);

    }


    static int lps(String s, int start, int end) {

        if (s == null) {
            return 0;
        }

        if (start > end) {
            return 0;
        }

        if (start == end) {
            return 1;
        }

        System.out.println(start);
        System.out.println(end);

        if (s.charAt(start) == s.charAt(end)) {

            return 2 + lps(s, start + 1, end - 1);
        }


        return Math.max(lps(s, start + 1, end), lps(s, start, end - 1));
    }


    static int lps(String s, int start, int end, Integer[][] dp) {

        if (start > end) {
            return 0;
        }

        if (start == end) {
            return 1;
        }

        //  System.out.println(start);
        //System.out.println(end);

        if (dp[start][end] == null) {
            if (s.charAt(start) == s.charAt(end)) {

                dp[start][end] = 2 + lps(s, start + 1, end - 1, dp);
            } else {

                dp[start][end] = Math.max(lps(s, start + 1, end, dp), lps(s, start, end - 1, dp));
            }
        }
        return dp[start][end];

    }


    public static int longestPalindromeSubseqBottomUp(String s) {

        int[][] dp = new int[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {

            dp[i][i] = 1;
        }

        for (int i = s.length() - 2; i >= 0; i--) {

            for (int j = i + 1; j < s.length(); j++) {

                if (s.charAt(i) == s.charAt(j)) {

                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {

                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }

        }
        return dp[0][s.length() - 1];
    }


    public static void main(String[] args) {


        String s = "bbbab";


        System.out.println(" longest palindromic substring = " + longestPalindromeSubseq(s));

        System.out.println(" longest palindromic substring = " + lps(s, 0, s.length() - 1, new Integer[s.length()][s.length()]));

        System.out.println("longest palindromic string bottom up = " + longestPalindromeSubseqBottomUp(s));
    }
}
