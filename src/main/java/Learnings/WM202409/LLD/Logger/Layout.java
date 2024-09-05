package Learnings.WM202409.LLD.Logger;

import java.util.Date;

public interface Layout {




    String format(Date timestamp,LogLevel level,String msg);
}
