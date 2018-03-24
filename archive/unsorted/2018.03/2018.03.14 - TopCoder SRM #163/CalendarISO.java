package main;

import java.util.Arrays;

public class CalendarISO {
    public int weekNumber(int year, int month, int day) {
        int s = 0;
        for (int y = 1582; y <= year; ++y) {
            if (y == year) {
                int d = whichDay(year, month, day);
                int dayOfWeek = (d - s) % 7;
                return (0 < dayOfWeek && dayOfWeek < 4 && d + 7 - dayOfWeek > days(y)) ? 1 : (d - s + 6) / 7;
            }
            int dayOfWeek = (days(y) - s) % 7;
            if (dayOfWeek >= 4)
                s = 7 - dayOfWeek;
            else
                s = -dayOfWeek;
        }
        return 0;
    }

    int whichDay(int y, int m, int d) {
        int[] md = new int[12];
        Arrays.fill(md, 30);
        md[2 - 1] = days(y) == 366 ? 29 : 28;
        for (int i : new int[]{1, 3, 5, 7, 8, 10, 12})
            md[i - 1] = 31;
        int res = 0;
        for (int i = 0; i < m - 1; ++i)
            res += md[i];
        return res + d;
    }

    int days(int y) {
        return (y % 4 == 0 && y % 100 != 0 || y % 400 == 0) ? 366 : 365;
    }

}
