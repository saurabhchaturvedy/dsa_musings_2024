package CodingSimplified.DynamicProgramming.DP002;

import java.util.Arrays;

public class LongestCommonSubsequence {


    public static int longestCommonSubsequenceRecursive(String text1, String text2) {


        return lcs(text1, text2, text1.length(), text2.length());

    }


    public static int lcs(String s1, String s2, int m, int n) {

        if (m == 0 || n == 0) {
            return 0;
        }


        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {

            return 1 + lcs(s1, s2, m - 1, n - 1);
        }


        return Math.max(lcs(s1, s2, m, n - 1), lcs(s1, s2, m - 1, n));
    }


    public static int longestCommonSubsequenceTopDown(String text1, String text2) {


        int[][] lcs = new int[text1.length() + 1][text2.length() + 1];

        Arrays.stream(lcs).forEach(row -> {

            Arrays.fill(row, -1);
        });

        return lcs(text1, text2, text1.length(), text2.length(), lcs);

    }


    public static int lcs(String s1, String s2, int n, int m, int[][] arr) {

        if (n == 0 || m == 0) {
            return 0;
        }

        if (arr[n][m] == -1) {
            if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
                arr[n][m] = 1 + lcs(s1, s2, n - 1, m - 1, arr);
            } else {
                arr[n][m] = Math.max(lcs(s1, s2, n - 1, m, arr), lcs(s1, s2, n, m - 1, arr));
            }
        }

        return arr[n][m];
    }


    public static int longestCommonSubsequenceBottomUp(String text1, String text2) {

        return lcsBottomUp(text1, text2, text1.length(), text2.length());

    }

    public static int lcsBottomUp(String s1, String s2, int m, int n) {

        if (m == 0 || n == 0) {
            return 0;
        }

        int[][] lcs = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {

                    lcs[i][j] = 1 + lcs[i - 1][j - 1];
                } else {

                    lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
                }
            }
        }

        return lcs[m][n];
    }


    public static void main(String[] args) {


        String text1 = "abcde";
        String text2 = "ace";


        System.out.println(" LCS recursive = " + longestCommonSubsequenceRecursive(text1, text2));
        System.out.println(" LCS top down = " + longestCommonSubsequenceTopDown(text1, text2));
        System.out.println(" LCS bottom up = " + longestCommonSubsequenceBottomUp(text1, text2));

    }
}
