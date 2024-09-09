package LLD.LLD001.TicTacToe;

public class Main {


    public static void main(String[] args) {


        Player saurabh = new Player("saurabh", 'X');
        Player annu = new Player("annu", 'O');

        TicTacToe ticTacToe = new TicTacToe(3, saurabh, annu);

        ticTacToe.play();

    }
}
