package CodingSimplified.String.String001;

public class ReverseWordsInString {


    public static String reverseWords(String s) {

        if (s == null || s.length() == 0) {
            return null;
        }

        char[] ch = reverse(s.toCharArray(), 0, s.length() - 1);

        int start = 0;
        int end = 0;

        for (int i = 0; i < ch.length; i++) {


            if (ch[i] != ' ') {

                ch[end++] = ch[i];
            } else if (i > 0 && ch[i - 1] != ' ') {

                reverse(ch, start, end - 1);
                ch[end++] = ' ';
                start = end;
            }


        }
        reverse(ch, start, end - 1);
        end = end > 0 && ch[end - 1] == ' ' ? end - 1 : end;
        return new String(ch, 0, end);
    }


    public static char[] reverse(char[] ch, int s, int e) {

        while (s < e) {

            char temp = ch[s];
            ch[s] = ch[e];
            ch[e] = temp;
            s++;
            e--;
        }

        return ch;
    }


    public static void main(String[] args) {


        System.out.println(" string with words in reversed order is =" + reverseWords("Map my india"));
        System.out.println(" string with words in reversed order is =" + reverseWords("   Map my india      "));
        System.out.println(" string with words in reversed order is =" + reverseWords("Map my         india"));
        System.out.println(" string with words in reversed order is =" + reverseWords("   Map my         india         "));

    }
}
