package CodingSimplified.Array.A003;

import java.util.HashSet;
import java.util.Set;

public class UnionUnsortedArrays {


    public Set<Integer> unionUnsortedArrays(int[] first, int[] second) {

        Set<Integer> set = new HashSet<>();

        for (int x : first) {

            set.add(x);
        }


        for (int x : second) {

            set.add(x);
        }

        return set;

    }


    public static void main(String[] args) {


        int[] first = {4, 2, 3, 5, 4, 2, 3};
        int[] second = {9, 1, 7, 2, 4, 2, 1, 2};

        UnionUnsortedArrays unionUnsortedArrays = new UnionUnsortedArrays();

        for (int x : unionUnsortedArrays.unionUnsortedArrays(first, second)) {


            System.out.print(x + " ");
        }
    }
}
