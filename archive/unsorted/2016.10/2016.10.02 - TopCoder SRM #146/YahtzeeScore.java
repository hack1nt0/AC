package main;

public class YahtzeeScore {
    public int maxPoints(int[] toss) {
        int ans = 0;
        for (int i = 0; i < toss.length; ++i) {
            int tot = 0;
            for (int j = 0; j < toss.length; ++j) if (toss[i] == toss[j])
                tot += toss[j];
            ans = Math.max(ans, tot);
        }
        return ans;
    }
}
