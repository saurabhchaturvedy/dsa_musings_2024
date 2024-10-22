package Atlassian.PostKarat20Oct.Router.RegexPrimer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AsteriskRegexMatch {


    public static void main(String[] args) {
        String text = "This is a test string.";
        String regex = ".*"; // Matches any string

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()) {
            System.out.println("The entire string matches: " + matcher.group());
        }
    }
}
