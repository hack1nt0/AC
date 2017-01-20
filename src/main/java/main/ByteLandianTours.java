package main;

import template.graph_theory.Edge;
import template.graph_theory.Graph;
import template.numbers.IntegerUtils;

import java.util.Scanner;
import java.io.PrintWriter;
import static template.numbers.IntegerUtils.*;

/**
 * WA : some draws
 *
 * Finally got it. TreeDp with some greedy and combination math
 */
public class ByteLandianTours {
    long[] chd2Root, cycle, cn, chd2chd;
    Graph G;
    long[] fact;
    int testNumber;
    long MOD = (long)1e9 + 7;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.testNumber = testNumber;
        int N = in.nextInt();
        G = new Graph(N);
        for (int i = 0; i < N - 1; ++i) {
            int a = in.nextInt();
            int b = in.nextInt();
            //if (testNumber == 14) System.err.println(a + " " + b);
            G.addEdge(new Graph.Edge(a, b, 1));
            G.addEdge(new Graph.Edge(b, a, 1));
        }
        chd2Root = new long[N];
        chd2chd = new long[N];
        cycle = new long[N];
        cn = new long[N];
        IntegerUtils.modulus = MOD;
        fact = new long[N]; fact[0] = 1;
        for (int i = 1; i < N; ++i) fact[i] = mul(i, fact[i - 1]);

        dfs(0, -1);
        out.println(cycle[0]);
        //if (testNumber == 14) System.err.println(cycle[0]);
    }

    private void dfs(int cur, int fa) {
        chd2Root[cur] = cycle[cur] = chd2chd[cur] = 0;
        cn[cur] = 1;

        int tot, single, schd = -1;
        tot = single = 0;
        long base = 1;
        for (Edge e : G.adj[cur]) {
            int chd = e.getTo();
            if (chd == fa) continue;
            dfs(chd, cur);
            cn[cur] += cn[chd];
            tot++;
            if (cn[chd] == 1) single++;
            schd = chd;
            base = mul(base, chd2Root[chd]);
        }
        if (tot == 0) {
            chd2Root[cur] = 1;
            cycle[cur] = 1;
            return;
        }
        if (tot == 1 && cn[cur] == 2) {
            chd2Root[cur] = 1;
            return;
        }
        if (tot == 1) {
            //cycle[cur] = chd2Root[chd];
            cycle[cur] = base;
            cycle[cur] = mul(cycle[cur], 2);
            cycle[cur] = add(cycle[cur], chd2chd[schd]);
            chd2Root[cur] = base;
            return;
        }

        cycle[cur] = chd2chd[cur] = chd2Root[cur] = base;
        int noSingle = tot - single;
        if (0 < noSingle && noSingle <= 2) {
            cycle[cur] = mul(cycle[cur], fact[tot - noSingle], 2);
        } else if (noSingle == 0) {
            cycle[cur] = mul(cycle[cur], fact[tot]);
        } else {
            cycle[cur] = 0;
        }

        if (noSingle == 1) {
            chd2Root[cur] = mul(chd2Root[cur], fact[tot - 1]);
        } else if (noSingle == 0) {
            chd2Root[cur] = mul(chd2Root[cur], fact[tot]);
        } else {
            chd2Root[cur] = 0;
        }

        if (tot >= 2) {
            if (noSingle == 0)
                chd2chd[cur] = mul(fact[tot], tot - 1);
            else if (noSingle == 1) {
                long perm = mul(fact[tot - 1], 2);
                perm = add(perm , mul(fact[tot - 1], (tot - 1 - 1), 2));

                chd2chd[cur] = mul(chd2chd[cur], perm);
            }
            else if (noSingle == 2)
                chd2chd[cur] = mul(chd2chd[cur], fact[tot - 1] * 2 % MOD);
            else
                chd2chd[cur] = 0;
        } else
            chd2chd[cur] = 0;
    }
}
