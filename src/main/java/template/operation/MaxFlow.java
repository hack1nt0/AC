package template.operation;

import template.collection.sequence.ArrayUtils;
import template.debug.Stopwatch;
import template.graph_theory.AbstractEdge;
import template.graph_theory.BidirectionalGraph;

import java.util.*;

/**
 * Created by dy on 16-10-1.
 */
public class MaxFlow {

    private int N, M;
    private List<AbstractEdge>[] adj;
    private List<AbstractEdge> edges; // only real edges, without RESIDUAL edges.
    private int[] dist;
    private int[] curE;

    public MaxFlow(int N) {
        this.N = N;
        adj = new ArrayList[N];
        edges = new ArrayList<>();
        for (int i = 0; i < adj.length; ++i) adj[i] = new ArrayList<>();
        dist = new int[N];
        curE = new int[N];
    }

    public void addE(int u, int v, int cap) {
        AbstractEdge e = creatEdge(u, v);
        AbstractEdge rev = creatEdge(v, u);
        edges.add(e);
        e.setId(M++);
        rev.setId(-1); // id=-1 indicates the REVERSAL Edge
        e.setCapacity(cap);
        rev.setCapacity(0);
        e.setReversalEdge(rev);
        rev.setReversalEdge(e);
        adj[u].add(e);
        adj[v].add(rev);
    }

    private AbstractEdge creatEdge(int from, int to) {
        return new AbstractEdge() {
            int capacity;
            AbstractEdge reversalEdge;
            int id;
            int flow;

            @Override
            public int getFrom() {
                return from;
            }

            @Override
            public int getTo() {
                return to;
            }

            @Override
            public int getCapacity() {
                return capacity;
            }

            @Override
            public void setCapacity(int capacity) {
                this.capacity = capacity;
            }

            @Override
            public int getFlow() {
                return flow;
            }

            @Override
            public void setFlow(int flow) {
                this.flow = flow;
            }

            @Override
            public AbstractEdge getReversalEdge() {
                return reversalEdge;
            }

            @Override
            public void setReversalEdge(AbstractEdge reversalEdge) {
                this.reversalEdge = reversalEdge;
            }

            @Override
            public void setId(int id) {
                this.id = id;
            }

            @Override
            public int getId() {
                return id;
            }

            @Override
            public String toString() {
                return id + "," + capacity + "," + flow;
            }
        };
    }

    public int maxFlow(int s, int t) {
        return maxFlow(s, t, null);
    }

    public int maxFlow(int s, int t, List<AbstractEdge> cuts) {
        reset();
        int maxflow = 0;
        while (true) {
            int dt = bfs(s, t);
            if (dt == Integer.MAX_VALUE) break;
            Arrays.fill(curE, 0);
            while (true) {
                int f = dfs(s, t, Integer.MAX_VALUE);
                if (f <= 0) break;
                maxflow += f;
            }
        }
        if (cuts != null) {
            /**
             * Found min-min-cut-set (min-num-edges & alphabet order of edge ids)
             */
            Integer[] index = (Integer[])ArrayUtils.inbox(ArrayUtils.index(M));
            Arrays.sort(index, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    AbstractEdge e1 = edges.get(o1), e2 = edges.get(o2);
                    int c1 = e1.getCapacity() + e1.getFlow();
                    int c2 = e2.getCapacity() + e2.getFlow();
                    if (c1 != c2) return c2 - c1;
                    return e1.getId() - e2.getId();
                }
            });
            int flows = maxflow;
            for (int rank = 0; rank < M; ++rank) {
                if (flows == 0) break;
                AbstractEdge e = edges.get(index[rank]);
                e.setVisited();
                int curFlow = maxFlow(s, t);
                if (flows - curFlow == e.getCapacity() + e.getFlow()) {
                    flows = curFlow;
                    cuts.add(e);
                    continue;
                }
                e.notVisited();
            }
            for (AbstractEdge e : edges) e.notVisited();

            /**
             * Found an arbitrary min-cut-set
             */
//            boolean[] S = new boolean[N];
//            cutsHelper(s, S);
//            for (AbstractEdge e : edges) if (S[e.getFlow()] && !S[e.getTo()] && e.getFlow() > 0) cuts.add(e);

        }
        return maxflow;
    }

    /**
     * MAKE SURE the class(same graph structure) can be invoked (maxFlow method) multiplied times.
     */
    private void reset() {
        for (AbstractEdge e : edges) {
            e.setCapacity(e.getCapacity() + e.getFlow());
            e.setFlow(0);
            AbstractEdge rev = e.getReversalEdge();
            rev.setCapacity(0);
            rev.setFlow(0);
        }
    }

    private int dfs(int s, int t, int curMinc) {
        if (s == t) return curMinc;

        while (curE[s] < adj[s].size()) {
            AbstractEdge e = adj[s].get(curE[s]++);
            if (e.getVisited()) continue; // dynamic deletion of edge
            int chd = e.other(s);
            if (e.getCapacity() > 0 && dist[chd] > dist[s]) {
                int minc = dfs(chd, t, Math.min(curMinc, e.getCapacity()));
                if (minc > 0) {
                    e.setCapacity(e.getCapacity() - minc);
                    e.setFlow(e.getFlow() + minc);
                    AbstractEdge rev = e.getReversalEdge();
                    rev.setCapacity(rev.getCapacity() + minc);
                    return minc;
                }
            }
        }

        return 0;
    }

    private int bfs(int s, int t) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(s);
        while (!que.isEmpty()) {
            int cur = que.poll();
            if (cur == t) break;
            for (AbstractEdge e : adj[cur]) {
                if (e.getVisited()) continue; // dynamic deletion of edge
                int chd = e.other(cur);
                if (dist[chd] != Integer.MAX_VALUE || e.getCapacity() <= 0) continue;
                dist[chd] = dist[cur] + 1;
                que.add(chd);
            }
        }
        return dist[t];
    }

    private void cutsHelper(int cur, boolean[] S) {
        if (S[cur]) return;
        S[cur] = true;
        for (AbstractEdge e : adj[cur]) if (e.getCapacity() > 0) cutsHelper(e.other(cur), S);
    }

    public void show() {
        BidirectionalGraph graph = new BidirectionalGraph(N);
        for (AbstractEdge e : edges) graph.addEdge(e);
        graph.show();
    }
}
