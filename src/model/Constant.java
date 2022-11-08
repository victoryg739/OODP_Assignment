package model;

import java.text.SimpleDateFormat;

public class Constant {
    public static final String FORMAT_DATE_SHORT = "dd/MM/yyyy";
    public static final String FORMAT_DATE_LONG = "dd MM yyyy";
    public static final String SESSION_TIMEDATE = "dd MM yyyy hh:mm";

    public static SimpleDateFormat dateFormatShort = new SimpleDateFormat(FORMAT_DATE_SHORT);
    public static SimpleDateFormat dateFormatLong = new SimpleDateFormat(FORMAT_DATE_LONG);
    public static SimpleDateFormat datetimeFormat = new SimpleDateFormat(SESSION_TIMEDATE);

    public final static String ADMINACCOUNTSFILE = "data/admin.txt";

    public final static String CUSTOMERACCOUNTFILE = "data/customer.txt";

    public final static String MOVIEFILE = "data/movies.txt";
    public final static String CINEPLEXFILE = "data/cineplex.txt";

    public final static String SESSIONFILE = "data/session.txt";
    public final static String PRICEFILE = "data/price.txt";

    public final static String CINEMAFILE = "data/cinema.txt";

    public final static String SETTINGFILE = "data/settings.txt";
    public final static String HOLIDAYFILE = "data/holiday.txt";

    public final static String BOOKINGFILE = "data/booking.txt";


}
