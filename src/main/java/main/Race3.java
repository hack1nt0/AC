package main;

import template.graph_theory.BidirectionalGraph;
import template.graph_theory.UndirectionalGraph;
import template.numbers.GaussJordanElimination;
import template.numbers.GaussianElimination;
import java.io.StringReader;
import java.util.*;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: race3
*/

public class Race3 {
    int MAX_N = 50;
    List<Integer>[] adj = new ArrayList[MAX_N];

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = -1;
        //BidirectionalGraph graph = new BidirectionalGraph(MAX_N);
        for (int i = 0; i < MAX_N; ++i) adj[i] = new ArrayList<>();

        //double[][] A = new double[MAX_N][MAX_N];
        for (int from = 0;; ++from) {
            Scanner strin = new Scanner(new StringReader(in.nextLine()));
            while (true) {
                int to = strin.nextInt();
                if (to == -1) {
                    N = from;
                    break;
                }
                if (to == -2) break;
                adj[from].add(to);
            }
            if (N != -1) break;
        }
        int S = 0, T = N - 1;
//        for (int i = 0; i < MAX_N; ++i) {
//            for (int j = 0; j < MAX_N; ++j) if (A[i][j] != 0) A[i][i] = 1;
//        }
//        Arrays.fill(A[0], 0);
//        A[0][0] = 1;
//        double[] b = new double[MAX_N];
//        b[0] = 1;
//
//        GaussianElimination gauss = new GaussianElimination(A, b);
//        //GaussJordanElimination gauss = new GaussJordanElimination(A, b);
//        out.println(Arrays.toString(gauss.primal()));

        List<Integer> unavoids = new ArrayList<>();
        for (int unavoid = 1; unavoid < N - 1; ++unavoid) {
            boolean[] visited = new boolean[N];
            if (!reachable(S, T, unavoid, visited)) unavoids.add(unavoid);
        }
        out.print(unavoids.size());
        for (int i = 0; i < unavoids.size(); ++i) out.print(" " + unavoids.get(i));
        out.println();

//        UndirectionalGraph graph = new UndirectionalGraph(N);
//        for (int from = 0; from < N; ++from) {
//            for (int to : adj[from]) {
//                graph.addEdge(from, to);
//                //graph.addEdge(to, from);
//            }
//        }
//        boolean[] isCut = graph.cut();
//        isCut[S] = isCut[T] = false;
//        List<Integer> cuts = new ArrayList<>();
////        for (int i = 0; i < N; ++i) if (isCut[i]) cuts.add(i);
//
//        List<Set<Integer>> bccList = graph.bcc();
//        for (Set<Integer> bcc : bccList) System.out.println(bcc);
//
//        for (int i = 0; i < N; ++i) {
//            if (!isCut[i]) continue;
//            boolean ok = false;
//            for (int j = 0; j < bccList.size(); ++j) if (bccList.get(j).contains(S) && bccList.get(j).contains(i)) {
//                for (int k = 0; k < bccList.size(); ++k) if (bccList.get(k).contains(T) && bccList.get(k).contains(i)) {
//                    ok = true; break;
//                }
//                if (ok) break;
//            }
//            if (ok) cuts.add(i);
//        }
//        out.print(cuts.size());
//        for (int i = 0; i < cuts.size(); ++i) out.print(" " + cuts.get(i));
//        out.println();

        List<Integer> cuts = new ArrayList<>();
        for (int cut : unavoids) {
            boolean[] left = new boolean[N];
            visit(S, cut, left);
            left[cut] = false;
            boolean[] visited = new boolean[N];
            if (!back(cut, left, visited)) cuts.add(cut);
        }
        out.print(cuts.size());
        for (int i = 0; i < cuts.size(); ++i) out.print(" " + cuts.get(i));
        out.println();

//        BidirectionalGraph graph = new BidirectionalGraph(N);
//        for (int i = 0; i < N; ++i) {
//            for (int j : adj[i]) graph.addEdge(i, j);
//        }
//        graph.show();

    }

    private boolean reachable(int cur, int t, int unavoid, boolean[] visited) {
        if (cur == t) return true;
        if (cur == unavoid) return false;
        if (visited[cur]) return false;
        visited[cur] = true;

        for (int to : adj[cur]) {
            if (reachable(to, t, unavoid, visited)) return true;
        }
        return false;
    }

    private void visit(int cur, int t, boolean[] visited) {
        if (cur == t) return;
        if (visited[cur]) return;
        visited[cur] = true;
        for (int to : adj[cur]) visit(to, t, visited);
    }

    private boolean back(int cur, boolean[] left, boolean[] visited) {
        if (left[cur]) return true;
        if (visited[cur]) return false;
        visited[cur] = true;

        for (int to : adj[cur]) {
            if (back(to, left, visited)) return true;
        }
        return false;
    }
}
