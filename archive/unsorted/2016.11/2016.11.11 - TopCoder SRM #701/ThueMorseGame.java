package main;

import java.util.HashMap;

public class ThueMorseGame {
    int N, M;
    HashMap<Integer, Boolean> dp = new HashMap<Integer, Boolean>();

    public String get(int n, int m) {
        this.N = n;
        this.M = m;
        dp.put(0, false);
        boolean ans = dfs(n);

        return ans ? "Alice" : "Bob";
    }

    private boolean dfs(int cur) {
        if (dp.containsKey(cur)) return dp.get(cur);

        boolean res = false;

        for (int i = 1; i <= M && i <= cur; ++i) {
            if (Integer.bitCount(cur - i) % 2 != 0) continue;
            boolean second = dfs(cur - i);
            if (!second) {res = true; break;}
        }

        dp.put(cur, res);
        return res;
    }
}
