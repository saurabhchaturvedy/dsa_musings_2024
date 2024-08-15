package CodingSimplified.Array.A002;

import java.util.HashSet;
import java.util.Set;

public class FirstRepeating {


    public int firstRepeating(int[] arr) {

        if (arr.length == 0) return -1;

        int firstRepeating = -1;

        Set<Integer> set = new HashSet<>();

        for (int i = arr.length - 1; i >= 0; i--) {

            if (set.contains(arr[i])) {

                firstRepeating = arr[i];
            } else {

                set.add(arr[i]);
            }
        }


        return firstRepeating;
    }


    public static void main(String[] args) {


        int[] arr = {2, 3, 4, 4, 3, 5, 7};

        FirstRepeating firstRepeating = new FirstRepeating();

        System.out.println(" First repeating : " + firstRepeating.firstRepeating(arr));
    }
}
