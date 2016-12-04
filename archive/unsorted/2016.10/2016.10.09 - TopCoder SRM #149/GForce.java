package main;

public class GForce {
    public double avgAccel(int period, int[] accel, int[] time) {
        double ans = 0;
        for (int i = 0; i < time.length; ++i) {
            double LT = time[i] - period;
            for (int j = i - 1; j >= 0; --j) {
                if (time[j] + period <= time[i]) {
                    double area = 0;
                    for (int k = i - 1; k >= j; --k) area += (accel[k] + accel[k + 1]) * (time[k + 1] - time[k]) / 2;
                    double interp = (accel[j + 1] - accel[j]) / (time[j + 1] - time[j]);
                    double LAccel = accel[j] + interp * (LT - time[j]);
                    area -= (accel[j] + LAccel) * (LT - time[j]) / 2;
                    ans = Math.max(ans, area / period);
                    break;
                }
            }

            double RT = time[i] + period;
            for (int j = i + 1; j < time.length; ++j) {
                if (time[i] + period <= time[j]) {
                    double area = 0;
                    for (int k = i + 1; k <= j; ++k) area += (accel[k] + accel[k - 1]) * (time[k] - time[k - 1]) / 2;
                    double interp = (accel[j] - accel[j - 1]) / (time[j] - time[j - 1]);
                    double RAccel = accel[j - 1] + interp * (RT - time[j - 1]);
                    area -= (accel[j] + RAccel) * (time[j] - RT) / 2;
                    ans = Math.max(ans, area / period);
                    break;
                }
            }
        }

        return ans;
    }
}
