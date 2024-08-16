package CodingSimplified.Array.A004;

import java.util.Random;

public class ShuffleArray {


    public void shuffleArray(int[] arr) {

        Random random = new Random();

        for (int i = 0; i < arr.length; i++) {

            int randomIndex = random.nextInt(arr.length);

            int temp = arr[i];
            arr[i] = arr[randomIndex];
            arr[randomIndex] = temp;
        }
    }


    public static void main(String[] args) {


        int[] arr = {3, 7, 4, 1, 8, 9, 10};

        ShuffleArray shuffleArray = new ShuffleArray();


        shuffleArray.shuffleArray(arr);

        for (int i = 0; i < arr.length; i++) {

            System.out.print(arr[i] + " ");
        }
    }
}
