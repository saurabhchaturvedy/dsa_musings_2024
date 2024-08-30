package LLD.SnakeAndLadders;

import java.util.Random;

public class Dice {


    int num;

    Dice(int num) {

        this.num = num;
    }


    public int roll() {

        Random random = new Random();

        return  random.nextInt(num)+1;
    }
}
