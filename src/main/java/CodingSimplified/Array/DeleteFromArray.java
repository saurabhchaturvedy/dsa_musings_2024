package CodingSimplified.Array;

public class DeleteFromArray {


    int count = 5;


    public void print(int[] arr) {

        for (int i = 0; i < count; i++) {

            System.out.print(arr[i] + " ");
        }
    }


    public void deleteFromEnd(int[] arr) {

        if (arr.length <= 0) {
            return;
        }

        count--;
    }


    public void deleteValue(int[] arr, int val) {

        int i = 0;

        for (i = 0; i < count; i++) {

            if (arr[i] == val) {
                break;
            }
        }


        if (i == count) {
            System.out.println("Value doesn't exist in array");
            return;
        }


        for (int j = i; j < count - 1; j++) {

            arr[j] = arr[j + 1];
        }

        count--;
    }


    public void deleteFromPosition(int[] arr, int position) {


        if (position > count || position < 0) {
            return;
        }


        for (int i = position - 1; i < count - 1; i++) {

            arr[i] = arr[i + 1];
        }

        count--;
    }


    public static void main(String[] args) {


        int[] arr = {1, 2, 3, 4, 5};

        DeleteFromArray deleteFromArray = new DeleteFromArray();


        deleteFromArray.deleteFromEnd(arr);

        deleteFromArray.print(arr);


        deleteFromArray.deleteValue(arr, 2);

        System.out.println();

        deleteFromArray.print(arr);

        System.out.println();

        deleteFromArray.deleteFromPosition(arr, 3);

        deleteFromArray.print(arr);
    }
}
