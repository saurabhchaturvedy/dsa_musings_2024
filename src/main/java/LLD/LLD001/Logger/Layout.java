package LLD.LLD001.Logger;

import java.util.Date;

public interface Layout {


    String format(Date timestamp,LogLevel level,String msg);
}
