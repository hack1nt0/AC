package main;

public class WidgetRepairs {
    public int days(int[] arrivals, int numPerDay) {
        int tot = 0;
        int ans = 0;
        for (int i = 0; i < arrivals.length; ++i) {
            tot += arrivals[i];
            if (tot > 0) ans++;
            tot = Math.max(0, tot - numPerDay);
        }
        ans += (tot + numPerDay - 1) / numPerDay;
        return ans;
    }
}
