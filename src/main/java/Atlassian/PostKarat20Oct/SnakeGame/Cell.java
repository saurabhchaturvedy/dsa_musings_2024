package Atlassian.PostKarat20Oct.SnakeGame;

import java.util.Objects;

public class Cell {


    int x;
    int y;

    Cell(int x, int y) {

        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {

        return "[" + x + "," + y + "]";
    }


    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        return this.x == cell.x && this.y == cell.y;
    }


    public int hashCode() {

        return Objects.hash(x, y);
    }
}
