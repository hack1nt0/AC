package main;

import template.collection.sets.SetUtils;
import template.debug.InputReader;
import template.graph_theory.AbstractEdge;
import template.graph_theory.BidirectionalGraph;
import template.graph_theory.CCTarjan;
import template.operation.MaxFlow;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

/*
 ID: hackint1
 LANG: JAVA
 TASK: schlnet
*/

public class Schlnet {
    int ans1 = Integer.MAX_VALUE;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.readInt();
        boolean[][] closure = new boolean[n][n];
        BidirectionalGraph graph = new BidirectionalGraph(n);
        for (int i = 0; i < n; ++i) {
            while (true) {
                int r = in.readInt();
                if (r == 0) break;
                r--;
                closure[i][r] = true;
                graph.addEdge(i, r);
            }
            closure[i][i] = true;
        }
        graph.show();
//        for (int k = 0; k < n; ++k) for (int i = 0; i < n; ++i) for (int j = 0; j < n; ++j) closure[i][j] |= closure[i][k] & closure[k][j];
//        Set<Integer>[] closureSet = new HashSet[n];
//        for (int i = 0; i < n; ++i) {
//            closureSet[i] = new HashSet<>();
//            for (int j = 0; j < n; ++j) if (closure[i][j]) closureSet[i].add(j);
//        }
//        dfs(0, 0, closureSet, new HashSet<Integer>());
//        out.println(ans1);

        BidirectionalGraph dag = new CCTarjan(graph).dag();
        dag.show();
//        BidirectionalGraph bipartite = new BidirectionalGraph(dag.V() * 2 + 2);
//        MaxFlow maxFlow = new MaxFlow(dag.V() * 2 + 2);
//        for (int i = 0; i < dag.V(); ++i) {
//            for (AbstractEdge e : dag.adj(i)) {
//                int j = e.other(i) + dag.V();
////                bipartite.addEdge(i, j);
//                maxFlow.addEdge(i, j, 1);
//            }
//        }
//        for (int i = 0; i < dag.V(); ++i) {
////            bipartite.addEdge(dag.V() * 2, i);
////            bipartite.addEdge(dag.V() + i, dag.V() * 2 + 1);
//            maxFlow.addEdge(dag.V() * 2, i, 1);
//            maxFlow.addEdge(dag.V() + i, dag.V() * 2 + 1, 1);
//        }
//        int ans2 = dag.V() - maxFlow.maxFlow(dag.V() * 2, dag.V() * 2 + 1);
//        if (dag.V() == 1) ans2 = 0;
//        out.println(ans2);
        int x, y;
        x = y = 0;
        for (int i = 0; i < dag.V(); ++i) {
            if (dag.indegree(i) == 0) x++;
            if (dag.outdegree(i) == 0) y++;
        }
        out.println(x);
        out.println(dag.V() == 1 ? 0 : Math.max(x, y));
    }

    private void dfs(int cur, int starts, Set<Integer>[] closureSet, Set<Integer> visited) {
        if (starts >= ans1) return;
        boolean ok = visited.size() == closureSet.length;
        if (ok) {
            ans1 = Math.min(ans1, starts);
            return;
        }
        int maxClosureSize = 0;
        int maxVisited = 0;
        for (int i = cur; i < closureSet.length; ++i) {
            int size = closureSet[i].size();
            maxVisited += size;
            maxClosureSize = Math.max(maxClosureSize, size);
        }
        if (maxVisited < closureSet.length - visited.size()) return;
        int lb = starts + (closureSet.length - visited.size() + maxClosureSize - 1) / maxClosureSize;
        if (ans1 <= lb) return;

        HashSet<Integer> union = new HashSet<>();
        SetUtils.union(visited, closureSet[cur], union);
        dfs(cur + 1, starts + 1, closureSet, union);

        dfs(cur + 1, starts, closureSet, visited);

    }

}
