package CodingSimplified.Array.A004;

public class TripletSumLessTarget {


    public static int tripletSumLessThanTarget(int[] arr, int target) {

        if (arr.length == 0) return -1;


        int tripletCount = 0;

        for (int i = 0; i < arr.length - 2; i++) {

            int start = i + 1;
            int end = arr.length - 1;

            while (start < end) {

                int t = arr[i] + arr[start] + arr[end];

                if (t < target) {

                    tripletCount = tripletCount + end - start;
                    start++;
                } else {

                    end--;
                }
            }
        }
        return tripletCount;
    }


    public static void main(String[] args) {


        int arr[] = {1, 2, -3, 4, -2};

        int tripletCount = tripletSumLessThanTarget(arr, 1);


        System.out.println(" triplet count =" + tripletCount);
    }
}
