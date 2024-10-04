package Atlassian;

import java.util.Stack;

public class DailyTemperatures {


    public static int[] dailyTemperatures(int[] arr) {

        if (arr.length == 0) return arr;

        Stack<Integer> stack = new Stack<>();

        int[] result = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {


            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {

                int lastIndex = stack.pop();
                result[lastIndex] = i - lastIndex;
            }


            stack.push(i);

        }


        return result;

    }

    public static void main(String[] args) {


        int[] result = dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});

        for (int x : result) {

            System.out.print(x + " ");
        }
    }

}
