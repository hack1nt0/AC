package main;

public class RectangularGrid {
    public long countRectangles(int width, int height) {
        long sn = 0;
        for (int sw = 1; sw <= Math.min(width, height); ++sw) {
            sn += (width - sw + 1) * (height - sw + 1);
        }

        long tot = C(width + 1, 2) * C(height + 1, 2);
        long ans = tot - sn;
        return ans;
    }

    private long C(int n, int m) {
        return (long) n * (n - 1) / 2;
    }
}
