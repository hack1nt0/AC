package main;

public class Intersect {
    public int area(int[] x, int[] y) {
        int[] ix = {-10000, +10000};
        int[] iy = {-10000, +10000};
        for (int i = 0; i < x.length; i += 2) {
            int x1 = Math.min(x[i], x[i + 1]);
            int x2 = Math.max(x[i], x[i + 1]);
            int y1 = Math.min(y[i], y[i + 1]);
            int y2 = Math.max(y[i], y[i + 1]);
            ix[0] = Math.max(ix[0], x1);
            ix[1] = Math.min(ix[1], x2);
            iy[0] = Math.max(iy[0], y1);
            iy[1] = Math.min(iy[1], y2);
        }
        int ar = (ix[1] - ix[0]) * (iy[1] - iy[0]);
        if (ix[1] <= ix[0] || iy[1] <= iy[0]) ar = 0;
        return ar;
    }
}
