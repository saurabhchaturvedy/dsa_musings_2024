package Atlass;

public class ReverseNumber {


    public static int reverse(int x) {

        int rem = 0;
        int rev = 0;

        while (x > 0) {

            rem = x % 10;
            rev = rev * 10 + rem;
            x = x / 10;
        }

        return rev;
    }


    public static void main(String[] args) {

        System.out.println(" reversed number is = " + reverse(123));
    }
}
