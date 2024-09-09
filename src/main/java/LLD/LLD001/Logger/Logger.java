package LLD.LLD001.Logger;

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


    public void setLevel(LogLevel level) {

        this.level = level;
    }


    public void log(LogLevel level, String msg) {


        if (this.level.ordinal() <= level.ordinal()) {

            Date timestamp = new Date();

            for (Appender appender : appenders) {

                String logMessage = new SimpleLayout().format(timestamp, level, msg);
                appender.append(logMessage);
            }
        }
    }


    public void info(String msg) {

        log(LogLevel.INFO, msg);
    }

    public void error(String msg) {

        log(LogLevel.ERROR, msg);
    }


    public void debug(String msg) {

        log(LogLevel.DEBUG, msg);
    }


    public void warn(String msg) {

        log(LogLevel.WARN, msg);
    }

}
