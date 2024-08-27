package CodingSimplified.String.String001;

import java.util.HashSet;
import java.util.Set;

public class UniqueCharacters {


    public static int uniqueCharacterCount(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        Set<Character> set = new HashSet<>();

        for (char c : str.toCharArray()) {

            set.add(c);
        }

        return set.size();
    }


    public static void main(String[] args) {


        System.out.println(" unique character count = " + uniqueCharacterCount("hello"));
    }
}
