package main;

public class ArithmeticSequenceDiv1 {
    public int findMinCost(int[] x) {
        int MAXD = 102;
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < x.length; ++i) {
            for (int d = -MAXD; d <= +MAXD; ++d) {
                int cost = 0;
                for (int j = 0; j < x.length; ++j) {
                    int t = x[i] + (j - i) * d;
                    cost += Math.abs(t - x[j]);
                }
                minCost = Math.min(minCost, cost);
            }
        }
        return minCost;
    }
}
