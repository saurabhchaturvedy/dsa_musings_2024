package Atlassian.PostKarat20Oct.Router.RegexPrimer;

public class StringMatchesExample {
    public static void main(String[] args) {
        String text = "HelloWorld";    // Input string containing only alphabetic characters
        String pattern = "[a-zA-Z]+";  // Regular expression to match alphabetic characters
        
        // Using String.matches() method
        boolean isMatch = text.matches(pattern);
        System.out.println("Does the string match? " + isMatch);  // Output: true
    }
}
