package CodingSimplified.Array.A005.CircularArray;

public class CyclicSort {


    public static void cyclicSort(int[] arr) {

        if (arr.length <= 1) return;

        int i = 0;


        while (i < arr.length) {

            if (arr[i] != i + 1) {

                int otherIndex = arr[i] - 1;

                int temp = arr[otherIndex];
                arr[otherIndex] = arr[i];
                arr[i] = temp;
            } else {

                i++;
            }
        }
    }


    public static void main(String[] args) {


        int[] arr = {3, 4, 6, 2, 1, 5};

        cyclicSort(arr);

        for (int i = 0; i < arr.length; i++) {

            System.out.print(arr[i] + " ");
        }
    }
}
