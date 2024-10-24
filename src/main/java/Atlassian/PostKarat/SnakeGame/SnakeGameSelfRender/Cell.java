package Atlassian.PostKarat.SnakeGame.SnakeGameSelfRender;

import java.util.Objects;

public class Cell {


    int x;

    int y;


    Cell(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


    public String toString() {

        return "(" + x + "," + y + ")";
    }
}
