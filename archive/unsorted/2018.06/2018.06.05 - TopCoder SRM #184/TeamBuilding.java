package main;

import template.graph_theory.GraphUtils;

import java.util.*;

public class TeamBuilding {
    public int fewestPaths(String[] paths) {
        int n = paths.length;
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; ++i)
            adj[i] = new ArrayList<>();
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
            if (paths[i].charAt(j) == '1') {
                adj[i].add(j);
            }
//        GraphUtils.vizDirected(adj);
        int[] scc = scc(adj);
        int min = 0;
        for (int i = 0; i < n; ++i)
            if (scc[i] == i && paths[i].charAt(i) == '0')
                min++;
        int[] in = new int[n];
        int[] out = new int[n];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                if (paths[i].charAt(j) == '1' && scc[i] != scc[j]) {
                    out[scc[i]]++;
                    in[scc[j]]++;
                }
        Map<Integer, Integer> sccCount = new HashMap<>();
        for (int i : scc) sccCount.put(i, sccCount.containsKey(i) ? sccCount.get(i) + 1 : 1);
        int nsource = 0, nsink = 0, nsingle = 0;
        for (int i : sccCount.keySet()) {
            if (in[i] == 0 && out[i] != 0)
                nsource++;
            if (in[i] != 0 && out[i] == 0)
                nsink++;
            if (in[i] == 0 && out[i] == 0 && sccCount.get(i) == 1 && paths[i].charAt(i) == '0')
                nsingle++;
        }
        return nsource + nsink - Math.min(nsource, nsink) + nsingle;
    }

    int[] scc(List<Integer>[] adj) {
        int n = adj.length;
        int[] which = new int[n];
        int count = 0;
        int[] preOrder = new int[n];
        Arrays.fill(preOrder, -1);
        int[] lowLink = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; ++i)
            if (preOrder[i] == -1)
                count += scc(i, count, preOrder, adj, which, lowLink, stack);
        return which;
    }

    private int scc(int cur, int count, int[] preOrder, List<Integer>[] adj, int[] which, int[] lowLink, Stack<Integer> stack) {
        preOrder[cur] = count++;
        lowLink[cur] = preOrder[cur];
        stack.push(cur);
        for (int nxt : adj[cur]) {
            if (preOrder[nxt] == -1)
                count += scc(nxt, count, preOrder, adj, which, lowLink, stack);
            lowLink[cur] = Math.min(lowLink[cur], lowLink[nxt]);
        }
        if (lowLink[cur] == preOrder[cur]) {
            while (true) {
                int top = stack.pop();
                which[top] = cur;
                lowLink[top] = Integer.MAX_VALUE;
                if (top == cur)
                    break;
            }
        }
        return count;
    }
}
