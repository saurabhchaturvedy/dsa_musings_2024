package LLD.LLD001.Logger;

import java.util.Date;

public class SimpleLayout implements Layout {
    @Override
    public String format(Date timestamp, LogLevel level, String msg) {
        return String.format("[%s] [%s] %s", timestamp, level, msg);
    }
}
