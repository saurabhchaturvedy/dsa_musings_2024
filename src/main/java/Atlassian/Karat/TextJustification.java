package Atlassian.Karat;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {


    public List<String> fullJustify(String[] words, int maxWidth) {

        List<String> lines = new ArrayList<>();
        int n = words.length;

        int i = 0;

        while (i < n) {

            int currentLength = words[i].length();
            int j = i + 1;
            int spacesToFill = 0;

            while (j < n && currentLength + spacesToFill + words[j].length() + 1 <= maxWidth) {

                currentLength += words[j].length();
                spacesToFill += 1;
                j++;
            }

            int availableSpaces = maxWidth - currentLength;
            int spacePerSlot = spacesToFill == 0 ? 0 : availableSpaces / spacesToFill;
            int extraSpaces = spacesToFill == 0 ? 0 : availableSpaces % spacesToFill;

            if (j == n) {
                spacePerSlot = 1;
                extraSpaces = 0;

            }

            String line = justifyLine(i, j, spacePerSlot, extraSpaces, maxWidth, words);
            lines.add(line);

            i = j;

        }

        return lines;
    }

    String justifyLine(int i, int j, int spacePerSlot, int extraSpaces, int maxWidth, String[] words) {

        StringBuilder s = new StringBuilder();

        for (int k = i; k < j; k++) {

            s.append(words[k]);

            if (k == j - 1) {

                continue;
            }

            for (int l = 1; l <= spacePerSlot; l++) {

                s.append(" ");
            }

            if (extraSpaces > 0) {

                s.append(" ");
                extraSpaces--;
            }

        }

        while (s.length() < maxWidth) {
            s.append(" ");
        }

        return s.toString();
    }


    public static void main(String[] args) {


        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;

        TextJustification textJustification = new TextJustification();

        List<String> strings = textJustification.fullJustify(words, maxWidth);

        System.out.println(" Justified String ");
        System.out.println(strings);
    }
}
