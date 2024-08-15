package CodingSimplified.Array.A002;

public class SearchSortedUnSorted {


    public int searchUnsorted(int[] arr, int key) {


        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == key) {
                return i;
            }
        }

        return -1;
    }


    public int searchSorted(int[] arr, int key) {


        int start = 0;
        int end = arr.length - 1;

        while (start < end) {

            int mid = (start + end) >>> 1;

            if (arr[mid] == key) {
                return mid;
            } else if (key < arr[mid]) {

                end = mid - 1;
            } else {

                start = mid + 1;
            }
        }

        return -1;
    }


    public static void main(String[] args) {


        int[] arr = {7, 3, 1, 9, 12, 21};

        SearchSortedUnSorted searchSortedUnSorted = new SearchSortedUnSorted();

        int searchedUnsorted = searchSortedUnSorted.searchUnsorted(arr, 12);

        System.out.println(" index unsorted " + searchedUnsorted);

        int[] arr2 = {1, 2, 3, 4, 5, 6, 7, 8};

        int searchSorted = searchSortedUnSorted.searchSorted(arr2, 2);


        System.out.println(" search sorted " + searchSorted);

    }
}
