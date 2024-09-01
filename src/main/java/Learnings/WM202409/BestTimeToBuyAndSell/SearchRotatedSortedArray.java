package Learnings.WM202409.BestTimeToBuyAndSell;

public class SearchRotatedSortedArray {


    public static int search(int[] nums, int target) {

        if (nums.length == 0) return -1;

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {

            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[start] <= nums[mid]) {

                if (nums[start] <= target && target <= nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (nums[mid] <= target && target <= nums[end]) {
                    start = mid + 1;

                } else {
                    end = mid - 1;
                }

            }
        }

        return -1;

    }


    public static void main(String[] args) {


        System.out.println(" is element in array ? = " + search(new int[]{4, 5, 6, 7, 0, 1, 2}, 1));

    }
}
