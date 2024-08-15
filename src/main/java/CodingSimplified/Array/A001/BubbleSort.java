package CodingSimplified.Array.A001;

public class BubbleSort {


    public void bubbleSort(int[] arr) {


        if (arr.length == 0 || arr.length == 1) {
            return;
        }


        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = 0; j < arr.length - 1 - i; j++) {

                if (arr[j] > arr[j + 1]) {

                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }


    public static void main(String[] args) {


        int[] arr = {12, 5, 3, -1};

        BubbleSort bubbleSort = new BubbleSort();

        bubbleSort.bubbleSort(arr);

        for (int i = 0; i < arr.length; i++) {

            System.out.print(arr[i] + " ");
        }
    }
}
