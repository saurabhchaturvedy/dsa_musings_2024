package BackTracking;

public class ArraySorted {


    static boolean isSorted(int[] arr, int index) {

        if (index == arr.length-1) return true;

        if (arr[index] < arr[index + 1]) return isSorted(arr, index + 1);

        return false;
    }


    public static void main(String[] args) {


        int[] arr = {2, 4, 9, 8, 10};
        boolean sorted = isSorted(arr, 0);

        System.out.println(" Is array sorted ? " + sorted);
    }
}
