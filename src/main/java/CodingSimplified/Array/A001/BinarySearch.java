package CodingSimplified.Array.A001;

public class BinarySearch {


    public int searchIndex(int[] arr, int key, int start, int end) {

        if (arr.length == 0 || start > end) {
            return -1;
        }


        int mid = start + ((end - start) / 2);   // avoid integer overflow


        if(arr[mid]==key)
        {
            return mid;
        }

        if (key < arr[mid]) {

            return searchIndex(arr, key, start, mid - 1);
        } else {

            return searchIndex(arr, key, start + 1, end);
        }


    }


    public static void main(String[] args) {


        int[]arr = {1,2,3,4,5,6,7,8};

        BinarySearch binarySearch = new BinarySearch();

       int index = binarySearch.searchIndex(arr,3,0,arr.length-1);

        System.out.println("search index value is = "+index);
    }
}
