package Atlassian.Karat;

import java.util.ArrayList;
import java.util.List;

public class WordWrap {

    public static List<String> wrapLines(String[] words, int maxLength) {
        List<String> wrappedLines = new ArrayList<>();
        List<String> currentLine = new ArrayList<>();
        int currentLength = 0;

        for (String word : words) {
            // Check if the word can fit in the current line
            int currentLineSize = currentLine.size() > 0 ? 1 : 0;
            int overallCurrentLineSize = currentLength + word.length() + currentLineSize;
            if (overallCurrentLineSize <= maxLength) {
                if (!currentLine.isEmpty()) { // If there's already a word in the line, add '-'
                    currentLine.add("-");
                    currentLength++; // account for the '-'
                }
                currentLine.add(word);
                currentLength += word.length();
            } else {
                // If current line is not empty, join and add to wrappedLines
                if (!currentLine.isEmpty()) {
                    wrappedLines.add(String.join("", currentLine));
                }
                // Start a new line with the current word
                currentLine = new ArrayList<>();
                currentLine.add(word);
                currentLength = word.length();
            }
        }

        // Add the last line if there are any remaining words
        if (!currentLine.isEmpty()) {
            wrappedLines.add(String.join("", currentLine));
        }

        return wrappedLines;
    }

    public static void main(String[] args) {
        String[] words1 = {
                "The", "day", "began", "as", "still", "as", "the",
                "night", "abruptly", "lighted", "with", "brilliant",
                "flame"
        };
        System.out.println(wrapLines(words1, 13)); // Expected output: ["The-day-began", "as-still-as", "the-night", "abruptly", "lighted-with", "brilliant", "flame"]
//        System.out.println(wrapLines(words1, 12)); // Expected output: ["The-day", "began-as", "still-as-the", "night", "abruptly", "lighted-with", "brilliant", "flame"]
//        System.out.println(wrapLines(words1, 20)); // Expected output: ["The-day-began-as", "still-as-the-night", "abruptly-lighted", "with-brilliant-flame"]
//
//        String[] words2 = {"Hello"};
//        System.out.println(wrapLines(words2, 5)); // Expected output: ["Hello"]
//        System.out.println(wrapLines(words2, 30)); // Expected output: ["Hello"]
//
//        String[] words3 = {"Hello", "Hello"};
//        System.out.println(wrapLines(words3, 5)); // Expected output: ["Hello", "Hello"]
//
//        String[] words4 = {"Well", "Hello", "world"};
//        System.out.println(wrapLines(words4, 5)); // Expected output: ["Well", "Hello", "world"]
//
//        String[] words5 = {"Hello", "HelloWorld", "Hello", "Hello"};
//        System.out.println(wrapLines(words5, 20)); // Expected output: ["Hello-HelloWorld", "Hello-Hello"]
//
//        String[] words6 = {"a", "b", "c", "d"};
//        System.out.println(wrapLines(words6, 20)); // Expected output: ["a-b-c-d"]
//        System.out.println(wrapLines(words6, 4));  // Expected output: ["a-b", "c-d"]
//        System.out.println(wrapLines(words6, 1));  // Expected output: ["a", "b", "c", "d"]
    }
}
