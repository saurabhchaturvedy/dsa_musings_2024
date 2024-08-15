package CodingSimplified.Array;

public class MergeSort {


    public void mergesort(int[] arr, int start, int end) {

        if (start != end) {

            int mid = start + ((end - start) / 2);
            mergesort(arr, start, mid);
            mergesort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }

    private void merge(int[] arr, int start, int mid, int end) {


        int i = start;
        int j = mid + 1;
        int k = 0;


        int[] temp = new int[end - start + 1];


        while ((i <= mid) && (j <= end)) {

            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {

                temp[k++] = arr[j++];
            }
        }


        while (i <= mid) {

            temp[k++] = arr[i++];
        }


        while (j <= end) {

            temp[k++] = arr[j++];
        }


        for (i = start; i <= end; i++) {

            arr[i] = temp[i - start];
        }
    }


    public static void main(String[] args) {


        int[] arr = {5, 14, 2, 3, 1};


        MergeSort mergeSort = new MergeSort();

        mergeSort.mergesort(arr, 0, arr.length - 1);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
