package Atlassian.PostKarat.SnakeGame.SnakeGame14Oct;

public class Main {
    public static void main(String[] args) {
        SnakeGame game = new SnakeGameImpl(10, 10);
        
        // Initial state of the snake:
        // Snake is horizontally positioned in the middle at (5, 5), (5, 4), (5, 3)
        // Head: (5, 5), Tail: (5, 3)
        
        game.moveSnake(Direction.RIGHT);  // Move right (to (5, 6))
        game.moveSnake(Direction.DOWN);   // Move down (to (6, 6))
        game.moveSnake(Direction.LEFT);   // Move left (to (6, 5))
        game.moveSnake(Direction.UP);     // Move up (to (5, 5)) -> Hits itself
        
        System.out.println("Is game over? " + game.isGameOver());  // Should print true
    }
}
