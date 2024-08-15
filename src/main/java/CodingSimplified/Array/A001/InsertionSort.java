package CodingSimplified.Array.A001;

public class InsertionSort {


    public void insertionSort(int[] arr) {

        if (arr.length <= 1) {
            return;
        }


        for (int i = 1; i < arr.length; i++) {

            int key = arr[i];

            int j = i - 1;

            while (j >= 0 && arr[j] > key) {

                arr[j + 1] = arr[j];
                j = j - 1;
            }

            arr[j + 1] = key;

        }
    }


    public static void main(String[] args) {


        int[] arr = {5, 2, 14, 6, 3};

        InsertionSort insertionSort = new InsertionSort();

        insertionSort.insertionSort(arr);

        for (int i = 0; i < arr.length; i++) {

            System.out.print(arr[i] + " ");
        }
    }
}
