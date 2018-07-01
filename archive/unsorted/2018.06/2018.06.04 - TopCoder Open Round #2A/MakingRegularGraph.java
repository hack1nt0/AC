package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MakingRegularGraph {
    public int[] addEdges(int n, int[] x, int[] y) {
        if (n <= 2)
            return new int[]{-1};
        int[] degrees = new int[n];
        boolean[][] adj = new boolean[n][n];
        for (int e = 0; e < x.length; ++e) {
            degrees[x[e]]++;
            degrees[y[e]]++;
            adj[x[e]][y[e]] = adj[y[e]][x[e]] = true;
        }
        List<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < n; ++i)
            if (degrees[i] < 2)
                nodeList.add(new Node(i, degrees[i]));
        Collections.sort(nodeList);
        List<Integer> edgeList = new ArrayList<>();
        boolean good = dfs(0, nodeList, edgeList, adj);
        if (good) {
            int[] ans = new int[edgeList.size()];
            for (int i = 0; i < ans.length; ++i) ans[i] = edgeList.get(i);
            return ans;
        } else {
//            throw new RuntimeException();
            return new int[]{-1};
        }
    }

    private boolean dfs(int cur, List<Node> nodeList, List<Integer> edgeList, boolean[][] adj) {
        if (cur == nodeList.size())
            return true;
        Node curNode = nodeList.get(cur);
        if (curNode.deg == 2)
            return dfs(cur + 1, nodeList, edgeList, adj);
//        if (cur == nodeList.size() - 1)
//            return false;
//        if (cur == nodeList.size() - 2 && adj[cur][cur + 1])
//            return false;
        for (int nxt = cur + 1; nxt < nodeList.size(); ++nxt) {
            Node nxtNode = nodeList.get(nxt);
            if (!adj[curNode.ind][nxtNode.ind] && nxtNode.deg < 2) {
                adj[curNode.ind][nxtNode.ind] = adj[nxtNode.ind][curNode.ind] = true;
                curNode.deg++;
                nxtNode.deg++;
                edgeList.add(curNode.ind);
                edgeList.add(nxtNode.ind);
                if (dfs(cur, nodeList, edgeList, adj))
                    return true;
                adj[curNode.ind][nxtNode.ind] = adj[nxtNode.ind][curNode.ind] = false;
                curNode.deg--;
                nxtNode.deg--;
                edgeList.remove(edgeList.size() - 1);
                edgeList.remove(edgeList.size() - 1);
            }
        }
//        System.out.println(curNode);
        return false;
    }

    class Node implements Comparable<Node>{
        int ind, deg;

        public Node(int ind, int deg) {
            this.ind = ind;
            this.deg = deg;
        }

        @Override
        public int compareTo(Node o) {
            return ind - o.ind;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "ind=" + ind +
                    ", deg=" + deg +
                    '}';
        }
    }
}
