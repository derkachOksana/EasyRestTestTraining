package utility;

public final class Month {

    private static final String[] monthNames = {"January", "February", "March", "April", "May",
            "June", "July", "August", "September", "October", "November", "December"};

    public static String getMonthFromDate(int monthNumber) {
        return monthNames[monthNumber];
    }

    private Month() {}

}
