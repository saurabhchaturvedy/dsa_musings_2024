package LLD.SnakeAndLadders;

import java.util.ArrayList;
import java.util.List;

public class SnakeAndLadder {


    Board board;
    List<Player> players;


    SnakeAndLadder(Board board) {

        this.board = board;
        this.players = new ArrayList<>();
    }


    public void addPlayer(Player player) {

        this.players.add(player);
    }


    void play() {

        boolean isTargetCellReached = false;

        Dice dice = new Dice(6);

        while (!isTargetCellReached) {

            for (Player player : players) {

                System.out.println(" player " + player.name + " is rolling dice now ");

                int rolledValue = dice.roll();

                isTargetCellReached = player.move(board, rolledValue);

                if (isTargetCellReached) {

                    System.out.println(" Player " + player.name + " has won the game");
                    break;
                }
            }
        }
    }
}
