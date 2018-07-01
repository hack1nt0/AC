package main;

import java.util.*;
import java.io.PrintWriter;

public class TaskB {
    class Node {
        int l, r;
        public Node(int l, int r) {
            this.l = l;
            this.r = r;
        }

        public boolean valid() {
            return l <= r;
        }
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int P = in.nextInt();
        int[] R = new int[N];
        for (int i = 0; i < N; ++i)
            R[i] = in.nextInt();
        int[][] Q = new int[N][P];
        for (int i = 0; i < N; ++i)
            for (int j = 0; j < P; ++j)
                Q[i][j] = in.nextInt();
        Node[][] nodes = new Node[N][P];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < P; ++j) {
                // R * m * 0.9 <= Q <= R * m * 1.1
                // Q / R * 10 / 11 <= m <= Q / R * 10 / 9
                int l = (Q[i][j] * 10 + R[i] * 11 - 1) / (R[i] * 11);
                int r = (Q[i][j] * 10) / (R[i] * 9);
                nodes[i][j] = new Node(l, r);
            }
            Arrays.sort(nodes[i], new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    if (o1.l != o2.l)
                        return o1.l - o2.l;
                    else
                        return o1.r - o2.r;
                }
            });
        }
        int ans = 0;
        boolean[][] visited = new boolean[N][P];
        for (int i = 0; i < P; ++i) {
            Node root = nodes[0][i];
            if (root.valid() && dfs(0, i, root.l, root.r, visited, nodes))
                ++ans;
        }
        out.println("Case #" + testNumber + ": " + ans);
    }

    private boolean dfs(int r, int c, int L, int R, boolean[][] visited, Node[][] nodes) {
        int nr = r + 1;
        if (nr == visited.length)
            return true;
        for (int nc = 0; nc < visited[nr].length; ++nc) {
            Node child = nodes[nr][nc];
            if (visited[nr][nc] || !child.valid())
                continue;
            int nL = Math.max(L, child.l);
            int nR = Math.min(R, child.r);
            if (nL > nR)
                continue;
            visited[nr][nc] = true;
            if (dfs(nr, nc, nL, nR, visited, nodes))
                return true;
            visited[nr][nc] = false;
        }
        return false;
    }
}
