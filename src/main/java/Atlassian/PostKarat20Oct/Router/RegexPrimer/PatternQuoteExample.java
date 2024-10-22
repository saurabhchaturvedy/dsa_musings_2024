package Atlassian.PostKarat20Oct.Router.RegexPrimer;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PatternQuoteExample {
    public static void main(String[] args) {
        // String with special regex characters
        String specialString = "abc.def*ghi?";

        // Use Pattern.quote to escape the special characters
        String quotedString = Pattern.quote(specialString);
        
        // Now you can use the quoted string in a regex pattern
        String regexPattern = quotedString;

        // Test string to search
        String text = "Here is the string: abc.def*ghi? and more text.";

        // Create a Pattern and Matcher
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(text);
        
        // Check if the pattern matches
        if (matcher.find()) {
            System.out.println("Matched: " + matcher.group());
        } else {
            System.out.println("No match found.");
        }
    }
}
