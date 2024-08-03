package DaveFreinBerg.Recursion;

public class Recursion {


    public static void mystery(int count) {
        if (count > 0) {
            System.out.println("Hello");
            mystery(count - 1);

        }


    }


    public static int factorial(int n) {


        if (n == 0) {
            return 1;
        } else {

            int result = factorial(n - 1);
            return result * n;
        }
    }


    public static void main(String[] args) {

        mystery(5);

        System.out.println(factorial(9));

    }
}
