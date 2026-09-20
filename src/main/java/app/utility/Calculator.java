package app.utility;

public class Calculator {

    public static String findLastDayOfMonth(String month, int year) {
        if (month.equals("01") || month.equals("03") || month.equals("05") || month.equals("07") || month.equals("08") || month.equals("10") || month.equals("12")) {
            return "31";
        } else if (month.equals("04") || month.equals("06") || month.equals("09") || month.equals("11")) {
            return "30";
        } else if (month.equals("02")) {
            boolean leapYear = false;
            if (year % 4 == 0) {
                leapYear = true;
                if (year % 100 == 0 && year % 400 != 0) {
                    leapYear = false;
                }
            }
            if (!leapYear) {
                return "28";
            } else {
                return "29";
            }
        }
        return "";
    }
}