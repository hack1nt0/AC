package main;

public class Intersect {
    public int area(int[] x, int[] y) {
        int rx1 = -10000;
        int rx2 = +10000;
        int ry1 = -10000;
        int ry2 = +10000;
        for (int i = 0; i < x.length; i += 2) {
            int cx1 = Math.min(x[i], x[i + 1]);
            int cx2 = Math.max(x[i], x[i + 1]);
            int cy1 = Math.min(y[i], y[i + 1]);
            int cy2 = Math.max(y[i], y[i + 1]);
            rx1 = Math.max(rx1, cx1);
            rx2 = Math.min(rx2, cx2);
            ry1 = Math.max(ry1, cy1);
            ry2 = Math.min(ry2, cy2);
            if (rx1 >= rx2 || ry1 >= ry2) return 0;
        }
        return (rx2 - rx1) * (ry2 - ry1);
    }
}
