package CodingSimplified.Array.A005;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FirstKMissingPositiveNumber {


    static List<Integer> firstKMissingPositive(int[] arr, int k) {


        if (arr.length == 0 || k < 1) return new ArrayList<>();

        int i = 0;

        while (i < arr.length) {

            if (arr[i] > 0 && arr[i] <= arr.length && arr[i] != arr[arr[i] - 1]) {

                int otherIndex = arr[i] - 1;

                int temp = arr[otherIndex];
                arr[otherIndex] = arr[i];
                arr[i] = temp;
            } else {

                i++;
            }
        }


        List<Integer> missing = new ArrayList<>();

        Set<Integer> otherNumber = new HashSet<>();


        for (i = 0; i < arr.length && missing.size() < k; i++) {

            if (arr[i] != i + 1) {

                missing.add(i + 1);
                otherNumber.add(arr[i]);
            }
        }

        for (int j = i; missing.size() < k; j++) {

            if (!otherNumber.contains(j + 1)) {

                missing.add(j + 1);
            }
        }


        return missing;
    }

    public static void main(String[] args) {


        int[] arr = {-2, 11, 1, -3, 2, 8, 4};

        List<Integer> integers = firstKMissingPositive(arr, 5);

        System.out.println(integers);

    }
}
