package main;

import template.collection.sequence.ArrayUtils;

public class StablePairsDiv1 {
    boolean[][] bad;

    public int[] findMaxStablePairs(int n, int c, int k) {
        if (n == 1)
            return new int[0];
        int[] ans = new int[k * 2];
        ans[0] = n;
        ans[1] = n - 1;
        bad = new boolean[k * 2][n * 2];
        if (!dfs(2, k * 2, n + n - 1, c, ans)) {
            return new int[0];
        }
        ArrayUtils.reverse(ans);
        return ans;
    }

    private boolean dfs(int cur, int tot, int pre, int c, int[] ans) {
        if (cur >= tot)
            return true;
        if (bad[cur][pre])
            return false;
//        if ((tot - cur + tot - cur - 1) + c > ans[cur - 1] + ans[cur])
//            return false;
//        if (ans[cur - 1] - 1 < tot - cur)
//            return false;
        for (int i = ans[cur - 1] - 1; i >= 1; --i) {
            ans[cur] = i;
            ans[cur + 1] = pre - c - i;
            if (1 <= ans[cur + 1] && ans[cur] > ans[cur + 1] &&
                    dfs(cur + 2, tot, ans[cur] + ans[cur + 1], c, ans))
                return true;
        }
        bad[cur][pre] = true;
        return false;
    }
}
