package CodingSimplified.Array.A006;

public class SegregateZeroesOnes {


    public static void segregateZeroesOnes(int[] arr) {

        if (arr.length == 0) return;

        int start = 0;
        int end = arr.length - 1;

        while (start < end) {


            while (arr[start] == 0 && start < end) {
                start++;
            }

            while (arr[end] == 1 && start < end) {
                end--;
            }


            if (start < end) {

                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;

                start++;
                end--;
            }
        }
    }


    public static void main(String[] args) {


        int[] arr = {1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0};

        segregateZeroesOnes(arr);

        for (int i = 0; i < arr.length; i++) {

            System.out.print(arr[i] + " ");
        }
    }
}
