package Learnings.WM202409;

public class ValidPalindrome2 {



    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                // Skip either the left character or the right character
                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        ValidPalindrome2 solution = new ValidPalindrome2();

        System.out.println(solution.validPalindrome("abca"));  // Output: true
        System.out.println(solution.validPalindrome("racecar"));  // Output: true
        System.out.println(solution.validPalindrome("hello"));  // Output: false
    }
}
