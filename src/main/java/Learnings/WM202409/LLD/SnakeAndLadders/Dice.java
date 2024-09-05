package Learnings.WM202409.LLD.SnakeAndLadders;

import java.util.Random;

public class Dice {


    int num;


    Dice(int num) {

        this.num = num;
    }


    public int roll() {

        Random random = new Random();

        return 1 + random.nextInt(num);
    }
}
