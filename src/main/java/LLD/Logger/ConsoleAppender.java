package LLD.Logger;

public class ConsoleAppender implements Appender {



    Layout layout;


    ConsoleAppender(Layout layout)
    {

        this.layout = layout;
    }


    public void append(String msg)
    {
        System.out.println(" Msg : "+msg);
    }

}
