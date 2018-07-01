package main;

public class CalendarISO {
    public int weekNumber(int year, int month, int day) {
        int[] monthDays = new int[13];
        for (int i = 1; i <= 12; ++i) {
            if (i == 4 || i == 6 || i == 9 || i == 11) monthDays[i] = 30;
            else monthDays[i] = 31;
        }
        monthDays[2] = isLeapYear(1582) ? 29 : 28;

        return 0;
    }

    boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }
}
