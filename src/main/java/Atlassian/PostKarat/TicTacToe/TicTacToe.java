package Atlassian.PostKarat.TicTacToe;

import java.util.Scanner;

public class TicTacToe {


    Board board;

    Player player1;
    Player player2;

    Player currentPlayer;
    GameStatus gameStatus;


    TicTacToe(int size, Player player1, Player player2) {
        this.board = new Board(size);
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.gameStatus = GameStatus.PLAYING;

    }


    public void switchPlayer() {

        this.currentPlayer = this.currentPlayer == player1 ? player2 : player1;
    }


    public void play() {

        board.publishBoard();
        Scanner scanner = new Scanner(System.in);

        while (this.gameStatus == GameStatus.PLAYING) {


            System.out.println(" Player " + this.currentPlayer.name + " Please play your move (row,col) for the symbol = " + this.currentPlayer.symbol);

            int row = scanner.nextInt();
            int col = scanner.nextInt();

            while (!board.isValidMove(row, col)) {

                System.out.println(" Invalid Move !! " + this.currentPlayer.name + " Please enter a valid MOVE !");
                row = scanner.nextInt();
                col = scanner.nextInt();
            }

            Move move = this.currentPlayer.makeMove(row, col);
            board.placeMove(move, this.currentPlayer.symbol);
            board.publishBoard();

            if (board.checkWin(move, this.currentPlayer.symbol)) {
                gameStatus = GameStatus.WIN;
                System.out.println(this.currentPlayer.name + " has WON the game !!!");
            }

            if (board.isFull()) {

                gameStatus = GameStatus.DRAW;
                System.out.println(" Uh Oh !! Game has ended in a DRAW !!");
            }

            switchPlayer();
        }
    }
}
