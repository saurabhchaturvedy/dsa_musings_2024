package TwoDimensionalArrays;

public class TwoDimensionalArray {


    public static void main(String[] args) {


        int[][] g = new int[3][4];


        System.out.println("No of rows " + g.length);
        System.out.println("No of columns " + g[0].length);


        int[][] arr = {
                {1, 2, 3, 4},
                {14, 7, 8, 11},
                {23, 9, 21, 10},
                {13, 18, 22, 33}
        };


        System.out.println(arr[2]);
        for (int x : arr[2]) {

            System.out.print(x + " ");
        }
        System.out.println();
        System.out.println(arr[2][3]);

    }
}
