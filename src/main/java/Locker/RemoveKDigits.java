package Locker;

import java.util.Stack;

public class RemoveKDigits {


    public static String removeKdigits(String num, int k) {

        if (k == 0) {
            return num;
        }

        if (k == num.length()) {

            return "";
        }

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < num.length(); i++) {

            while (!stack.isEmpty() && k > 0 && stack.peek() > num.charAt(i)) {

                stack.pop();
                k--;
            }

            stack.push(num.charAt(i));

        }

        for (int i = 0; i < k; i++) {

            stack.pop();
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {

            sb.append(stack.pop());
        }

        sb.reverse();

        System.out.println(sb.toString());

        while (sb.length() > 1 && sb.charAt(0) == '0') {

            sb.deleteCharAt(0);
        }

        return sb.toString();
    }


    public static void main(String[] args) {


        String num = "1432219";


        System.out.println(removeKdigits(num,3));

    }
}
