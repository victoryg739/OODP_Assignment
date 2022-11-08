package modal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Constant {
    public static final String FORMAT_DATE_SHORT = "dd/MM/yyyy";
    public static final String FORMAT_DATE_LONG  = "dd MM yyyy";
    public static final String SESSION_TIMEDATE = "dd MM yyyy hh:mm";

    public static SimpleDateFormat dateFormatShort = new SimpleDateFormat(FORMAT_DATE_SHORT);
    public static SimpleDateFormat dateFormatLong  = new SimpleDateFormat(FORMAT_DATE_LONG);
    public static SimpleDateFormat datetimeFormat = new SimpleDateFormat(SESSION_TIMEDATE);
}
