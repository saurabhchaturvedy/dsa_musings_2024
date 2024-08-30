package Learnings.WM202409;

public class PalindromeMinimum2Deletion {


    public static boolean isPalindrome(char[] ch, int start, int end) {

        if (ch.length == 0) return false;

        while (start < end) {

            if (ch[start] != ch[end]) {

                return true;
            }

            start++;
            end--;
        }

        return false;
    }


    public static boolean isPalindromeMinimum2Deletion(String str) {


        char[] ch = str.toCharArray();

        int start = 0;
        int end = ch.length - 1;
        int deletions = 0;

        while (start < end) {

            if (ch[start] == ch[end]) {

                start++;
                end--;
            } else {


                if (isPalindrome(ch, start + 1, end) || isPalindrome(ch, start, end - 1)) {
                    deletions++;
                } else {

                    deletions = deletions + 2;
                }

                break;
            }


        }

        return deletions == 2;
    }


    public static void main(String[] args) {


        String input = "abecbea";

        System.out.println(" can string be palindrome after 2 deletions ? " + isPalindromeMinimum2Deletion(input));
    }
}
