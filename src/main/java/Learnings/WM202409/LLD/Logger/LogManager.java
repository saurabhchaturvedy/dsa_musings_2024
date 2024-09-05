package Learnings.WM202409.LLD.Logger;

public class LogManager {


    public static Logger getInstance(String name) {

        return new Logger(name);
    }
}
