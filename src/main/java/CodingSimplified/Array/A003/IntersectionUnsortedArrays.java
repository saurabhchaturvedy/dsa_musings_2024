package CodingSimplified.Array.A003;

import java.util.HashSet;
import java.util.Set;

public class IntersectionUnsortedArrays {


    public Set<Integer> intersectionUnsortedArray(int[] first, int[] second) {


        Set<Integer> seen = new HashSet<>();
        Set<Integer> finall = new HashSet<>();

        for (int x : first) {

            seen.add(x);
        }


        for (int x : second) {

            if (seen.contains(x)) {
                finall.add(x);
            }
        }

        return finall;
    }


    public static void main(String[] args) {


        int[] first = {4, 2, 3, 5, 4, 2, 3};
        int[] second = {9, 1, 7, 2, 4, 2, 1, 2};

        IntersectionUnsortedArrays intersectionUnsortedArrays = new IntersectionUnsortedArrays();

        for (int x : intersectionUnsortedArrays.intersectionUnsortedArray(first, second)) {

            System.out.print(x + " ");
        }
    }
}
