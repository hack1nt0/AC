package main;

import java.util.ArrayList;
import java.util.List;

public class Scheduling {
    public int fastest(String[] dag) {
        int n = dag.length;
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; ++i) adj[i] = new ArrayList<>();
        int[] cost = new int[n];
        for (int i = 0; i < n; ++i) {
            String[] tmp = dag[i].split(":");
            cost[i] = Integer.valueOf(tmp[0]);
            if (tmp.length > 1 && tmp[1].length() != 0) {
                String[] dep = tmp[1].split(",");
                for (int j = 0; j < dep.length; ++j)
                    adj[i].add(Integer.valueOf(dep[j]));
            }
        }
        int ans = Integer.MAX_VALUE;
        int[][][] dp = new int[n][11][1 << n];
        for (int set = 1; set < 1 << n; ++set) {
            for (int i = 0; i < n; ++i) {
                if (!valid(i, set, adj)) continue;
                for (int left = 0; left <= cost[i]; ++left) {
                    int res = Integer.MAX_VALUE;
                    if ((set ^ (1 << i)) == 0)
                        res = left;
                    else {
                        for (int j = 0; j < n; ++j)
                            if (j != i && valid(j, set, adj)) {
                                if (cost[j] < left)
                                    res = Math.min(res, cost[j] + dp[i][left - cost[j]][set ^ (1 << j)]);
                                else
                                    res = Math.min(res, left + dp[j][cost[j] - left][set ^ (1 << i)]);
                            }
                        for (int j = 0; j < n; ++j)
                            if (valid(j, set ^ (1 << i), adj))
                                res = Math.min(res, left + dp[j][cost[j]][set ^ (1 << i)]);
                    }
                    dp[i][left][set] = res;
                    if (set == (1 << n) - 1 && left == cost[i])
                        ans = Math.min(ans, res);
                }
            }
        }
        return ans;
    }

    private boolean valid(int i, int set, List<Integer>[] adj) {
        if ((set >> i & 1) == 0)
            return false;
        for (int dep : adj[i])
            if ((set >> dep & 1) > 0)
                return false;
        return true;
    }


}
