package Atlassian.PostKarat20Oct.Router.RegexPrimer;

import java.util.regex.*;

public class MatcherGroupExample {
    public static void main(String[] args) {
        String text = "Today's date is 22-10-2024";  // Input string containing a date
        String pattern = "(\\d{2})-(\\d{2})-(\\d{4})";  // Regular expression with capturing groups
        
        // Compile the pattern
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(text);
        
        // Using find() method to locate the date in the string
        if (matcher.find()) {
            // Retrieve the entire match (the date itself)
            System.out.println("Full match: " + matcher.group(0));  // Output: 22-10-2024
            
            // Retrieve each capturing group (day, month, year)
            System.out.println("Day: " + matcher.group(1));    // Output: 22
            System.out.println("Month: " + matcher.group(2));  // Output: 10
            System.out.println("Year: " + matcher.group(3));   // Output: 2024
        }
    }
}
