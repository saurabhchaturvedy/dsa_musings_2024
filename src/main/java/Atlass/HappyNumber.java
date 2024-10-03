package Atlass;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {


    private boolean isHappyNumber(int num) {


        Set<Integer> seen = new HashSet<>();


        while (num != 1) {

            int x = num;

            int rem = 0;
            int sum = 0;

            while (x > 0) {


                rem = x % 10;
                sum = sum + rem * rem;
                x = x / 10;
            }


            if (seen.contains(sum)) {
                return false;
            }

            seen.add(sum);
            num = sum;
        }

        return true;
    }


    public static void main(String[] args) {


        int x = 68;

        HappyNumber happyNumber = new HappyNumber();

        boolean isHappyNumber = happyNumber.isHappyNumber(x);

        System.out.println(" Is number happy ? " + isHappyNumber);
    }
}
