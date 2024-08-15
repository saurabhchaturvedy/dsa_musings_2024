package CodingSimplified.Array.A002;

public class FindFirstOccurenceAdjacent1 {


    public int firstOccurenceIndex(int[] arr, int key) {

        if (arr.length == 0) return -1;


        int start = 0;

        while (start < arr.length) {

            if (arr[start] == key) {
                return start;
            }

            int diff = Math.abs(arr[start] - key);
            start = start + diff;
        }

        return -1;
    }


    public static void main(String[] args) {


        int arr1[] = {7, 6, 7, 6, 7, 6, 5, 4, 5, 4, 3, 2, 1, 2, 3};

        FindFirstOccurenceAdjacent1 findFirstOccurenceAdjacent1 = new FindFirstOccurenceAdjacent1();


        int firstOccurenceIndex = findFirstOccurenceAdjacent1.firstOccurenceIndex(arr1, 2);

        System.out.println(" first occurence index : "+firstOccurenceIndex);

    }
}
