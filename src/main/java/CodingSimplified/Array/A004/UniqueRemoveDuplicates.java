package CodingSimplified.Array.A004;

public class UniqueRemoveDuplicates {


    public static int removeDuplicateUnique(int[] arr) {

        if (arr.length <= 1) return arr.length;


        int start = 1;

        for (int i = 1; i < arr.length; i++) {

            if (arr[i] != arr[i - 1]) {

                arr[start++] = arr[i];
            }
        }

        return start;
    }


    public static void main(String[] args) {


        int arr[] = {1, 4, 4, 4, 5, 6, 7, 7, 8};

        System.out.println(removeDuplicateUnique(arr));
    }
}
