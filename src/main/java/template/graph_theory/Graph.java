package template.graph_theory;

import edu.princeton.cs.algs4.Draw;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dy on 16-12-1.
 */
public class Graph {
    public static class Edge extends template.graph_theory.Edge {
        public int from;
        public int to;
        public int cost;

        public Edge(int a, int b, int cost) {
            this.from = a;
            this.to = b;
            this.cost = cost;
        }

        @Override
        public int getFrom() {
            return from;
        }

        @Override
        public int getCost() {
            return cost;
        }

        @Override
        public int getTo() {
            return to;
        }
    }

    public int N, M;
    public List<template.graph_theory.Edge>[] adj;

    public Graph(int N) {
        this.N = N;
        adj = new List[N];
        for (int i = 0; i < N; ++i) adj[i] = new ArrayList<>();
    }

    public void addEdge(template.graph_theory.Edge edge) {
        //d[a][b] = cost;
        adj[edge.getFrom()].add(edge);
        M++;
    }

    public List<template.graph_theory.Edge> adj(int node) {
        return adj[node];
    }

    public boolean isTree() {
        int[] vis = new int[N];
        return isTreeHelper(0, -1, vis);
    }

    private boolean isTreeHelper(int cur, int fa, int[] vis) {
        if (vis[cur] != 0) return false;
        vis[cur] = -1;
        for (template.graph_theory.Edge e : adj[cur]) {
            if (e.getTo() == fa) continue;
            if (!isTreeHelper(e.getTo(), cur, vis)) return false;
        }
        vis[cur] = 1;
        return true;
    }

    @Override
    public String toString() {
        StringBuffer res = new StringBuffer(N + " " + M + "\n");
        for (int i = 0; i < N; ++i)
            for (template.graph_theory.Edge e : adj[i]) res.append(i + "-->" + e.getTo() + " " + e.getCost() + "\n");
        return res.toString();
    }


    String edgeStyle = " -- ";
    String graphStyle = "graph ";
    String title = "tmp";
    int w, h;
    String dot = null;
    int root = 0;
    public void viz(String title, boolean directed, int w, int h) {
        this.title = "NO." + title;
        this.w = (w + 99) / 100;
        this.h = (h + 99) / 100;
        edgeStyle = directed ? " -> " : " -- ";
        graphStyle = directed ? "digraph " : " graph ";
        //type: dag, tree, null(else)
        StringBuffer dot = new StringBuffer(graphStyle + title + " {");

        if (isTree()) {
            visTree(root, -1, dot);
        } else if (isDag()) {
            dot.append("rankdir=LR;\n")
                    .append("node[group=main];\n");
            boolean[] vis = new boolean[N];
            visDag(root, vis, dot);
        } else {
            throw new UnsupportedOperationException();

        }

        this.dot = dot.append("}").toString();
        visDot();
    }

    private void visDag(int cur, boolean[] vis, StringBuffer dot) {
        if (vis[cur]) return;
        vis[cur] = true;

        for (template.graph_theory.Edge e : adj[cur]) {
            dot.append(cur).append(edgeStyle).append(e.getTo()).append(';');
            visDag(e.getTo(), vis, dot);
        }
    }

    private boolean isDag() {
        int[] vis = new int[N];
        return isDagHelper(0, -1, vis);
    }

    private boolean isDagHelper(int cur, int fa, int[] vis) {
        if (vis[cur] == -1) return false;
        vis[cur] = -1;
        for (template.graph_theory.Edge e : adj[cur]) {
            if (e.getTo() == fa) continue;
            if (!isDagHelper(e.getTo(), cur, vis)) return false;
        }
        vis[cur] = 1;
        return true;
    }

    private void visDot() {
        File tmp = null;
        try {
            tmp = File.createTempFile(title, ".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuffer commd = new StringBuffer("echo \"")
                .append(dot).append("\"")
                .append(" | dot -Tpng -Gsize=").append(w).append(",").append(h).append("\\! > ").append(tmp.getAbsolutePath());
        //System.err.println(commd.toString());
        try {
            Runtime.getRuntime().exec(new String[] { "bash", "-c", commd.toString()}).waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Draw draw = new Draw(title);
        draw.setCanvasSize(w * 100, h * 100);
        draw.picture(.5, .5, tmp.getAbsolutePath());
        while (!tmp.delete());
        //tmp.deleteOnExit();
    }

    private void visTree(int cur, int fa, StringBuffer dot) {
        for (template.graph_theory.Edge e : adj[cur]) {
            if (e.getTo() == fa) continue;
            dot.append(cur).append(edgeStyle).append(e.getTo()).append(' ');
        }
        for (template.graph_theory.Edge e : adj[cur]) {
            if (e.getTo() == fa) continue;
            visTree(e.getTo(), cur, dot);
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(3);
        g.addEdge(new Edge(0, 1, 1));
        g.addEdge(new Edge(0, 2, 1));
        g.addEdge(new Edge(1, 2, 1));
        g.addEdge(new Edge(1, 2, 1));
        g.addEdge(new Edge(1, 2, 1));
        g.addEdge(new Edge(1, 2, 1));
        g.viz("null", true, 1000, 1000);
    }

}
