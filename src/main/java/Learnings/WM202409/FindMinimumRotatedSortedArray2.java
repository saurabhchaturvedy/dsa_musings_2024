package Learnings.WM202409;

public class FindMinimumRotatedSortedArray2 {


    public static int findMin(int[] nums) {

        if (nums.length == 0) return -1;

        int start = 0;
        int end = nums.length - 1;

        while (start < end) {

            int mid = start + (end - start) / 2;

            if (nums[mid] > nums[end]) {

                start = mid + 1;
            } else if (nums[mid] < nums[end]) {

                end = mid;
            } else {

                end--;
            }
        }

        return nums[start];

    }


    public static void main(String[] args) {


        int[] arr = {2, 2, 2, 0, 1};


        System.out.println(" min in rotated sorted array : " + findMin(arr));
    }
}
