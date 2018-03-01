package main;

import java.util.TreeSet;

public class HourGlass {
    int glass1, glass2;
    TreeSet<Integer> first10 = new TreeSet<>();
    boolean[][][] vis = new boolean[500 + 1][50 + 1][50 + 1];

    public int[] measurable(int glass1, int glass2) {
        this.glass1 = glass1;
        this.glass2 = glass2;
        for (int i = 1; i <= 10; ++i) first10.add(i * glass1);
        for (int i = 1; i <= 10; ++i) if (i * glass2 < first10.last() && !first10.contains(i * glass2)) {
            first10.pollLast(); first10.add(i * glass2);
        }
        dfs(0, 0, 0);
        int[] res = new int[10];
        for (int i = 0; i < 10; ++i) res[i] = first10.pollFirst();
        return res;
    }

    private void dfs(int tic, int up1, int up2) {
//        System.out.println(up2 + " " + up1);
        if (tic >= first10.last()) return;
        if (vis[tic][up1][up2]) return;
        vis[tic][up1][up2] = true;
        if (tic > 0 && !first10.contains(tic)) {
            first10.pollLast(); first10.add(tic);
        }
        if (!(up1 == 0 || up2 == 0)) throw new RuntimeException();

        int nup1, nup2, t;
        nup1 = glass1 - up1;
        nup2 = glass2 - up2;
        t = Math.min(nup1, nup2);
        dfs(tic + t, nup1 - t, nup2 - t);
        if (t == 0) {
            t = Math.max(nup1, nup2);
            dfs(tic + t, 0, 0);
        }

        nup1 = glass1 - up1;
        nup2 = up2;
        t = Math.min(nup1, nup2);
        dfs(tic + t, nup1 - t, nup2 - t);
        if (t == 0) {
            t = Math.max(nup1, nup2);
            dfs(tic + t, 0, 0);
        }

        nup1 = up1;
        nup2 = glass2 - up2;
        t = Math.min(nup1, nup2);
        dfs(tic + t, nup1 - t, nup2 - t);
        if (t == 0) {
            t = Math.max(nup1, nup2);
            dfs(tic + t, 0, 0);
        }
    }
}
