package DaveFreinBerg.TwoDimensionalArrays;

public class TwoDimensionalArray {


    public static boolean isValid(int[][] arr, int row, int col) {

        return row >= 0 && row < arr.length && col >= 0 && col < arr[0].length;
    }


    public static int getValueIn(int[][] arr, int row, int col) {

        if (isValid(arr, row, col)) return arr[row][col];

        return 0;
    }


    public static int sumAround(int[][] arr, int row, int col) {

        return getValueIn(arr, row - 1, col) + getValueIn(arr, row, col - 1) + getValueIn(arr, row, col + 1) + getValueIn(arr, row + 1, col);
    }


    public static boolean colContains(int[][] arr, int col, int value) {

        for (int row = 0; row < arr.length; row++) {

            if (arr[row][col] == value) {
                return true;
            }
        }

        return false;
    }


    public static int getNumColContains(int[][] arr, int value) {

        int count = 0;

        for (int col = 0; col < arr[0].length; col++) {

            if (colContains(arr, col, value)) {
                count++;
            }
        }
        return count;
    }


    public int addAll(int[][] arr) {

        int sum = 0;

        for (int row = 0; row < arr.length; row++) {

            for (int col = 0; col < arr[0].length; col++) {

                sum = sum + arr[row][col];
            }
        }

        return sum;
    }

    public static void main(String[] args) {


        int[][] g = new int[3][4];


        System.out.println("No of rows " + g.length);
        System.out.println("No of columns " + g[0].length);


        int[][] arr = {{1, 2, 3, 4}, {14, 7, 8, 14}, {23, 9, 21, 10}, {13, 18, 22, 33}};


        System.out.println(arr[2]);
        for (int x : arr[2]) {

            System.out.print(x + " ");
        }
        System.out.println();
        System.out.println(arr[2][3]);


        System.out.println("sum around is : " + sumAround(arr, 2, 3));


        System.out.println(" Col contains " + colContains(arr, 2, 21));


        System.out.println("Col value count " + getNumColContains(arr, 14));
    }
}
