package main;

import template.LinesToGraph;

import java.util.*;

public class PenLift {
    int[] dx = {0, 0, -1, +1};
    int[] dy = {+1, -1, 0, 0};

    public int numTimes(String[] segments, int n) {
        List<LinesToGraph.Seg> segs = new LinkedList<LinesToGraph.Seg>();
        for (String segment : segments) {
            String[] tmp = segment.split("[ ]");
            int x1 = Integer.valueOf(tmp[0]);
            int y1 = Integer.valueOf(tmp[1]);
            int x2 = Integer.valueOf(tmp[2]);
            int y2 = Integer.valueOf(tmp[3]);
            segs.add(new LinesToGraph.Seg(new LinesToGraph.Point(x1, y1), new LinesToGraph.Point(x2, y2)));
        }
        //concat
        List<LinesToGraph.Seg> tsegs = new LinkedList<LinesToGraph.Seg>();
        tsegs.add(segs.get(0));
        for (int i = 0; i < segs.size(); ++i) {
            boolean ok = false;
            for (int j = 0; j < tsegs.size(); ++j)
                if (segs.get(i).overlap(tsegs.get(j))) {
                    ok = true;
                    tsegs.set(j, segs.get(i).cont(tsegs.get(j)));
                    break;
                }
            if (!ok) tsegs.add(segs.get(i));
        }
        segs = tsegs;

        //n is even, how many connect comp
        if (n % 2 == 0) {
            boolean[][] M = LinesToGraph.zip(segs, true, false);
            int ans = 0;
            boolean[][] vis = new boolean[M.length][M[0].length];
            for (int i = 0; i < M.length; ++i)
                for (int j = 0; j < M[i].length; ++j) {
                    if (!M[i][j] || vis[i][j]) continue;
                    dfs(i, j, M, vis);
                    ans++;
                }
            ans--;
            return ans;
        }

        //n is odd, the Min Stroke prob (the cnt of odd-deg node)
        boolean[][] M = LinesToGraph.zip(segs, true, false);
        int ans = 0;
        boolean[][] vis = new boolean[M.length][M[0].length];

        for (int i = 0; i < M.length; ++i)
            for (int j = 0; j < M[i].length; ++j) {
                if (!M[i][j] || vis[i][j]) continue;
                int oddN = dfs(i, j, M, vis);
                if (oddN % 2 != 0) throw new RuntimeException();
                ans += Math.max(1, oddN / 2);
            }
        ans--;
        return ans;
    }

    private int dfs(int i, int j, boolean[][] M, boolean[][] vis) {
        if (vis[i][j]) return 0;
        vis[i][j] = true;

        int deg = 0;
        int res = 0;
        for (int d = 0; d < dx.length; ++d) {
            int ni = i + dx[d], nj = j + dy[d];
            if (ni < 0 || M.length <= ni || nj < 0 || M[i].length <= nj) continue;
            if (!M[ni][nj]) continue;
            res += dfs(ni, nj, M, vis);
            ++deg;
        }

        if (deg % 2 != 0) res++;
        return res;
    }



}
