package main;

import template.graph_theory.Edge;
import template.graph_theory.Graph;

import java.util.*;
import java.io.PrintWriter;

/**
 * WA : a hard treeDp
 *
 *
 */
public class RepairRoadsSingleThread {
    int N;
    Graph G;
    int[] startWithRoot;
    int[] ret;
    boolean[] mustStartWith;
    int[] cn;
    int testNumber;
    int[] delta;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.testNumber = testNumber;
        N = in.nextInt();
        G = new Graph(N);
        for (int i = 0; i < N - 1; ++i) {
            int a = in.nextInt();
            int b = in.nextInt();
            G.addEdge(new Graph.Edge(a, b, 1));
            G.addEdge(new Graph.Edge(b, a, 1));
        }
        startWithRoot = new int[N];
        ret = new int[N];
        cn = new int[N];
        delta = new int[N];
        mustStartWith = new boolean[N];
        dfs(0, -1);

//        if (testNumber == 4)
//            G.viz(String.valueOf(testNumber), false, 1000, 1000);

        out.println(ret[0]);
    }

    private void dfs(int cur, int fa) {
        startWithRoot[cur] = ret[cur] = 0;
        cn[cur] = 1;
        int tot = 0;
        List<Integer> chds = new ArrayList<>();
        for (Edge e : G.adj[cur]) {
            int chd = e.getTo();
            if (chd == fa) continue;
            dfs(chd, cur);
            cn[cur] += cn[chd];
            tot++;
            if (cn[chd] == 1) {
                continue;
            }
            chds.add(chd);
        }

        int noSingle = chds.size();
        int single = tot - noSingle;

        if (noSingle == 0) {
            if (single > 0)
                ret[cur] = startWithRoot[cur] = 1;
            return;
        }

        int canMerge = 0;
        int mustStarts = 0;
        for (int i = 0; i < chds.size(); ++i) {
            int chd = chds.get(i);
            if (mustStartWith[chd]) mustStarts++;
            int acc = mustStartWith[chd] ? startWithRoot[chd] : ret[chd];
            if (acc == startWithRoot[chd]) canMerge++;
        }
        int res = 0;
        if (mustStarts == chds.size()) {
            for (int i = 0; i < chds.size(); ++i) {
                int chd = chds.get(i);
                res += startWithRoot[chd];
                delta[chd] = -startWithRoot[chd] + startWithRoot[chd];
            }
        } else {
            for (int i = 0; i < chds.size(); ++i) {
                int chd = chds.get(i);
                res += ret[chd];
                delta[chd] = -ret[chd] + startWithRoot[chd];
            }
        }
        ret[cur] = res - canMerge / 2;

        if (single > 0) {
            if (canMerge % 2 == 1)
                startWithRoot[cur] = ret[cur];
            else
                startWithRoot[cur] = ret[cur] + 1;
        } else if (canMerge > 0) {
            startWithRoot[cur] = res - (canMerge - 1) / 2;
        } else {
            Collections.sort(chds, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return delta[o1] - delta[o2];
                }
            });
            startWithRoot[cur] = res + delta[chds.get(0)];
        }
        if (ret[cur] < startWithRoot[cur] && canMerge == 0)
            mustStartWith[cur] = true;

        assert ret[cur] >= 0 : ret[cur];
        assert startWithRoot[cur] >= ret[cur];

    }
}
