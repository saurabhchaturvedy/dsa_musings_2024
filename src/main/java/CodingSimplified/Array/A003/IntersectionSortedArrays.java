package CodingSimplified.Array.A003;

import java.util.LinkedList;
import java.util.List;

public class IntersectionSortedArrays {


    public List<Integer> intersectionSortedArrays(int[] first, int[] second) {

        int i = 0;
        int j = 0;

        List<Integer> finalList = new LinkedList<>();

        while (i < first.length && j < second.length) {

            while ((i < first.length - 1) && first[i] == first[i + 1]) {
                i++;
            }

            while ((j < second.length - 1) && second[j] == second[j + 1]) {
                j++;
            }


            if (first[i] < second[j]) {

                i++;
            } else if (first[i] > second[j]) {

                j++;

            } else {

                finalList.add(first[i]);
                i++;
                j++;
            }

        }

        return finalList;
    }

    public static void main(String[] args) {


        int[] first = {2, 4, 4, 5, 6, 7, 7};
        int[] second = {1, 2, 2, 2, 3, 4, 5, 7};


        IntersectionSortedArrays intersectionSortedArrays = new IntersectionSortedArrays();

        for (int x : intersectionSortedArrays.intersectionSortedArrays(first, second)) {

            System.out.print(x + " ");
        }
    }
}
