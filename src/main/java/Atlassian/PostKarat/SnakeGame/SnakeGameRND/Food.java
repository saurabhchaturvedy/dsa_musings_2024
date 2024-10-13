package Atlassian.PostKarat.SnakeGame.SnakeGameRND;

public class Food {
    private Point location;

    public Food(Point location) {
        this.location = location;
    }

    public Point getLocation() {
        return location;
    }

    public void respawn(Point newLocation) {
        this.location = newLocation;
    }
}
