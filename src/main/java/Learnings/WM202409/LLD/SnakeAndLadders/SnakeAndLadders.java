package Learnings.WM202409.LLD.SnakeAndLadders;

import java.util.ArrayList;
import java.util.List;

public class SnakeAndLadders {


    Board board;

    List<Player> players;


    SnakeAndLadders(Board board) {

        this.board = board;
        this.players = new ArrayList<>();
    }


    public void addPlayer(Player player) {

        this.players.add(player);
    }


    public void play() {

        boolean isTargetCellReached = false;

        Dice dice = new Dice(6);


        while (!isTargetCellReached) {


            for (Player player : players) {


                int rolledDiceValue = dice.roll();
                System.out.println(" Player " + player.name + " rolled dice with value = " + rolledDiceValue);

                isTargetCellReached = player.move(board, rolledDiceValue);

                if (isTargetCellReached) {

                    System.out.println(" Player " + player.name + " has won !!!");
                    isTargetCellReached = true;
                    break;
                }
            }
        }

    }


}
