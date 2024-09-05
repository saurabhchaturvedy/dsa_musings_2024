package Learnings.WM202409.LLD.TicTacToe;

import java.util.Scanner;

public class TicTacToe {


    Board board;

    Player player1;

    Player player2;

    Player currentPlayer;

    GameStatus status;


    TicTacToe(int size, Player player1, Player player2) {

        this.board = new Board(size);
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.status = GameStatus.PLAYING;
    }


    public void switchPlayer() {

        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }


    public void play() {

        board.publishBoard();

        Scanner scanner = new Scanner(System.in);

        while (status == GameStatus.PLAYING) {


            System.out.println(" Player " + currentPlayer.name + " Please make your move to place your symbol " + currentPlayer.symbol);

            int row = scanner.nextInt();
            int col = scanner.nextInt();


            if (!board.isValidMove(row, col)) {

                System.out.println(" Please put a valid move !!");
                row = scanner.nextInt();
                col = scanner.nextInt();
            }


            Move move = currentPlayer.makeMove(row, col);
            board.placeMove(move, currentPlayer.symbol);
            board.publishBoard();

            if (board.isFull()) {

                System.out.println(" Game has ended in a DRAW !!");
                status = GameStatus.DRAW;
                break;
            }

            if (board.checkWin(move, currentPlayer.symbol)) {

                System.out.println(" Player " + currentPlayer.name + " has won the GAME !!");
                status = GameStatus.WIN;
                break;
            }

            switchPlayer();
        }
    }
}
