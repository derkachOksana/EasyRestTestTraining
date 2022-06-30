package utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateHandler {

    private static final String[] monthNames = {"January", "February", "March", "April", "May",
            "June", "July", "August", "September", "October", "November", "December"};

    public static String getMonthFromDate(int monthNumber) {
        return monthNames[monthNumber];
    }

    private DateHandler() {}

    public static String getCurrentDateTime()   {
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss");
        Date date = new Date();
        return format.format(date);
    }
}
