package Atlassian.PostKarat20Oct.Router.RegexPrimer;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MatcherMatcherExample {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("a*b");
        Matcher matcher = pattern.matcher("aaab"); // Creates a Matcher for the string "aaab"

        if (matcher.find()) { // Using find() to check for a match
            System.out.println("Found a match: " + matcher.group());
        }
    }
}
