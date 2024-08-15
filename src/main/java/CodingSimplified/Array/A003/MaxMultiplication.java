package CodingSimplified.Array.A003;

public class MaxMultiplication {


    public int maxMultiplication(int[] arr) {

        if (arr.length == 0) return -1;

        if (arr.length == 2) return arr[0] * arr[1];


        int smallest = arr[0] < arr[1] ? arr[0] : arr[1];
        int secondSmallest = arr[0] > arr[1] ? arr[0] : arr[1];

        int largest = arr[0] > arr[1] ? arr[0] : arr[1];
        int secondLargest = arr[0] < arr[1] ? arr[0] : arr[1];

        for (int i = 2; i < arr.length; i++) {

            if (arr[i] < smallest) {

                secondSmallest = smallest;
                smallest = arr[i];
            } else if (arr[i] < secondSmallest) {

                secondSmallest = arr[i];
            }

            if (arr[i] > largest) {

                secondLargest = largest;
                largest = arr[i];
            } else if (arr[i] > secondLargest) {

                secondLargest = arr[i];
            }
        }

        int firstTwo = smallest * secondSmallest;
        int lastTwo = largest * secondLargest;

        int maxMultiplication = firstTwo > lastTwo ? firstTwo : lastTwo;

        return maxMultiplication;
    }


    public static void main(String[] args) {


        int arr[] = {-4, 2, 3, -10, 4, -15, 3, 6};

        MaxMultiplication maxMultiplication = new MaxMultiplication();

        int maxedMultiplication = maxMultiplication.maxMultiplication(arr);

        System.out.println(" max multiplication : " + maxedMultiplication);
    }
}
