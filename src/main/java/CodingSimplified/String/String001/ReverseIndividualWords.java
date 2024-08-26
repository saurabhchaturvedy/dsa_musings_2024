package CodingSimplified.String.String001;

import java.util.Stack;

public class ReverseIndividualWords {


    public static String reverseWords(String s) {


        if (s == null || s.length() == 0) {
            return null;
        }

        String tempStr = "";
        String finalStr = "";

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (c != ' ') {

                tempStr = c + tempStr;
            } else {
                finalStr = finalStr + tempStr + " ";
                tempStr = "";
            }
        }

        finalStr = finalStr + tempStr;

        return finalStr;

    }


    public static String reverseWordsStack(String s) {


        if (s == null || s.length() == 0) {
            return null;
        }

        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) != ' ') {

                stack.push(s.charAt(i));
            } else {

                while (!stack.isEmpty()) {

                    sb.append(stack.pop());
                }
                sb.append(" ");
            }
        }

        while (!stack.isEmpty()) {

            sb.append(stack.pop());
        }


        return sb.toString();
    }


    public static String reverseWordsStringBuilder(String s) {


        if (s == null || s.length() == 0) {
            return null;
        }

        StringBuilder tempStr = new StringBuilder();
        StringBuilder finalStr = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (s.charAt(i) != ' ') {

                tempStr.insert(0, c);
            } else {

                finalStr.append(tempStr).append(" ");
                tempStr.setLength(0);

            }
        }
        finalStr.append(tempStr);

        return finalStr.toString();

    }


    public static void main(String[] args) {


        System.out.println(" string with individually reversed words = " + reverseWords("This is India"));
        System.out.println(" string with individually reversed words = " + reverseWordsStringBuilder("This is India"));
        System.out.println(" string with individually reversed words = " + reverseWordsStack("This is India"));
    }
}
