package main;

import template.debug.InputReader;
import template.debug.OutputWriter;
import template.graph_theory.BidirectionalGraph;
import template.operation.MaxFlow;

public class CostumeChange {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                grid[i][j] = in.readInt() + n;
        int ncolor = n * 2 + 1;
        int[][] count = new int[n * 2][ncolor];
        for (int i = 0; i < n * 2; ++i) {
            if (i < n) {
                int row = i;
                for (int col = 0; col < n; ++col)
                    count[i][grid[row][col]]++;
            } else {
                int col = i - n;
                for (int row = 0; row < n; ++row)
                    count[i][grid[row][col]]++;
            }
        }
        int[][] node = count;
        int nV = 0;
        int nL = 0, nR = 0;
        for (int i = 0; i < count.length; ++i)
            for (int j = 0; j < count[i].length; ++j)
                if (count[i][j] > 0) {
                    node[i][j] = nV;
                    nV++;
                    if (i < n) nL++;
                    else nR++;
                } else
                    node[i][j] = -1;
        if (nV != nL + nR)
            throw new RuntimeException();
        int S = nV, T = nV + 1;
        BidirectionalGraph graph = new BidirectionalGraph(nV + 2);
        for (int i = 0; i < nL; ++i)
            graph.addEdge(S, i, 1, 0);
        for (int i = 0; i < nR; ++i)
            graph.addEdge(i + nL, T, 1, 0);
        int nMaybe = 0;
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j) {
                int u = node[i][grid[i][j]];
                int v = node[j + n][grid[i][j]];
                if (u != -1 && v != -1) {
                    graph.addEdge(u, v, 1, 0);
                }
                if (u != -1 || v != -1)
                    nMaybe++;
            }
        int ans = n * n - (new MaxFlow(graph, S, T).getMaxFlow());
        out.printLine("Case #" + testNumber + ": " + ans);
    }
}
