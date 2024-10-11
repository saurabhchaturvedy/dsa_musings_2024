package Atlassian.KaratFriday;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WrapLines1 {



    public static List<String> wrapLines(List<String> words,int maxWidth)
    {

        List<String> currentLine = new ArrayList<>();
        List<String> wrapped = new ArrayList<>();

        int currentLength=0;

        for(String word : words)
        {


            int currentLineSize = currentLine.size()>0 ? 1 : 0;

            if(currentLength + currentLineSize + word.length() <=maxWidth)
            {

                if(!currentLine.isEmpty())
                {

                    currentLine.add("-");
                    currentLength++;
                }

                currentLine.add(word);
                currentLength = currentLength + word.length();
            }
            else {


                if(!currentLine.isEmpty())
                {

                    wrapped.add(String.join("",currentLine));


                }

                currentLine = new ArrayList<>();
                currentLine.add(word);
                currentLength = word.length();
            }
        }


        if(!currentLine.isEmpty())
        {
            wrapped.add(String.join("",currentLine));

        }

        return wrapped;
    }


    public static void main(String[] args) {


        String[]words = { "The", "day", "began", "as", "still", "as", "the",
                "night", "abruptly", "lighted", "with", "brilliant",
                "flame" };


        List<String> wrappedLines = wrapLines(Arrays.asList(words),13);

        System.out.println(wrappedLines);
    }

}
