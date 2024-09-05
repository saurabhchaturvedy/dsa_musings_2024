package Learnings.WM202409;

public class Palindrome3 {



    public boolean validPalindrome(String s) {
        return canBePalindrome(s, 0, s.length() - 1, 2);
    }

    private boolean canBePalindrome(String s, int left, int right, int deletionsAllowed) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                if (deletionsAllowed == 0) {
                    return false;  // No more deletions allowed
                }
                // Try skipping either the left character or the right character
                return canBePalindrome(s, left + 1, right, deletionsAllowed - 1) ||
                        canBePalindrome(s, left, right - 1, deletionsAllowed - 1);
            }
            left++;
            right--;
        }
        return true;  // No mismatches found, or all mismatches resolved within allowed deletions
    }

    public static void main(String[] args) {
        Palindrome3 solution = new Palindrome3();

        System.out.println(solution.validPalindrome("abca"));  // Output: true
        System.out.println(solution.validPalindrome("racecar"));  // Output: true
        System.out.println(solution.validPalindrome("hello"));  // Output: false
        System.out.println(solution.validPalindrome("abcdef"));  // Output: false
        System.out.println(solution.validPalindrome("abccbaa"));  // Output: true
    }
}
