package CodingSimplified.DynamicProgramming.DP002;

public class EditDistance {


    public static int minDistance(String word1, String word2) {

        if (word1 == word2) {
            return 0;
        }

        return mindis(word1, word2, word1.length(), word2.length());

    }

    static int mindis(String s1, String s2, int m, int n) {

        if (m == 0) {
            return n;
        }

        if (n == 0) {

            return m;
        }

        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {

            return mindis(s1, s2, m - 1, n - 1);
        }

        return 1 + Math.min(mindis(s1, s2, m - 1, n - 1), Math.min(mindis(s1, s2, m, n - 1), mindis(s1, s2, m - 1, n)));
    }


    static int mindisBottomUp(String s1, String s2, int m, int n) {


        if (s1 == s2) {
            return 0;
        }


        if (m == 0) {
            return n;
        }

        if (n == 0) {
            return m;
        }

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[0][i] = i;
        }

        for (int i = 0; i <= n; i++) {

            dp[i][0] = i;
        }


        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= n; j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {

                    dp[i][j] = dp[i - 1][j - 1];
                } else {

                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
                }
            }
        }

        return dp[m][n];
    }


    public static int editOperationsBottomUp(String s1, String s2, int m, int n) {


        if(s1 == s2) {
            return 0;
        }

        if(n == 0) {
            return m;
        }

        if(m == 0) {
            return n;
        }

        int[][] arr = new int[n + 1][m + 1];

        for(int i = 0; i < m; i++) {
            arr[0][i] =  i;
        }

        for(int i = 0; i < n; i++) {
            arr[i][0] =  i;
        }

        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {

                if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    arr[i][j] = arr[i - 1][j - 1];
                } else {
                    arr[i][j] = 1 + Math.min(arr[i - 1][j - 1], Math.min(arr[i][j - 1], arr[i - 1][j]));
                }
            }
        }

        return arr[n][m];
    }

    public static void main(String[] args) {

        String word1 = "horse";
        String word2 = "ros";


        System.out.println(" edit distance  = " + minDistance(word1, word2));
        System.out.println(" edit distance >>>> " + editOperationsBottomUp("bat", "bau", "bat".length(), "bau".length()));
    }

}
