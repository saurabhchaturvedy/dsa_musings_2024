package Learnings.WM202409.LLD.Logger;

public class ConsoleAppender implements Appender {


    Layout layout;


    ConsoleAppender(Layout layout) {

        this.layout = layout;
    }

    @Override
    public void append(String msg) {

        System.out.println(" Msg : " + msg);
    }
}
