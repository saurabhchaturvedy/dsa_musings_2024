package CodingSimplified.Array.A002;

public class FindFirstOccurenceAdjacentK {


    public int firstOccurenceIndex(int[] arr, int key, int k) {

        if (arr.length == 0) return -1;


        int start = 0;

        while (start < arr.length) {

            if (arr[start] == key) {
                return start;
            }

            int diff = Math.abs(arr[start] - key) / k;
            diff = Math.max(1, diff);
            start = start + diff;
        }

        return -1;
    }

    public static void main(String[] args) {

        int arr1[] = {70, 60, 70, 80, 90, 110, 130};

        FindFirstOccurenceAdjacentK findFirstOccurenceAdjacentK = new FindFirstOccurenceAdjacentK();

        int firstOccurenceIndex = findFirstOccurenceAdjacentK.firstOccurenceIndex(arr1, 10, 110);

        System.out.println(" first occurence index : " + firstOccurenceIndex);
    }
}
