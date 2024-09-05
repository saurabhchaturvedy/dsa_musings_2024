package Learnings.WM202409.Design;

public class NumberPrinter {


    int num = 1;
    int MAX_NUMBER = 20;


    public synchronized void printOdd() {


        while (num <= MAX_NUMBER) {

            if (num % 2 == 1) {

                System.out.println(" Print Odd " + num);
                num++;
                notify();
            } else {

                try {

                    wait();
                } catch (Exception e) {

                    System.out.println(" error");
                }
            }
        }
    }


    public synchronized void printEven() {


        while (num <= MAX_NUMBER) {

            if (num % 2 == 0) {

                System.out.println(" Print Even " + num);
                num++;
                notify();
            } else {

                try {

                    wait();
                } catch (Exception e) {

                    System.out.println(" error");
                }
            }
        }
    }
}
