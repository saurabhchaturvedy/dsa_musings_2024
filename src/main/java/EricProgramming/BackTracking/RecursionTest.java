package EricProgramming.BackTracking;

public class RecursionTest {


    public void print(int[] arr, int index) {

        if (index > -1) {
            System.out.println(arr[index]);
            print(arr, index - 1);
        }
    }


    public static void printX(int[] arr, int index) {

        if (index > -1) {
            printX(arr, index - 1);
            System.out.println(arr[index]);

        }
    }


    public static void main(String[] args) {


        RecursionTest recursionTest = new RecursionTest();

        int[] arr = {1, 2, 3, 4, 5};
        recursionTest.print(arr, arr.length - 1);
        System.out.println();
        printX(arr, arr.length-1);
    }
}
