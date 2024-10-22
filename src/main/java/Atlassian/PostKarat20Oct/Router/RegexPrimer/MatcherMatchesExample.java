package Atlassian.PostKarat20Oct.Router.RegexPrimer;

import java.util.regex.*;

public class MatcherMatchesExample {
    public static void main(String[] args) {
        String text = "12345";      // Input string containing only digits
        String pattern = "\\d+";    // Regular expression to match one or more digits

        // Compile the pattern
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(text);

        // Using matches() method
        boolean isMatch = matcher.matches();
        System.out.println("matches(): " + isMatch);  // Output: true
    }
}
