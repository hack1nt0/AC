package main;

import java.util.ArrayList;

public class RGBTree {
    String[] G;
    boolean[] vis;
    //boolean find = false;
    int target;
    public String exist(String[] G) {
        this.G = G;
        this.target = (G.length - 1) / 3;
        vis = new boolean[G.length];
        vis[0] = true;
        boolean find = dfs(0, 0, 0, 0);
        return find ? "Exist" : "Does not exist";
    }

    private boolean dfs(int cur, int r, int g, int b) {
        if (r == target && g == target && b == target) {
            return true;
        }

        if (r > target || g > target || b > target) return false;

        ArrayList<Integer> cands = new ArrayList<Integer>();
        for (int i = 0; i < G.length; ++i) {
            if (vis[i] || G[cur].charAt(i) == '.') continue;
            cands.add(i);
        }

        if (cands.size() == 0) return false;

        ArrayList<ArrayList<Integer>> candSubs = enumerate(cands);
        int r1 = 0, g1 = 0, b1 = 0;
        for (ArrayList<Integer> candSub : candSubs) {
            for (int nxt: candSub) {
                if (G[cur].charAt(nxt) == 'R') r1 += 1;
                if (G[cur].charAt(nxt) == 'G') g1 += 1;
                if (G[cur].charAt(nxt) == 'B') b1 += 1;
            }
            for (int nxt: candSub) vis[nxt] = true;
            for (int nxt: candSub) {
                if (dfs(nxt, r + r1, g + g1, b + b1)) return true;
            }
            for (int nxt: candSub) vis[nxt] = false;
        }
        return false;
    }

    private ArrayList<ArrayList<Integer>> enumerate(ArrayList<Integer> cands) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
        enumerateHelper(0, cands, new ArrayList<Integer>(), ans);
        return ans;
    }

    private void enumerateHelper(int cur, ArrayList<Integer> cands, ArrayList<Integer> acc, ArrayList<ArrayList<Integer>> ans) {
        if (cur == cands.size()) {
            ans.add(new ArrayList<Integer>(acc));
            return;
        }
        acc.add(cands.get(cur));
        enumerateHelper(cur + 1, cands, acc, ans);
        acc.remove(acc.size() - 1);

        enumerateHelper(cur + 1, cands, acc, ans);

    }
}
