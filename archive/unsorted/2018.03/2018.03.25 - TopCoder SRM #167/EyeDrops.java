package main;

public class EyeDrops {
    public double closest(int sleepTime, int k) {
        double tot = 24;
        if (k == 1)
            return tot * 60;
        double ans = tot / k;
        if (comp(ans, sleepTime) < 0) {
            ans = (tot - sleepTime) / (k - 1);
            ans = Math.min(ans, sleepTime);
        }
        return ans * 60;
    }

    int comp(double a, double b) {
        if (Math.abs(a - b) < 1e-9)
            return 0;
        return a < b ? -1 : +1;
    }
}
