package Learnings.WM202409.LLD.Logger;

public class FileAppender implements Appender {


    Layout layout;
    String filePath;


    FileAppender(String filePath, Layout layout) {

        this.filePath = filePath;
        this.layout = layout;
    }

    @Override
    public void append(String msg) {

        System.out.println(" File Path : " + this.filePath + " Msg : " + msg);
    }
}
