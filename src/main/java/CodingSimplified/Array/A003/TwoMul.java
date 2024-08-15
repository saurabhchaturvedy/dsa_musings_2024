package CodingSimplified.Array.A003;

import java.util.HashSet;
import java.util.Set;

public class TwoMul {


    public static boolean productExists(int[] arr, int target) {

        if (arr.length == 0) return false;

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {

            if (target % arr[i] == 0 && set.contains(target / arr[i])) {
                return true;
            }

            set.add(arr[i]);
        }

        return false;
    }


    public static void main(String[] args) {


        int[] arr = {1, 2, 3, 7, 5, 8};


        System.out.println(" product exists ? : " + productExists(arr, 15));
    }
}
