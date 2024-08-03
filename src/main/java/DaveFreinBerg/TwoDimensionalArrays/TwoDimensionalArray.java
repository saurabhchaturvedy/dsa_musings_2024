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

    public static void main(String[] args) {


        int[][] g = new int[3][4];


        System.out.println("No of rows " + g.length);
        System.out.println("No of columns " + g[0].length);


        int[][] arr = {{1, 2, 3, 4}, {14, 7, 8, 11}, {23, 9, 21, 10}, {13, 18, 22, 33}};


        System.out.println(arr[2]);
        for (int x : arr[2]) {

            System.out.print(x + " ");
        }
        System.out.println();
        System.out.println(arr[2][3]);


        System.out.println("sum around is : " + sumAround(arr, 2, 3));
    }
}
