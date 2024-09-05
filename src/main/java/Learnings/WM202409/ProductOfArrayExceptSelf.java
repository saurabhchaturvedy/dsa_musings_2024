package Learnings.WM202409;

public class ProductOfArrayExceptSelf {


    public static int[] productExceptSelf(int[] nums) {

        int[] output = new int[nums.length];

        if (nums.length == 0) {
            return output;
        }

        int leftProduct = 1;

        for (int i = 0; i < nums.length; i++) {

            output[i] = leftProduct;
            leftProduct = leftProduct * nums[i];
        }

        int rightProduct = 1;

        for (int i = nums.length - 1; i >= 0; i--) {

            output[i] = output[i] * rightProduct;
            rightProduct = rightProduct * nums[i];
        }

        return output;
    }


    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4};

        System.out.println(" product except self :: ");

        for (int x : productExceptSelf(arr)) {

            System.out.print(x + " ");
        }
    }
}
