package CodingSimplified.Array.A003;

public class MaximumSubarraySum {


    public int maximumSubarraySum(int[] arr) {

        if (arr.length == 0) return -1;


        int sum = 0;
        int max_sum = Integer.MIN_VALUE;

        int max_element = Integer.MIN_VALUE;
        boolean isPositiveExists = false;

        for (int i = 0; i < arr.length; i++) {

            sum = sum + arr[i];

            if (arr[i] >= 0) {
                isPositiveExists = true;
            }


            max_element = Math.max(max_element, arr[i]);

            if (sum < 0) {
                sum = 0;
                continue;
            }

            if (sum > max_sum) {

                max_sum = sum;
            }
        }

        return isPositiveExists ? max_sum : max_element;
    }


    public static void main(String[] args) {

        int arr[] = {-3, 2, -7, 6, -2, 4, -8, 5};

        MaximumSubarraySum maximumSubarraySum = new MaximumSubarraySum();

        int subarraySum = maximumSubarraySum.maximumSubarraySum(arr);

        System.out.println(" subarray sum is = " + subarraySum);
    }
}
