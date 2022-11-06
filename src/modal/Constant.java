package modal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Constant {
    public static final String FORMAT_TIME_CLOCK = "HH:mm";
    public static final String FORMAT_TIME_HOUR  = "hh:mma";
    public static final String FORMAT_DATE_SHORT = "dd/MM/yyyy";
    public static final String FORMAT_DATE_LONG  = "dd MM yyyy";
    public static final String FORMAT_BOOKING_ID = "yyyyMMddHHmm";
    public static final String FORMAT_HOLIDAY	 = "dd MMM";
    public static final String SESSION_TIMEDATE = "dd MM yyyy hh:mm";

    public static SimpleDateFormat dateFormatShort = new SimpleDateFormat(FORMAT_DATE_SHORT);
    public static SimpleDateFormat dateFormatLong  = new SimpleDateFormat(FORMAT_DATE_LONG);
    public static SimpleDateFormat clockFormat = new SimpleDateFormat(FORMAT_TIME_CLOCK);
    public static SimpleDateFormat timeFormat  = new SimpleDateFormat(FORMAT_TIME_HOUR);

    public static SimpleDateFormat bookingFormat = new SimpleDateFormat(FORMAT_BOOKING_ID);
    public static SimpleDateFormat holidayFormat = new SimpleDateFormat(FORMAT_HOLIDAY);
    public static SimpleDateFormat datetimeFormat = new SimpleDateFormat(SESSION_TIMEDATE);


    public static DateFormat dateTimeFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
}
