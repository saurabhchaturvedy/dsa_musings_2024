package Atlassian.Karat;

public class WordsFormedByCharacters {


    public static int countCharacters(String[] words, String chars) {

        int count=0;

        int[]ch = new int[26];

        for(char c : chars.toCharArray())
        {

            ch[c-'a']++;
        }

        for(String word : words)
        {


            int[]wordCount = new int[26];

            for(char c : word.toCharArray())
            {

                wordCount[c-'a']++;
            }

            boolean ok=true;

            for(int i=0; i<26; i++)
            {


                if(wordCount[i]>ch[i])
                {
                    ok=false;
                    break;
                }
            }

            if(ok)
            {

                count+=word.length();
            }

        }


        return count;
    }


    public static void main(String[] args) {


        String[]ch = {"cat","bt","hat","tree"};

        System.out.print(" count is = "+countCharacters(ch,"atach"));
    }
}
