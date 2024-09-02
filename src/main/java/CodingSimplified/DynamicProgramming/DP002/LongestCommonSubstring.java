package CodingSimplified.DynamicProgramming.DP002;

public class LongestCommonSubstring {


    public static int longestCommonSubstringRecursive(String s1, String s2) {


        return lcs(s1, s2, s1.length(), s2.length(), 0);
    }

    private static int lcs(String s1, String s2, int m, int n, int lcsCount) {

        if (m == 0 || n == 0) {
            return lcsCount;
        }


        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {

            lcsCount = lcs(s1, s2, m - 1, n - 1, lcsCount + 1);
        }


        int count_one = lcs(s1, s2, m, n - 1, 0);
        int count_two = lcs(s1, s2, m - 1, n, 0);

        return Math.max(lcsCount, Math.max(count_one, count_two));
    }


    public static void main(String[] args) {

        String s1 = "abd";
        String s2 = "abca";

        System.out.println(" longest common substring = " + longestCommonSubstringRecursive(s1, s2));
    }
}
