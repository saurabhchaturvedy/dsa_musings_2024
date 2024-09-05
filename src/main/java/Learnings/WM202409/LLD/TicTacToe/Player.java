package Learnings.WM202409.LLD.TicTacToe;

public class Player {


    String name;

    char symbol;


    Player(String name, char symbol) {

        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }


    public Move makeMove(int row, int col) {

        return new Move(row, col);
    }
}
