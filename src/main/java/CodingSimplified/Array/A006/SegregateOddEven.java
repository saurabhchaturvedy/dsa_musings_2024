package CodingSimplified.Array.A006;

public class SegregateOddEven {


    public static void segregateOddEven(int[] arr) {

        if (arr.length == 0) return;

        int start = 0;
        int end = arr.length - 1;

        while (start < end) {


            while (arr[start] % 2 == 0 && start < end) {

                start++;
            }


            while (arr[end] % 2 == 1 && start < end) {

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


        int[] arr = {2, 3, 4, 7, 11, 14, 19, 22};

        segregateOddEven(arr);

        for (int i = 0; i < arr.length; i++) {

            System.out.print(arr[i] + " ");
        }
    }
}
