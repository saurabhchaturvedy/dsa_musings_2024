package CodingSimplified.Array.A005;

public class ReverseArray {


    public static void reverse(int[] arr) {

        if (arr.length <= 1) return;

        int n = arr.length;
        for (int i = 0; i < n / 2; i++) {

            int temp = arr[i];
            arr[i] = arr[n - i - 1];
            arr[n - i - 1] = temp;
        }
    }


    public static void main(String[] args) {


        int[] arr = {1, 2, 3, 4, 5};

        reverse(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
