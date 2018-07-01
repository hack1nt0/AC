package main;

public class BagOfDevouring {
    public double expectedYield(int[] values, int[] weights) {
        int n = values.length;
        double[] dp = new double[1 << n];
        for (int set = 1; set < 1 << n; ++set) {
            double max = 0;
            int wbag = 0;
            for (int i = 0; i < n; ++i)
                if ((set >> i & 1) != 0)
                    wbag += weights[i];
            for (int i = 0; i < n; ++i)
                if ((set >> i & 1) != 0) {
                    double res = values[i];
                    for (int j = 0; j < n; ++j)
                        if (j != i && (set >> j & 1) != 0)
                            res += (double) weights[j] / (wbag - weights[i] + 100) * dp[set ^ (1 << i) ^ (1 << j)];
                    res += (double) 100 / (wbag - weights[i] + 100) * dp[set ^ (1 << i)];
                    max = Math.max(max, res);
                }
            dp[set] = max;
        }
        return dp[(1 << n) - 1];
    }
}
