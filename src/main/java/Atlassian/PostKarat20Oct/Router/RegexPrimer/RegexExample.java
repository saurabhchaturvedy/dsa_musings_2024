package Atlassian.PostKarat20Oct.Router.RegexPrimer;

import java.util.regex.*;

public class RegexExample {
    public static void main(String[] args) {
        String text = "abc123xyz";
        String pattern = "\\d+";  // regular expression to match one or more digits
        
        // Compile the pattern
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(text);
        
        // Using matches() method
        boolean isMatch = matcher.matches();
        System.out.println("matches(): " + isMatch);  // Output: false
        
        // Using find() method
        matcher = compiledPattern.matcher(text);  // Reset matcher
        boolean isFound = matcher.find();
        System.out.println("find(): " + isFound);  // Output: true
    }
}
