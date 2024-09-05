package Learnings.WM202409.Design;

public class OddEvenPrinter {


    public static void main(String[] args) {


        NumberPrinter numberPrinter = new NumberPrinter();


        Thread t1 = new Thread(numberPrinter::printOdd);
        Thread t2 = new Thread(numberPrinter::printEven);

        t1.start();
        t2.start();
    }
}
