package Atlassian.PostKarat18Oct.PostKaratSat19Oct;

import java.util.ArrayList;
import java.util.List;

public class StringTransformation {


    public static int numberOfWays(String s, String t, long k) {

        final int MOD = 1000000007;


        if (s.equals(t) && k == 0) {
            return 1;
        }


        int n = s.length();

        List<Long> rotations = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            String rotatedString = s.substring(i) + s.substring(0, i);

            if (rotatedString.equals(t)) {

                rotations.add((long) i);
            }
        }


        if (rotations.isEmpty()) {
            return 0;
        }


        long[][] dp = new long[(int) (k + 1)][n];

        dp[0][0] = 1;


        for (long step = 1; step <= k; step++) {

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < n; j++) {


                    dp[(int) step][i] = (dp[(int) (step - 1)][(i - j + n) % n]) % MOD;
                }
            }
        }

        long result = 0;

        for (long rotation : rotations) {

            result = (result + dp[(int) k][(int) rotation]) % MOD;
        }

        return (int) result;
    }

    public static void main(String[] args) {


        String s = "abcd";
        String t = "cdab";

        int k = 2;

        System.out.println(" Number of ways : " + numberOfWays(s, t, k));
    }
}
