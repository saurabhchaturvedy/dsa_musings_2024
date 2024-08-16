package CodingSimplified.Array.A004;

public class SquaresOfSortedArray {


    public static int[] squaresOfSortedArray(int[] arr) {


        int[] result = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {

            arr[i] = arr[i] * arr[i];
        }


        int left = 0;
        int right = arr.length - 1;


        for (int end = arr.length - 1; end >= 0; end--) {

            if (arr[left] > arr[right]) {

                result[end] = arr[left];
                left++;
            } else {

                result[end] = arr[right];
                right--;
            }
        }

        return result;
    }


    public static void main(String[] args) {


        int arr[] = {-4, -2, -1, 3, 5};

        SquaresOfSortedArray squaresOfSortedArray = new SquaresOfSortedArray();

        int[] ints = squaresOfSortedArray(arr);

        for (int x : ints) {

            System.out.print(x + " ");
        }
    }
}
