package Learnings.WM202409.LLD.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Logger {


    String name;

    List<Appender> appenders;
    LogLevel level;


    Logger(String name) {

        this.name = name;
        this.level = LogLevel.INFO;
        this.appenders = new ArrayList<>();
    }


    public void addAppender(Appender appender) {

        this.appenders.add(appender);
    }


    public void setLogLevel(LogLevel level) {

        this.level = level;
    }


    public void log(LogLevel level, String msg) {


        if (this.level.ordinal() <= level.ordinal()) {

            Date timestamp = new Date();


            for (Appender appender : appenders) {


                String message = new SimpleLayout().format(timestamp, level, msg);
                appender.append(message);
            }
        }
    }


    public void info(String msg) {

        log(LogLevel.INFO, msg);
    }


    public void debug(String msg) {

        log(LogLevel.DEBUG, msg);
    }


    public void warn(String msg) {

        log(LogLevel.WARN, msg);
    }


    public void error(String msg) {

        log(LogLevel.ERROR, msg);
    }


    public static void main(String[] args) {


        Logger logger = LogManager.getInstance("test-logger");


        Layout layout = new SimpleLayout();
        ConsoleAppender consoleAppender = new ConsoleAppender(layout);
        FileAppender fileAppender = new FileAppender("C://abc.txt", layout);


        logger.addAppender(consoleAppender);
        logger.addAppender(fileAppender);


        logger.setLogLevel(LogLevel.ERROR);

        logger.info("This is a info msg");
        logger.error("This is a error msg");
        logger.debug("This is a debug msg");
        logger.warn("This is a warn msg");
    }
}
