package CodingSimplified.Array.A005;

public class FirstMissingPositive {


    static int firstMissingPositive(int[] arr) {


        int i = 0;

        while (i < arr.length) {

            if (arr[i] > 0 && arr[i] <= arr.length && arr[i] != arr[arr[i] - 1]) {

                int otherIndex = arr[i] - 1;

                int temp = arr[otherIndex];
                arr[otherIndex] = arr[i];
                arr[i] = temp;
            } else {

                i++;
            }
        }


        for (int k = 0; k < arr.length; k++) {

            if (arr[k] != k + 1) return k + 1;
        }


        return arr.length + 1;
    }


    public static void main(String[] args) {


        int[] arr = {3, 4, -1, 1};

        int firstMissingPositive = firstMissingPositive(arr);

        System.out.println(" first missing positive = " + firstMissingPositive);
    }
}
