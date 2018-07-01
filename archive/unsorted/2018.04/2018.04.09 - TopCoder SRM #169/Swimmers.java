package main;

public class Swimmers {
    public int[] getSwimTimes(int[] distances, int[] speeds, int current) {
        int n = distances.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            if (distances[i] == 0)
                ans[i] = 0;
            else if (speeds[i] <= current)
                ans[i] = -1;
            else
                ans[i] = (int)((double)distances[i] / (speeds[i] + current) + (double)distances[i] / (speeds[i] - current));
        }
        return ans;
    }
}
