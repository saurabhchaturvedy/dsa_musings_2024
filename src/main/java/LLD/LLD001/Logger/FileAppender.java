package LLD.LLD001.Logger;

public class FileAppender implements Appender {


    Layout layout;
    String filePath;


    FileAppender(Layout layout, String filePath) {

        this.layout = layout;
        this.filePath = filePath;
    }


    public void append(String msg) {

        System.out.println(" File Path : " + this.filePath + " Msg : " + msg);
    }
}
