package Learnings.WM202409.LLD.TicTacToe;

public class Main {


    public static void main(String[] args) {


        Player annu = new Player("annu", 'X');
        Player saurabh = new Player("saurabh", 'O');

        TicTacToe ticTacToe = new TicTacToe(3, annu, saurabh);


        ticTacToe.play();
    }
}
