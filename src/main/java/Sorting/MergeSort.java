package Sorting;

public class MergeSort {


    public void mergesort(int[] arr, int l, int r) {


        if (l < r) {

            int mid = (l + r) >>> 1;
            mergesort(arr, l, mid);
            mergesort(arr, mid + 1, r);
            merge(arr, l, mid, r);
        }
    }

    private void merge(int[] arr, int l, int mid, int r) {

        int n1 = mid - l + 1;
        int n2 = r - mid;


        int[] left = new int[n1];
        int[] right = new int[n2];

        for (int i = 0; i < n1; i++) {

            left[i] = arr[l + i];
        }

        for (int j = 0; j < n2; j++) {

            right[j] = arr[mid + 1 + j];
        }


        int i = 0;
        int j = 0;
        int k = l;


        while (i < n1 && j < n2) {

            if (left[i] <= right[j]) {

                arr[k] = left[i];
                i++;
            } else {

                arr[k] = right[j];
                j++;
            }

            k++;
        }


        while (i < n1) {

            arr[k] = left[i];
            i++;
            k++;
        }


        while (j < n2) {

            arr[k] = right[j];
            j++;
            k++;
        }
    }


    public static void main(String[] args) {


        int[] arr = {1, 7, 3, 9, 11, 8, 17, 15, 19, 13};

        MergeSort mergeSort = new MergeSort();

        mergeSort.mergesort(arr, 0, arr.length - 1);

        for (int x : arr) {

            System.out.print(x + " ");
        }
    }
}
