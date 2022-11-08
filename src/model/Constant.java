package model;

import java.text.SimpleDateFormat;

public class Constant {
    public static final String FORMAT_DATE_SHORT = "dd/MM/yyyy";
    public static final String FORMAT_DATE_LONG = "dd MM yyyy";
    public static final String SESSION_TIMEDATE = "dd MM yyyy hh:mm";

    public static SimpleDateFormat dateFormatShort = new SimpleDateFormat(FORMAT_DATE_SHORT);
    public static SimpleDateFormat dateFormatLong = new SimpleDateFormat(FORMAT_DATE_LONG);
    public static SimpleDateFormat datetimeFormat = new SimpleDateFormat(SESSION_TIMEDATE);

    public final static String MOVIEFILE = "data/movies.txt";
    public final static String HOLIDAYFILE = "data/holidays.txt";

    public final static String SETTINGFILE = "data/settings.txt";
}
