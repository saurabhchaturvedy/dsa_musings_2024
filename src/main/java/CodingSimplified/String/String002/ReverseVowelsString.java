package CodingSimplified.String.String002;

public class ReverseVowelsString {


    public static String reverseVowels(String s) {


        char[] ch = s.toCharArray();

        int start = 0;
        int end = ch.length - 1;

        while (start < end) {


            if (!isVowel(ch[start])) {

                start++;
                continue;
            }

            if (!isVowel(ch[end])) {

                end--;
                continue;
            }

            char temp = ch[start];
            ch[start] = ch[end];
            ch[end] = temp;

            start++;
            end--;
        }
        return new String(ch);
    }


    public static boolean isVowel(char c) {

        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {

            return true;
        }

        return false;
    }


    public static void main(String[] args) {


        String str = "hello";


        System.out.println("String after reversed vowels : " + reverseVowels(str));

    }

}
