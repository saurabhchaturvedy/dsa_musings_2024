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


    public static void main(String[] args) {


        String s = "bbbab";


        System.out.println(" longest palindromic substring = " + longestPalindromeSubseq(s));
    }
}
