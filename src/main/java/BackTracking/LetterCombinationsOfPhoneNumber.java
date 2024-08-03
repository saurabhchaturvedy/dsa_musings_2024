package BackTracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfPhoneNumber {


    Map<Character, String> map = new HashMap<>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};


    List<String> response = new ArrayList<>();
    char[] ch;

    public List<String> letterCombinations(String digits) {

        ch = digits.toCharArray();


        if (ch.length != 0) {

            dfs(0, new StringBuilder());
        }

        return response;
    }

    private void dfs(int index, StringBuilder sb) {

        if (index == ch.length) {
            response.add(sb.toString());
            return;
        }


        String charactersOnKey = map.get(ch[index]);
        for (char c : charactersOnKey.toCharArray()) {
            sb.append(c);
            dfs(index + 1, sb);
            sb.setLength(sb.length() - 1);

        }
    }


    public static void main(String[] args) {


        LetterCombinationsOfPhoneNumber letterCombinationsOfPhoneNumber = new LetterCombinationsOfPhoneNumber();


        List<String> ab = letterCombinationsOfPhoneNumber.letterCombinations("23");

        System.out.println(ab);

    }
}
