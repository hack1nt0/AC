package main;

import java.util.*;
import java.io.PrintWriter;
/*
check cycles on a sub-graph(all nodes reachable s S and reaching t B)
 */
public class KingdomConnectivity {
    int S, T;
    int[] vis;
    boolean[] valid; // can reach T
    List<Integer>[] adj;


    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int M = in.nextInt();
        long MODULAR =(long)1e9;
        adj = new ArrayList[N];
        List<Integer>[] iadj = new List[N];
        for (int i = 0; i < N; ++i) {
            adj[i] = new ArrayList<Integer>();
            iadj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < M; ++i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            adj[a].add(b);
            iadj[b].add(a);
        }
        S = 0; T = N - 1;
        vis = new int[N];
        valid = new boolean[N];
        validate(T, iadj);

        Arrays.fill(vis, 0);
        if (hasCycle(S)) {
            out.println("INFINITE PATHS");
            return;
        }

        Arrays.fill(vis, 0);
        List<Integer> dag = new ArrayList<Integer>();
        topoSort(S, dag);
        Collections.reverse(dag);
        long[] ret = new long[N]; ret[S] = 1;
        for (int i = 0; i < dag.size() - 1; ++i) {
            int a = dag.get(i);
            for (int b : adj[dag.get(i)]) ret[b] = (ret[b] + ret[a]) % MODULAR;
        }
        out.println(ret[T]);
    }

    private void topoSort(int cur, List<Integer> dag) {
        if (vis[cur] != 0) return;
        if (cur == T) {
            dag.add(T);
            return;
        }
        for (int chd : adj[cur]) if (valid[chd]) topoSort(chd, dag);
        vis[cur] = 1;
        dag.add(cur);
    }

    private boolean hasCycle(int cur) {
        if (vis[cur] == -1) return true;
        if (vis[cur] == 1) return false;
        vis[cur] = -1;

        for (int chd : adj[cur]) if (valid[chd]) if (hasCycle(chd)) return true;

        vis[cur] = 1;
        return false;
    }

    private void validate(int cur, List<Integer>[] iadj) {
        if (vis[cur] != 0) return;
        vis[cur] = 1;
        valid[cur] = true;
        for (int chd : iadj[cur]) validate(chd, iadj);
    }

}
