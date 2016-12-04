package template;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dy on 16-12-1.
 */
public class Graph {
    public static class Edge {
        public int b, cost;

        public Edge(int b, int cost) {
            this.b = b;
            this.cost = cost;
        }
    }

    int N, M;
    long[][] d;
    public List<Edge>[] adj;

    public Graph(int N) {
        this.N = N;
        d = new long[N][N];
        adj = new List[N];
        for (int i = 0; i < N; ++i) adj[i] = new ArrayList<Edge>();
    }

    public void addE (int a, int b, int cost) {
        d[a][b] = cost;
        adj[a].add(new Edge(b, cost));
        M++;
    }
}
