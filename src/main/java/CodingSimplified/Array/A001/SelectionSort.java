package CodingSimplified.Array.A001;

public class SelectionSort {


    public void selectionSort(int[] arr) {

        if (arr.length <= 1) {
            return;
        }


        for (int i = 0; i < arr.length - 1; i++) {

            int min_index = i;

            for (int j = i + 1; j < arr.length; j++) {

                if (arr[j] < arr[min_index]) {

                    min_index = j;
                }
            }


            if (min_index != i) {

                int temp = arr[min_index];
                arr[min_index] = arr[i];
                arr[i] = temp;
            }
        }
    }


    public static void main(String[] args) {


        int[] arr = {5, 14, 2, 3, 1};

        SelectionSort selectionSort = new SelectionSort();

        selectionSort.selectionSort(arr);

        for (int i = 0; i < arr.length; i++) {

            System.out.print(arr[i] + " ");
        }
    }
}
