package CodingSimplified.Array.A003;

import java.util.Deque;
import java.util.LinkedList;

public class MaxSubArraySizeK {


    public void maxSubArraySizeK(int[] arr, int k) {


        Deque<Integer> queue = new LinkedList<>();

        int i = 0;

        for (i = 0; i < k; i++) {

            while (!queue.isEmpty() && arr[i] >= arr[queue.peekLast()]) {

                queue.pollLast();
            }

            queue.addLast(i);
        }

        for (; i < arr.length; i++) {

            System.out.print(arr[queue.peek()] + " ");

            while (!queue.isEmpty() && i - k >= queue.peek()) {

                queue.pollFirst();
            }

            while (!queue.isEmpty() && arr[i] >= arr[queue.peekLast()]) {

                queue.pollLast();
            }


            queue.addLast(i);

        }

        System.out.print(arr[queue.peek()] + " ");
    }


    public static void main(String[] args) {


        int arr[] = {2, 5, 4, 3, 1, 7};
        int k = 3;

        MaxSubArraySizeK maxSubArraySizeK = new MaxSubArraySizeK();

        maxSubArraySizeK.maxSubArraySizeK(arr, k);
    }
}
