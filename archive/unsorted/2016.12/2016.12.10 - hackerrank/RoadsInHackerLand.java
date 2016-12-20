package main;

import template.Graph;
import template.MinSpanningTree;

import java.math.BigInteger;
import java.util.BitSet;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.TreeMap;

//timeout due to BigInteger, need customized version of that.
public class RoadsInHackerLand {

    BigInteger ret = BigInteger.ZERO;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int M = in.nextInt();
        MinSpanningTree minSpanningTree = new MinSpanningTree(N);
        for (int i = 0; i < M; ++i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int c = in.nextInt();
            minSpanningTree.addE(a, b, c);
            minSpanningTree.addE(b, a, c);
        }

        //System.err.println(minSpanningTree.cost());
        Graph tree = minSpanningTree.tree();
        //System.err.println(tree);
        dfs(0, -1, tree.adj);
        out.println(ret.toString(2));
    }

    private int dfs(int cur, int fa, List<Graph.Edge>[] adj) {
        int res = 1;
        for (Graph.Edge e : adj[cur]) {
            if (e.b == fa) continue;
            //System.err.println(cur + " " + e.b);
            int chds = dfs(e.b, cur, adj);
            BigInteger cure = BigInteger.ONE.shiftLeft(e.cost);
            BigInteger cnte = BigInteger.valueOf((long)chds * (adj.length - chds));

            //System.err.println(cure.toString(2) + " " + cnte);

            ret = ret.add(cure.multiply(cnte));
            res += chds;
        }
        return res;
    }

}
