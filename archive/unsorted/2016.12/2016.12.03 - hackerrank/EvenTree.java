package main;

import main.template.Graph;

import java.util.*;
import java.io.PrintWriter;

//treeDp

public class EvenTree {
    int[] f, g;
    Graph tree;
    int MIN_VALUE = Integer.MIN_VALUE;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int M = in.nextInt();
        tree = new Graph(N);
        for (int i = 0; i < M; ++i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            tree.addE(a, b, 0);
            tree.addE(b, a, 0);
        }

        f = new int[N];
        g = new int[N];
        int root = 0;
        dfs(root, -1);
        if (f[root] == MIN_VALUE) throw new RuntimeException();
        out.println(f[root]);
    }

    private void dfs(int cur, int fa) {
        f[cur] = g[cur] = MIN_VALUE;
        int cntchd = 0;

        for (Graph.Edge e : tree.adj[cur]) {
            if (e.b == fa) continue;
            cntchd++;
            dfs(e.b, cur);
        }

        if (cntchd == 0) {
            f[cur] = MIN_VALUE;
            g[cur] = 0;
            return;
        }

        List<Integer> chdList = new ArrayList<Integer>();
        int res0 = 0;
        int gc0 = 0;
        for (Graph.Edge e : tree.adj[cur]) {
            if (e.b == fa) continue;
            if (f[e.b] == MIN_VALUE && g[e.b] == MIN_VALUE) throw new RuntimeException();
            if (f[e.b] == MIN_VALUE) {
                res0 += g[e.b];
                gc0++;
            }
            else if (g[e.b] == MIN_VALUE) {
                res0 += f[e.b] + 1;
            }
            else chdList.add(e.b);
        }

        //Below code is useless, yet more illustrating.
        Integer[] chds = chdList.toArray(new Integer[0]);
        Arrays.sort(chds, new Comparator<Integer>() {
            //@Override
            public int compare(Integer o1, Integer o2) {
                int x = g[o1] - f[o1] - 1;
                int y = g[o2] - f[o2] - 1;
                return -x + y;
            }
        });
        for (int gc = 0; gc <= chds.length; ++gc) {
            int res = res0;
            for (int i = 0; i < gc; ++i) res += g[chds[i]];
            for (int i = gc; i < chds.length; ++i) res += f[chds[i]] + 1;
            if ((gc + gc0) % 2 == 0) g[cur] = Math.max(g[cur], res);
            else f[cur] = Math.max(f[cur], res);
        }

    }
}
