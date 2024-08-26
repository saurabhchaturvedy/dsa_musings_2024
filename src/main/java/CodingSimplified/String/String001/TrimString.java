package CodingSimplified.String.String001;

public class TrimString {


    public static String trimString(String str) {

        if (str == null) {
            return null;
        }


        int start = 0;
        int end = 0;

        int i;
        for (i = 0; i < str.length(); i++) {

            if (str.charAt(i) != ' ') {
                start = i;
                break;
            }
        }

        i = 0;
        for (i = str.length() - 1; i > start - 1; i--) {

            if (str.charAt(i) != ' ') {
                end = i;
                break;
            }
        }


        int count = end - start + 1;

        return new String(str.toCharArray(), start, count);
    }


    public static void main(String[] args) {


        String str = "     Hello      ";

        String trimmedString = trimString(str);

        System.out.println(trimmedString);
        System.out.println(trimmedString.length());
    }
}
