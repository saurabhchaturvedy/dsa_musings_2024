package LLD.SnakeAndLadders;

public class Main {


    public static void main(String[] args) {


        Player saurabh = new Player("saurabh");
        Player annu = new Player("annu");

        Board board = new Board(100);

        SnakeAndLadder snakeAndLadder = new SnakeAndLadder(board);
        snakeAndLadder.addPlayer(saurabh);
        snakeAndLadder.addPlayer(annu);


        System.out.println("*************** START PLAYING **************");
        snakeAndLadder.play();
    }
}
