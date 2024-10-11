package Atlassian.KaratFriday;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class WrapLines2 {


    public static List<String> wrapLines2(List<String> lines, int maxWidth) {

        List<String> wrapped = new ArrayList<>();
        List<String> currentLine = new ArrayList<>();
        int currentLength = 0;


        List<String> words = new ArrayList<>();

        for (String line : lines) {

            String[] w = line.split(" ");

            for (String sw : w) {
                words.add(sw);
            }
        }


        for (String word : words) {


            if (currentLength + currentLine.size() + word.length() <= maxWidth) {

                currentLine.add(word);
                currentLength = currentLength + word.length();
            } else {


                wrapped.add(justifyText(currentLine, maxWidth));

                currentLine = new ArrayList<>();
                currentLine.add(word);
                currentLength = word.length();
            }
        }
        if (!currentLine.isEmpty()) {

            wrapped.add(justifyText(currentLine, maxWidth));
        }


        return wrapped;

    }

    private static String justifyText(List<String> words, int maxWidth) {

        if (words.size() == 1) {
            return words.get(0);
        }

        int totalCharacters = words.stream().mapToInt(String::length).sum();
        int totalSpaces = maxWidth - totalCharacters;

        int spacesToFill = totalSpaces / (words.size() - 1);
        int extraSpaces = totalSpaces % (words.size() - 1);


        StringBuilder justifiedLine = new StringBuilder();

        String str = "";
        for (int i = 0; i < words.size(); i++) {

            justifiedLine.append(words.get(i));

            if (i < words.size() - 1) {

                for (int k = 0; k < spacesToFill; k++) {
                    str += "-";
                }


                justifiedLine.append(str);

                if (extraSpaces > 0) {

                    justifiedLine.append("-");
                    extraSpaces--;
                }
            }

            str = "";

        }

        return justifiedLine.toString();
    }

    public static void main(String[] args) {


        String[] lines = {"The day began as still as the",
                "night abruptly lighted with",
                "brilliant flame"};


        List<String> wrapped = wrapLines2(Arrays.asList(lines), 24);

        System.out.println(wrapped);
    }
}
