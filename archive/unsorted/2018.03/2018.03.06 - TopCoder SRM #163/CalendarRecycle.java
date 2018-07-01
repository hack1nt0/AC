package main;

public class CalendarRecycle {
    public int useAgain(int year) {
        int last = 0;
        int ans = year + 1;
        while (true) {
            last += 365;
            if (isLeapYear(ans - 1))
                last += 1;
            if (last % 7 == 0 && isLeapYear(year) == isLeapYear(ans))
                break;
            ++ans;
        }
        return ans;
    }

    boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }
}
