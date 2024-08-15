package CodingSimplified.Array.A002;

public class Sort012 {


    public void sort012(int[] arr) {

        int low = 0;
        int mid = 0;
        int high = arr.length - 1;


        while (mid <= high) {


            switch (arr[mid]) {

                case 0:
                    if (arr[low] != arr[mid]) {
                        int temp = arr[low];
                        arr[low] = arr[mid];
                        arr[mid] = temp;

                    }
                    low++;
                    mid++;
                    break;

                case 1:
                    mid++;
                    break;

                case 2:
                    if (arr[mid] != arr[high]) {
                        int temp = arr[mid];
                        arr[mid] = arr[high];
                        arr[high] = temp;

                    }
                    high--;
            }
        }
    }


    public static void main(String[] args) {


        int[] arr = {0, 1, 1, 0, 2, 2, 1, 0, 2};

        Sort012 sort012 = new Sort012();


        sort012.sort012(arr);

        for (int i = 0; i < arr.length; i++) {

            System.out.print(arr[i] + " ");
        }
    }
}
