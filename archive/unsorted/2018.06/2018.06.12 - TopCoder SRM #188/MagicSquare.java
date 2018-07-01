package main;

public class MagicSquare {
    public int missing(int[] square) {
        int n = 3;
        int tr = -1;
        for (int r = 0; r < n; ++r) {
            for (int c = 0; c < n; ++c) {
                if (square[r * n + c] == -1) {
                    tr = r;
                    square[r * n + c] = 0;
                }
            }
        }
        int[] rsum = new int[n];
        for (int r = 0; r < n; ++r) {
            for (int c = 0; c < n; ++c)
                rsum[r] += square[r * n + c];
        }
        return rsum[(tr + 1) % n] - rsum[tr];
    }
}
