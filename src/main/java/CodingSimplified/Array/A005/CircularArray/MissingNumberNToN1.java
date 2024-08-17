package CodingSimplified.Array.A005.CircularArray;

public class MissingNumberNToN1 {


    public static int missingNumber(int[] arr) {


        if (arr.length == 0) return -1;

        int i = 0;

        while (i < arr.length) {

            if (arr[i] <= arr.length && arr[i] != i + 1) {

                int otherIndex = arr[i] - 1;

                int temp = arr[otherIndex];
                arr[otherIndex] = arr[i];
                arr[i] = temp;
            } else {

                i++;
            }

        }


        for (i = 0; i < arr.length; i++) {
            if (arr[i] != i + 1) {

                return i + 1;
            }

        }

        return -1;
    }


    public static void main(String[] args) {


        int[] arr = {3, 2, 4, 6, 1};

        System.out.println(" missing number is = " + missingNumber(arr));
    }
}
