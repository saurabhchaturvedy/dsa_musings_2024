package Learnings.WM202409.LLD.SnakeAndLadders;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {


        Board board = new Board(100);

        Player annu = new Player("Annu");
        Player saurabh = new Player("Saurabh");

        SnakeAndLadders snakeAndLadders = new SnakeAndLadders(board);
        snakeAndLadders.addPlayer(annu);
        snakeAndLadders.addPlayer(saurabh);

        snakeAndLadders.play();
    }
}
