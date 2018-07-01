package main;

import com.sun.javafx.binding.StringFormatter;

public class RaceApproximator {
    public String timeToBeat(int d1, int t1, int d2, int t2, int raceDistance) {
        int d = raceDistance;
        int seconds = (int) (t1 * Math.exp(Math.log((double) t2 / t1) * Math.log((double) d1 / d) / Math.log((double) d1 / d2)));
        int h = seconds / 3600;
        seconds -= h * 3600;
        int m = seconds / 60;
        int s = seconds % 60;
        String ans = "" + h + ":" + (m < 10 ? "0" + m : m) + ":" + (s < 10 ? "0" + s : s);
        return ans;
    }
}
