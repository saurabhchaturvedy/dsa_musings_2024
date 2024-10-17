package Atlassian.PostKarat.TicTacToe;

public class Main {


    public static void main(String[] args) {


        Player shikha = new Player("Shikha", 'X');
        Player saurabh = new Player("Saurabh", 'O');


        TicTacToe ticTacToe = new TicTacToe(3, shikha, saurabh);

        ticTacToe.play();
    }
}
