package LLD.LLD001.Logger;

public class Main {


    public static void main(String[] args) {


        Logger logger = LogManager.getLogger(" Test Logger !!");


        SimpleLayout layout = new SimpleLayout();
        Appender consoleAppender = new ConsoleAppender(layout);
        Appender fileAppender = new FileAppender(layout, "C://logs.txt");


        logger.addAppender(consoleAppender);
        logger.addAppender(fileAppender);


        logger.setLevel(LogLevel.WARN);
        logger.info(" this is a test of INFO log");
        logger.debug("this is a test of DEBUG log");
        logger.warn("this is a test of WARN log");
        logger.error(" this is a test of ERROR log");
    }
}
