package Sorting;

public class QuickSort {


    public void quicksort(int[] arr, int l, int r) {


        if (l < r) {

            int pivotIndex = partition(arr, l, r);
            quicksort(arr, l, pivotIndex - 1);
            quicksort(arr, pivotIndex + 1, r);
        }
    }

    private int partition(int[] arr, int l, int r) {


        int pivot = arr[r];

        int i = l - 1;

        int temp = 0;

        for (int j = l; j < r; j++) {


            if (arr[j] < pivot) {
                i++;

                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

            }
        }

        i++;

        temp = arr[i];
        arr[i] = arr[r];
        arr[r] = temp;

        return i;
    }


    public static void main(String[] args) {


        int[] arr = {1, 7, 3, 9, 11, 8, 17, 15, 19, 13};

        QuickSort quickSort = new QuickSort();


        quickSort.quicksort(arr, 0, arr.length - 1);

        for (int x : arr) {

            System.out.print(x + " ");
        }
    }
}
