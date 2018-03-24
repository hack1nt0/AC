package main;

import java.util.*;

public class DesertWind {
    int n, m;
    String[] map;
    int[][] level;
    int[] di = {-1, +1, 0, 0, -1, -1, +1, +1};
    int[] dj = {0, 0, -1, +1, -1, +1, -1, +1};
    boolean[][] visited;

    public int daysNeeded(String[] theMap) {
        this.map = theMap;
        this.n = theMap.length;
        this.m = theMap[0].length();
        this.level = new int[n][m];
        for (int i = 0; i < n; ++i) Arrays.fill(level[i], -1);
        this.visited = new boolean[n][m];
        int si = -1, sj = -1;
        for (int i = 0; i < map.length; ++i) {
            int j = map[i].indexOf('@');
            if (j != -1) {
                si = i;
                sj = j;
                break;
            }
        }
        int ans = dfs(si, sj);
        return ans;
    }

    private int dfs(int ci, int cj) {
        if (map[ci].charAt(cj) == '*')
            return 0;
        if (visited[ci][cj])
            return -1;
        visited[ci][cj] = true;
        List<Integer> xs = new ArrayList<>();
        for (int d = 0; d < di.length; ++d) {
            int ni = ci + di[d];
            int nj = cj + dj[d];
            if (0 <= ni && ni < n && 0 <= nj && nj < m
                    && map[ni].charAt(nj) != 'X') {
                int dij = dfs(ni, nj);
                if (dij >= 0)
                    xs.add(dij + 1);
            }
        }
        int res = -1;
        Collections.sort(xs);
        if (xs.size() == 1)
            res = xs.get(0) + 2;
        else if (xs.size() > 1)
            res = Math.min(xs.get(0) + 2, xs.get(1));
//        System.out.println(ci + " " + cj + " " + res);
        visited[ci][cj] = false;
        return res;
    }
}
