package main;

import java.util.HashSet;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.Set;

/*
 ID: hackint1
 LANG: JAVA
 TASK: milk31
*/

public class Milk31 {

    private int ac;
    private int bc;
    private int cc;
    boolean[][][] visited;
    private Set<Integer> ans;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        ac = in.nextInt();
        bc = in.nextInt();
        cc = in.nextInt();
        visited = new boolean[ac + 1][bc + 1][cc + 1];
        ans = new HashSet<>();
        dfs(0, 0, cc);
        out.println();
    }

    private void dfs(int a, int b, int c) {
        if (visited[a][b][c]) return;
        if (a == 0) ans.add(c);
        if (a > 0) {
            //to 1 and 2
            int to1 = Math.min(a, bc - b);
            a -= to1;
            b += to1;
            int to2 = Math.min(a, cc - c);
            a -= to2;
            c += to2;
            dfs(a, b, c);
            //only to 1
            a += to2;
            c -= to2;
            dfs(a, b, c);
            a += to1;
            c -= to1;
            //only to 2
            a -= to2;
            c += to2;
            dfs(a, b, c);
            a += to2;
            c -= to2;
        }
    }
}
