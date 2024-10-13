package Atlassian.PostKarat.SnakeGame.SnakeGameRND;

import java.util.Objects;

public class Point {
    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point move(Direction direction) {
        switch (direction) {
            case UP: return new Point(x, y - 1);
            case DOWN: return new Point(x, y + 1);
            case LEFT: return new Point(x - 1, y);
            case RIGHT: return new Point(x + 1, y);
            default: throw new IllegalArgumentException("Unknown direction");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
