package Learnings.WM202409.LLD.NokiaSnakeGame;

import java.util.LinkedList;
import java.util.List;

public class Snake {


    LinkedList<Cell> snakeList = new LinkedList<>();

    Cell head;


    Snake(Cell initPos) {

        head = initPos;

        snakeList.add(head);
    }


    public void grow() {

        snakeList.add(head);
    }


    public void move(Cell nextCell) {


        System.out.println(" snake is moving to next cell with row = " + nextCell.getRow() + " col = " + nextCell.getCol());

        Cell tail = snakeList.removeLast();

        tail.setCellType(CellType.EMPTY);

        head = nextCell;
        snakeList.addFirst(head);
    }


    public boolean hasCollision(Cell nextCell) {

        System.out.println(" checking for collision !!");

        for (Cell cell : snakeList) {

            if (cell == nextCell) {

                System.out.println("Collision has occured !!!");
                return true;
            }
        }

        return false;
    }

    public LinkedList<Cell> getSnakeList() {
        return snakeList;
    }

    public void setSnakeList(LinkedList<Cell> snakeList) {
        this.snakeList = snakeList;
    }

    public Cell getHead() {
        return head;
    }

    public void setHead(Cell head) {
        this.head = head;
    }
}
