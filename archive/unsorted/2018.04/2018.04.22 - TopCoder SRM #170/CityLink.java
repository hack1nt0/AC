package main;

public class CityLink {
    public int timeTaken(int[] x, int[] y) {
        int N = x.length;
        if (N == 1)
            return 0;
        int[][] D = new int[N][N];
        int dMax = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (x[i] == x[j])
                    D[i][j] = (Math.abs(y[i] - y[j]) + 1) / 2;
                else if (y[i] == y[j])
                    D[i][j] = (Math.abs(x[i] - x[j]) + 1) / 2;
                else
                    D[i][j] = Math.max(Math.abs(x[i] - x[j]), Math.abs(y[i] - y[j]));
                dMax = Math.max(dMax, D[i][j]);
            }
        }
        int l = 0, r = dMax;
        while (l + 1 < r) {
            int mid = r - (r - l) / 2;
            boolean good = true;
            boolean[][] C = new boolean[N][N];
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    C[i][j] = D[i][j] <= mid;
                }
            }
            for (int k = 0; k < N; ++k) {
                for (int i = 0; i < N; ++i) {
                    for (int j = 0; j < N; ++j) {
                        C[i][j] = C[i][j] || C[i][k] && C[k][j];
                    }
                }
            }
            for (int i = 0; i < N; ++i)
                for (int j = 0; j < N; ++j)
                    if (!C[i][j]) {
                        good = false;
                        break;
                    }
            if (good)
                r = mid;
            else
                l = mid;
        }
        return r;
    }
}
