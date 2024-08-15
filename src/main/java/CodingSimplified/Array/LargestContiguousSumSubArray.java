package CodingSimplified.Array;

public class LargestContiguousSumSubArray {


    public int largestContiguosSum(int[] arr) {
        int max_so_far = arr[0];
        int current_max = arr[0];

        for (int i = 1; i < arr.length; i++) {

            current_max = Math.max(current_max + arr[i], arr[i]);
            max_so_far = Math.max(current_max, max_so_far);
        }


        return max_so_far;
    }


    public void largestContiguousSumIndexes(int[] arr) {

        int max_so_far = arr[0];
        int current_max = arr[0];

        int start = 0;
        int end = 0;
        int s = 0;

        for (int i = 1; i < arr.length; i++) {


            if (arr[i] > current_max + arr[i]) {


                current_max = arr[i];
                s = i;
            } else {

                current_max = current_max + arr[i];
            }

            if (current_max > max_so_far) {

                max_so_far = current_max;
                start = s;
                end = i;
            }
        }


        System.out.println(" max so far " + max_so_far);
        System.out.println(" start " + start);
        System.out.println(" end " + end);
    }


    public static void main(String[] args) {


        int[] arr = {-3, -4, 4, -1, -2, 1, 5, -3};

        LargestContiguousSumSubArray largestContiguousSumSubArray = new LargestContiguousSumSubArray();

        int largestContiguosSum = largestContiguousSumSubArray.largestContiguosSum(arr);

        System.out.println("largest contiguous sum " + largestContiguosSum);

        largestContiguousSumSubArray.largestContiguousSumIndexes(arr);
    }
}
