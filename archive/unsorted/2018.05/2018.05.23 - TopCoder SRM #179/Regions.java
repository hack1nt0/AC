package main;

public class Regions {
    public int numTaxes(int[] row, int[] col) {
        int tax = 0;
        for (int i = 1; i < row.length; ++i) {
            int h = Math.abs(row[i] - row[i - 1]);
            int w = Math.abs(col[i] - col[i - 1]);
            if (h == 0 || w == 0) {
                tax += Math.max(h, w);
            } else if (h == w) {
                tax += h;
            } else {
                tax += (h - 1) * (w - 1) + 2;
            }
        }
        return tax;
    }
}
