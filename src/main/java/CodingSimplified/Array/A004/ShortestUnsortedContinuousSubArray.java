package CodingSimplified.Array.A004;

public class ShortestUnsortedContinuousSubArray {


    public static int shortestUnsorted(int[] arr) {

        int start = -1;
        int end = -2;


        int min = arr[arr.length - 1];
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {

            max = Math.max(max, arr[i]);
            if (arr[i] < max) end = i;
        }

        for (int i = arr.length - 2; i >= 0; i--) {

            min = Math.min(min, arr[i]);
            if (arr[i] > min) start = i;
        }


        return end - start + 1;
    }


    public static void main(String[] args) {


        int[] arr = {2, 6, 4, 8, 10, 9, 15};



        int shortestUnsorted = shortestUnsorted(arr);

        System.out.println(" subarray length = " + shortestUnsorted);
    }
}
