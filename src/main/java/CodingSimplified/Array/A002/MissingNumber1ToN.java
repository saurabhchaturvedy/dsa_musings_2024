package CodingSimplified.Array.A002;

public class MissingNumber1ToN {


    public int missingNumber(int[] arr, int n) {

        int sum = n * (n + 1) / 2;
        int actualSum = 0;

        for (int i = 0; i < arr.length; i++) {
            actualSum = actualSum + arr[i];

        }

        return sum - actualSum;
    }


    public static void main(String[] args) {


        int[] arr = {2,4,1,3,6,7,8};

        MissingNumber1ToN missingNumber1ToN = new MissingNumber1ToN();

        int missingNumber = missingNumber1ToN.missingNumber(arr, arr.length + 1);

        System.out.println(" missing number is " + missingNumber);
    }
}
