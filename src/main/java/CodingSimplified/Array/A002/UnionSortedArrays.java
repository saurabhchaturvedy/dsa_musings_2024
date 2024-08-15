package CodingSimplified.Array.A002;

import java.util.LinkedList;
import java.util.List;

public class UnionSortedArrays {


    public List<Integer> unionSortedArrays(int[] first, int[] second) {

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

                finalList.add(first[i++]);
            } else if (first[i] > second[j]) {

                finalList.add(second[j++]);

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

        UnionSortedArrays unionSortedArrays = new UnionSortedArrays();

        List<Integer> unionList = unionSortedArrays.unionSortedArrays(first, second);

        for (int x : unionList) {

            System.out.print(x + " ");
        }
    }
}
