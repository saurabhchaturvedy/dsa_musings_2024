package BackTracking;

public class PrintRecursive {


    static void printRecursive(int[] arr, int index) {

        if (index == arr.length) return;

        System.out.println(arr[index]);
        printRecursive(arr, index + 1);
    }


    public static void main(String[] args) {


        int[] arr = {1, 2, 3, 4, 5};
        printRecursive(arr,0);
    }
}
