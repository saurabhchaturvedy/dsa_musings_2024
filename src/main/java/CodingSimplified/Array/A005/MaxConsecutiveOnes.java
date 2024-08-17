package CodingSimplified.Array.A005;

public class MaxConsecutiveOnes {


    public static int longestOnes(int[] nums, int k) {


        if (nums == null || nums.length == 0 || k < 0 || k > nums.length) return 0;


        int max_length = 0;
        int start = 0;
        int end = 0;
        int numberOfZeroes = 0;

        while (end < nums.length) {

            if (nums[end] == 0) {
                numberOfZeroes++;
            }

            if (numberOfZeroes > k) {

                if (nums[start] == 0) {

                    numberOfZeroes--;
                }

                start++;
            }

            max_length = Math.max(max_length, end - start + 1);
            end++;

        }

        return max_length;
    }


    public static void main(String[] args) {


        int[] arr = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};


        int longestOnes = longestOnes(arr, 2);

        System.out.println(" longest ones = " + longestOnes);
    }
}
