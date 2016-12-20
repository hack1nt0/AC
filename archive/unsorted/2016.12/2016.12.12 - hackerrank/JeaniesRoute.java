package main;

import template.Graph;

import java.util.*;
import java.io.PrintWriter;
/*
* treeDp, find some no-simple path in the tree
* Mapping the diversity of paths to the diversity of node, which are: node in the border of path? or inner of path.
* e[i] : the min-path-len with node i inner the path
* f[i] : the min-path-len with node i at one of the borders of the path
* g[i] : the min-path-len with node i being the both borders of the path
* h[i] : the min-path-len with node i
*
* bounder case CAUTION.
*
 */
public class JeaniesRoute {
    boolean[] isDest;
    Graph G;
    int[] f, g, h, dests, e;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int K = in.nextInt();
        isDest = new boolean[N];
        for (int i = 0; i < K; ++i) isDest[in.nextInt() - 1] = true;
        G = new Graph(N);
        for (int i = 0; i < N - 1; ++i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int c = in.nextInt();
            G.addE(a, b, c);
            G.addE(b, a, c);
        }
        f = new int[N];
        g = new int[N];
        h = new int[N];
        e = new int[N];
        dests = new int[N];
        dfs(0, -1);
        out.println(h[0]);
    }

    private void dfs(int cur, int fa) {
        if (isDest[cur]) dests[cur] += 1;
        List<Integer> idChds = new ArrayList<Integer>();
        final List<Integer> eChds = new ArrayList<Integer>();
        for (Graph.Edge e : G.adj[cur]) if (e.b != fa) {
            int chd = e.b;
            dfs(chd, cur);
            dests[cur] += dests[chd];
            if (dests[chd] > 0) {
                idChds.add(chd);
                eChds.add(e.cost);
            }
        }
        if (idChds.size() == 0) {
            return;
        }

        if (idChds.size() == 1) {
            int chd = idChds.get(0);
            int ec = eChds.get(0);
            f[cur] = f[chd] + ec;
            g[cur] = g[chd] + ec * 2;
            e[cur] = e[chd] + ec * 2;

            if (isDest[cur]) {
                h[cur] = Integer.MAX_VALUE;
                h[cur] = Math.min(h[cur], f[cur]);
                //h[cur] = Math.min(h[cur], g[cur]);
                h[cur] = Math.min(h[cur], e[cur]);
            }
            else h[cur] = h[chd];

            return;
        }

        List<Integer> fge = new ArrayList<Integer>();
        for (int i = 0; i < idChds.size(); ++i) {
            int chd = idChds.get(i);
            fge.add(f[chd] - g[chd] - eChds.get(i));
        }
        Collections.sort(fge);

        if (fge.size() < 2) {
            throw new RuntimeException();
        }

        for (int i = 0; i < idChds.size(); ++i) {
            int chd = idChds.get(i);
            g[cur] += g[chd] + eChds.get(i) * 2;
        }

        e[cur] = g[cur] + fge.get(0) + fge.get(1);
        for (int chd : idChds) e[cur] = Math.min(e[cur], g[cur] - g[chd] + e[chd]);
        f[cur] = g[cur] + fge.get(0);

//        f[cur] = e[cur] = Long.MAX_VALUE;
//        for (int i = 0; i < idChds.size(); ++i) {
//            long F = f[idChds.get(i)] + eChds.get(i);
//            for (int j = 0; j < idChds.size(); ++j) if (j != i)
//                F += g[idChds.get(j)] + eChds.get(j) * 2;
//            f[cur] = Math.min(f[cur], F);
//        }
//        for (int i = 0; i < idChds.size(); ++i)
//            for (int j = i + 1; j < idChds.size(); ++j) {
//                long BH = f[idChds.get(i)] + eChds.get(i) + f[idChds.get(j)] + eChds.get(j);
//                for (int k = 0; k < idChds.size(); ++k) if (k != i && k != j) {
//                    BH += g[idChds.get(k)] + eChds.get(k) * 2;
//                }
//                e[cur] = Math.min(e[cur], BH);
//            }
//
//        if (e[cur] != g[cur] + fge.get(0) + fge.get(1)) throw new RuntimeException();
//        if (f[cur] != g[cur] + fge.get(0)) throw new RuntimeException();

        h[cur] = Integer.MAX_VALUE;
        h[cur] = Math.min(h[cur], f[cur]);
        //h[cur] = Math.min(h[cur], g[cur]);
        h[cur] = Math.min(h[cur], e[cur]);

        return;
    }
}
