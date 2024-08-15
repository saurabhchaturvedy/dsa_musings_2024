package CodingSimplified.Array;

public class QuickSort {


    public void quicksort(int[] arr, int start, int end) {

        int pivot = 0;

        if (start < end) {

            pivot = start;
            int i = start;
            int j = end;

            while (i < j) {

                while (arr[i] <= arr[pivot] && i < end) i++;

                while (arr[j] > arr[pivot] && j >= 0) j--;

                if (i < j) {

                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }

            int temp = arr[pivot];
            arr[pivot] = arr[j];
            arr[j] = temp;


            quicksort(arr, start, j - 1);
            quicksort(arr, j + 1, end);
        }
    }


    public static void main(String[] args) {


        int[] arr = {12, 8, 30, 6, 15, 2, 1, 18};

        QuickSort quickSort = new QuickSort();

        quickSort.quicksort(arr, 0, arr.length - 1);

        for (int i = 0; i < arr.length; i++) {

            System.out.print(arr[i] + " ");
        }
    }
}
