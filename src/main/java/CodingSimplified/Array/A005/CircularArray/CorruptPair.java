package CodingSimplified.Array.A005.CircularArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CorruptPair {


    public static List<Integer> corruptPair(int[] arr) {

        if (arr.length == 0) return new ArrayList<>();


        int i = 0;

        while (i < arr.length) {

            if (arr[i] != arr[arr[i] - 1]) {

                int otherIndex = arr[i] - 1;

                int temp = arr[otherIndex];
                arr[otherIndex] = arr[i];
                arr[i] = temp;
            } else {

                i++;
            }
        }


        for (i = 0; i < arr.length; i++) {

            if (arr[i] != i + 1) {

                return Arrays.asList(arr[i], i + 1);
            }
        }


        return new ArrayList<>();
    }


    public static void main(String[] args) {


        int[] arr = {4, 3, 4, 5, 1};

        List<Integer> integers = corruptPair(arr);

        System.out.println(integers);
    }
}
