package CodingSimplified.String.String001;

public class ReverseString {


    public static String reverse(String str) {

        if (str == null) {
            return null;
        }
        char[] ch = str.toCharArray();
        int start = 0;
        int end = ch.length - 1;

        while (start < end) {

            char temp = ch[start];
            ch[start] = ch[end];
            ch[end] = temp;

            start++;
            end--;
        }


        return new String(ch);
    }


    public static void main(String[] args) {


        System.out.println(" reversed string is = " + reverse("tajmahal"));
    }
}
