package main;

import java.util.Arrays;

public class MNS {
    int[] numbers;
    int ans = 0;
    public int combos(int[] numbers) {
        this.numbers = numbers;
        int N = numbers.length;
        //int ans = 0;
        int[] vis = new int[10];
        for (int d : numbers) vis[d]++;
        int[] sum = new int[4];
        ans += dfs(0, 0, vis, sum, -1);

        //enumerate(0, vis, numbers.length, new int[numbers.length]);
        return ans;
    }

    private int dfs(int i, int j, int[] vis, int[] sum, int target) {
        int preRSum = sum[3];
        if (j == 3) {
            if (i == 0) target = sum[3];
            else if (i == 1 && target != sum[3]) return 0;
            else if (i == 2) {
                boolean ok = true;
                for (int k = 0; k < sum.length; ++k) if (sum[k] != target)
                    ok = false;
                if (ok) return 1;
                else return 0;
            }
            i++;
            j = 0;
            sum[3] = 0;
        }

        int res = 0;
        for (int d = 0; d < vis.length; ++d) if (vis[d] > 0) {
            vis[d]--;
            sum[j] += d;
            sum[3] += d;
            res += dfs(i, j + 1, vis, sum, target);
            vis[d]++;
            sum[j] -= d;
            sum[3] -= d;
        }
        sum[3] = preRSum;

        return res;
    }

    private void enumerate(int cur, int[] vis, int N, int[] acc) {
        if (cur == N) {
            //System.out.println(Arrays.toString(acc));
            int[] rsum = new int[3];
            int[] csum = new int[3];
            for (int i = 0; i < acc.length; ++i) {
                rsum[i / 3] += acc[i];
                csum[i % 3] += acc[i];
            }
            Arrays.sort(rsum);
            Arrays.sort(csum);
            boolean ok = true;
            if (rsum[rsum.length - 1] != csum[0]) ok = false;
            for (int i = 1; i < rsum.length; ++i) if (rsum[i - 1] != rsum[i]) ok = false;
            for (int i = 1; i < csum.length; ++i) if (csum[i - 1] != csum[i]) ok = false;
            if (ok) ans++;
            return;
        }

        for (int d = 0; d < vis.length; ++d) if (vis[d] > 0) {
            vis[d]--;
            acc[cur] = d;
            enumerate(cur + 1, vis, N, acc);
            vis[d]++;
        }
    }

    public static void main(String[] args) {
        int[] vis = new int[]{1,2};
        new MNS().enumerate(0, vis, 3, new int[3]);
    }

}
