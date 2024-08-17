package CodingSimplified.Array.A005;

import java.util.ArrayList;
import java.util.List;

public class SubArrayProductLessThanK {


    public static List<List<Integer>> subArrayProductLessThanK(int[] arr, int k) {

        int multiplication = 1;
        int start = 0;


        List<List<Integer>> products = new ArrayList<>();


        for (int i = 0; i < arr.length; i++) {

            multiplication = multiplication * arr[i];

            while (multiplication >= k) {
                multiplication = multiplication / arr[start];
                start++;
            }

            List<Integer> list = new ArrayList<>();

            for (int j = i; j >= 0; j--) {

                list.add(0, arr[j]);
                products.add(new ArrayList<>(list));

            }
        }

        return products;
    }


    public static void main(String[] args) {


        int[] arr = {10, 5, 2, 6};

        List<List<Integer>> list = subArrayProductLessThanK(arr, 100);

        System.out.println(list);
    }
}
