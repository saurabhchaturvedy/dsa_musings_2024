package Learnings.WM202409;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {


    public static boolean isHappy(int n) {

        Set<Integer> seen = new HashSet<>();

        if (n < 0) {

            return false;
        }

        while (n != 1) {

            int current = n;
            int rem = 0;
            int sum = 0;

            while (current > 0) {

                rem = current % 10;
                sum = sum + rem * rem;
                current = current / 10;
            }

            if (seen.contains(sum)) {
                return false;
            }

            seen.add(sum);
            n = sum;
        }
        return true;
    }


    public static void main(String[] args) {


        System.out.println(" is happy number ? : " + isHappy(19));
        System.out.println(" is happy number ? : " + isHappy(2));
    }
}
