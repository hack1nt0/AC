package main;

import com.sun.tools.javac.util.ListBuffer;

import java.util.*;

public class MagicianTour {
    public int bestDifference(String[] roads, int[] populations) {
        int n = populations.length;
        List<int[]> comps = new ArrayList<>();
        int[] color = new int[n];
        Arrays.fill(color, -1);
        for (int s = 0; s < n; ++s) {
            if (color[s] != -1)
                continue;
            int[] nKind = new int[2];
            color[s] = 0;
            nKind[0] += populations[s];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(s);
            while (queue.size() > 0) {
                int top = queue.poll();
                for (int t = 0; t < n; ++t)
                    if (t != top && color[t] == -1 && roads[top].charAt(t) == '1') {
                        color[t] = color[top] ^ 1;
                        nKind[color[t]] += populations[t];
                        queue.add(t);
                    }
            }
            comps.add(nKind);
        }
        int accMax = 0;
        int min = Integer.MAX_VALUE;
        int MAXV = 1000;
        boolean[][][] dp = new boolean[2][MAXV + 1][MAXV + 1];
        dp[1][0][0] = true;
        for (int i = 0; i < comps.size(); ++i) {
            accMax += Math.max(comps.get(i)[0], comps.get(i)[1]);
            int cur = i % 2, pre = cur ^ 1;
            for (int black = 0; black <= accMax; ++black) {
                for (int white = 0; white <= accMax; ++white) {
                    boolean res = false;
                    if (black >= comps.get(i)[0] && white >= comps.get(i)[1])
                        res |= dp[pre][black - comps.get(i)[0]][white - comps.get(i)[1]];
                    if (black >= comps.get(i)[1] && white >= comps.get(i)[0])
                        res |= dp[pre][black - comps.get(i)[1]][white - comps.get(i)[0]];
                    dp[cur][black][white] = res;
                    if (i == comps.size() - 1 && res) {
                        min = Math.min(min, Math.abs(black - white));
                    }
                }
            }
        }
        return min;
    }
}
