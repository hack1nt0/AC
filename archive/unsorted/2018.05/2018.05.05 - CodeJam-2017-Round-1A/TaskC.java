package main;

import java.util.Scanner;
import java.io.PrintWriter;

public class TaskC {

    int MAX = 100;
    int Hd, Hk, B, D;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        out.print("Case #" + testNumber + ": ");
        Hd = in.nextInt();
        int Ad = in.nextInt();
        Hk = in.nextInt();
        int Ak = in.nextInt();
        B = in.nextInt();
        D = in.nextInt();
        int[][][][][][] visited = new int[2][Hd + 1][MAX + 1][Hk + 1][MAX + 1][2];
        int[] ans = dfs(0, new int[][] {{Hd, Ad}, {Hk, Ak}}, visited, 1);
        out.println(ans[0] == -2 ? "IMPOSSIBLE" : ans[0]);
    }

    private int[] dfs(int cur, int[][] state, int[][][][][][] visited, int turn) {
        int[] ans = null;
        try {
            int hd = state[0][0], ad = state[0][1], hk = state[1][0], ak = state[1][1];
            ans = visited[cur][hd][ad][hk][ak];
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (ans[0] == -1)
            return new int[]{-2, -2};
        if (ans[0] > 0 || ans[0] == -2)
            return ans;
        ans[0] = -1;
        int[] res = new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE};
        int[] attacker = state[cur];
        int[] opponent = state[cur ^ 1];
        {
            int tmp = opponent[0];
            opponent[0] -= attacker[1];
            if (opponent[0] <= 0) {
                res[0] = turn;
                res[1] = -2;
            } else {
                int[] next = dfs(cur ^ 1, state, visited, turn + cur);
                if (next[0] == -2) {
                    res[0] = Math.min(res[0], next[1]);
                }
                res[1] = Math.min(res[1], next[0]);
            }
            opponent[0] = tmp;
        }
        {
            if (attacker[1] < MAX) {
                int tmp = attacker[1];
                attacker[1] = Math.min(MAX, attacker[1] + B);
                int[] next = dfs(cur ^ 1, state, visited, turn + cur);
                if (next[0] == -2) {
                    res[0] = Math.min(res[0], next[1]);
                }
                res[1] = Math.min(res[1], next[0]);
                attacker[1] = tmp;
            }
        }
        {
            int tmp = attacker[0];
            attacker[0] = cur == 0 ? Hd : Hk;
            int[] next = dfs(cur ^ 1, state, visited, turn + cur);
            if (next[0] == -2) {
                res[0] = Math.min(res[0], next[1]);
            }
            res[1] = Math.min(res[1], next[0]);
            attacker[0] = tmp;
        }
        {
            if (opponent[1] > 0) {
                int tmp = opponent[1];
                opponent[1] = Math.max(0, opponent[1] - D);
                int[] next = dfs(cur ^ 1, state, visited, turn + cur);
                if (next[0] == -2) {
                    res[0] = Math.min(res[0], next[1]);
                }
                res[1] = Math.min(res[1], next[0]);
                opponent[1] = tmp;
            }
        }
        ans[0] = res[0] == Integer.MAX_VALUE ? -2 : res[0];
        ans[1] = ans[0] > 0 ? -2 : res[1];
        return ans;
    }
}
