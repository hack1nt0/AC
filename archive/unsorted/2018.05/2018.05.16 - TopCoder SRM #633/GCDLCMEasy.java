package main;

import template.graph_theory.BidirectionalGraph;
import template.graph_theory.CCTarjan;
import template.numbers.IntUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GCDLCMEasy {

    class Node {
        int i, prime, count;

        public Node(int i, int prime, int count) {
            this.i = i;
            this.prime = prime;
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (i != node.i) return false;
            if (prime != node.prime) return false;
            return count == node.count;
        }

        @Override
        public int hashCode() {
            int result = i;
            result = 31 * result + prime;
            result = 31 * result + count;
            return result;
        }

        boolean collide(Node o) {
            return i == o.i && prime == o.prime;
        }
    }

    class Indexer<T> {
        Map<T, Integer> ids = new HashMap<T, Integer>();
        List<T> objs = new ArrayList<T>();

        int getId(T obj) {
            return ids.get(obj);
        }

        T getObj(int id) {
            return objs.get(id);
        }

        int add(T obj) {
            if (!ids.containsKey(obj)) {
                ids.put(obj, ids.size());
                objs.add(obj);
            }
            return ids.get(obj);
        }

        int size() {
            return ids.size();
        }
    }

    class Edge {
        int from, to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (x != pair.x) return false;
            return y == pair.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        Pair intersect(Pair o) {
            return new Pair(Math.max(x, o.x), Math.min(y, o.y));
        }
    }
    public String possible(int n, int[] A, int[] B, int[] G, int[] L) {
        String YES = "Solution exists";
        String NO = "Solution does not exist";
        Indexer<Node> indexer = new Indexer<>();
        List<Edge> edges = new ArrayList<>();
        Map<Pair, Pair> nodes = new HashMap<>();
        for (int i = 0; i < A.length; ++i) {
            if (!(L[i] >= G[i] && L[i] % G[i] == 0))
                return NO;
            Map<Integer, Integer> factors1 = IntUtils.decompose(G[i]);
            Map<Integer, Integer> factors2 = IntUtils.decompose(L[i]);
            for (Map.Entry<Integer, Integer> entry2 : factors2.entrySet()) {
                int prime = entry2.getKey();
                int max = entry2.getValue();
                int min = factors1.containsKey(prime) ? factors1.get(prime) : 0;
                Pair bound = new Pair(min, max);
                {
                    Pair pair = new Pair(A[i], prime);
                    if (nodes.containsKey(pair) && !valid(nodes.get(pair), bound)) {
                        return NO;
                    }
                    if (nodes.containsKey(pair))
                        nodes.put(pair, nodes.get(pair).intersect(bound));
                    else
                        nodes.put(pair, bound);
                }
                {
                    Pair pair = new Pair(B[i], prime);
                    if (nodes.containsKey(pair) && !valid(nodes.get(pair), bound)) {
                        return NO;
                    }
                    if (nodes.containsKey(pair))
                        nodes.put(pair, nodes.get(pair).intersect(bound));
                    else
                        nodes.put(pair, bound);
                }
                Node a1 = new Node(A[i], prime, min);
                Node b1 = new Node(B[i], prime, min);
                Node a2 = new Node(A[i], prime, max);
                Node b2 = new Node(B[i], prime, max);
                int ia1 = indexer.add(a1);
                int ib1 = indexer.add(b1);
                int ia2 = indexer.add(a2);
                int ib2 = indexer.add(b2);
                edges.add(new Edge(ia1, ib2));
                edges.add(new Edge(ia2, ib1));
            }
        }
        int N = indexer.size();
        BidirectionalGraph graph = new BidirectionalGraph(N);
        for (Edge edge : edges) {
            graph.addEdge(edge.from, edge.to);
        }
        int[] scc = new CCTarjan(graph).whichSCC();
        List<Node>[] sccList = new ArrayList[N];
        for (int i = 0; i < N; ++i)
            sccList[i] = new ArrayList<>();
        for (int i = 0; i < N; ++i) {
            Node cur = indexer.getObj(i);
            if (sccList[scc[i]].size() > 0) {
                for (Node old : sccList[scc[i]]) if (cur.collide(old))
                    return NO;
            }
            sccList[scc[i]].add(cur);
        }
        return YES;
    }

    private boolean valid(Pair pair, Pair bound) {
        if (pair.equals(bound))
            return true;
        if (pair.x == pair.y && (pair.x == bound.x || pair.x == bound.y))
            return true;
        if (bound.x == bound.y && (bound.x == pair.x || bound.x == pair.y))
            return true;
        return false;
    }
}
