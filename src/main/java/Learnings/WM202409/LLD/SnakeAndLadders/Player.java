package Learnings.WM202409.LLD.SnakeAndLadders;

public class Player {


    String name;

    int position;


    Player(String name) {

        this.name = name;
        this.position = 0;
    }


    public boolean move(Board board, int rolledDiceValue) {

        int newPosition = this.position + rolledDiceValue;

        System.out.println(" Value of dice rolled by " + name + " is : " + rolledDiceValue);
        if (newPosition > board.size) return false;

        System.out.println(" Player's new position request after rolling dice is =" + newPosition);

        Cell cell = board.getCell(newPosition);

        if (cell.cellType == CellType.TARGET) {
            return true;

        } else if (cell.cellType == CellType.SNAKE || cell.cellType == CellType.LADDER) {
            System.out.println("Player " + name + " is now at cell " + cell.cellType + " , has moved to " + cell.end);
            this.position = cell.end;
        } else {

            this.position = newPosition;
        }

        return false;
    }
}
