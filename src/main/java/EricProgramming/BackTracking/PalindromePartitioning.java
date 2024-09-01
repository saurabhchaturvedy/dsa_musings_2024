package EricProgramming.BackTracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PalindromePartitioning {


    List<List<String>> result = new ArrayList<>();
    char[] ch;
    String str;


    public List<List<String>> palindromePartitioning(String str) {

        this.ch = str.toCharArray();
        this.str = str;
        dfs(new LinkedList<>(), 0);
        return result;
    }

    private void dfs(LinkedList<String> currComb, int index) {

        if (index >= str.length()) {
            result.add(new ArrayList<>(currComb));
            return;
        }


        for (int i = index; i < str.length(); i++) {

            if (!isPalindrome(index, i)) continue;
            currComb.add(str.substring(index, i + 1));
            dfs(currComb, i + 1);
            currComb.remove(currComb.size() - 1);
        }
    }

    private boolean isPalindrome(int start, int end) {

        if (start < end) {

            if (ch[start] != ch[end])
                return false;

            start++;
            end--;
        }

        return true;
    }


    public static void main(String[] args) {


        String str = "aab";

        PalindromePartitioning palindromePartitioning = new PalindromePartitioning();

        List<List<String>> lists = palindromePartitioning.palindromePartitioning(str);

        System.out.println(lists);
    }
}
