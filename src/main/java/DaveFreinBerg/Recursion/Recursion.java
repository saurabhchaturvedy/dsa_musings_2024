package DaveFreinBerg.Recursion;

public class Recursion {


    public static void mystery(int count) {
        if(count>0) {
            System.out.println("Hello");
            mystery(count-1);
        }


    }


    public static void main(String[] args) {

        mystery(5);
    }
}
