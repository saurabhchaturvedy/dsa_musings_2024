package Learnings.WM202409;

public class FindPeakElement {


    public static int findPeakElement(int[] nums) {

        if (nums.length == 0) return -1;

        int start = 0;
        int end = nums.length - 1;

        while (start < end) {

            int mid = start + (end - start) / 2;

            if (nums[mid] > nums[mid + 1]) {

                end = mid;
            } else {

                start = mid + 1;
            }

        }

        return start;

    }


    public static void main(String[] args) {


        int[] arr = {1, 2, 1, 3, 5, 6, 4};

        System.out.println(" peak element = " + findPeakElement(arr));
    }
}
