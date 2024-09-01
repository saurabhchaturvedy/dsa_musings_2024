package Learnings.WM202409;

public class FindMinimumRotatedSortedArray {


    public static int findMin(int[] nums) {


        if (nums.length == 0) return -1;

        int start = 0;
        int end = nums.length - 1;

        while (start < end) {

            int mid = start + (end - start) / 2;

            if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else {

                end = mid;
            }

        }


        return nums[start];

    }


    public static void main(String[] args) {

        int[] arr = {3, 4, 5, 1, 2};

        System.out.println(" min in rotated sorted array : " + findMin(arr));
    }
}
