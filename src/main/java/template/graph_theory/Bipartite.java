package template.graph_theory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by dy on 2018/6/14.
 */
public class Bipartite {
    public static boolean is(List<Integer>[] adj, int[] color) {
        int N = adj.length;
        if (color == null)
            color = new int[N];
        Arrays.fill(color, -1);
        for (int i = 0; i < N; ++i) {
            if (color[i] != -1)
                continue;
            Queue<Integer> queue = new LinkedList<>();
            color[i] = 0;
            queue.add(i);
            while (queue.size() > 0) {
                int cur = queue.poll();
                for (int nxt : adj[cur]) {
                    if (color[nxt] == -1) {
                        color[nxt] = color[cur] ^ 1;
                        queue.add(nxt);
                    } else if (color[nxt] == color[cur])
                        return false;
                }
            }
        }
        return true;
    }

    public static int[] maxMatch(List<Integer>[] adj, int left) {
        int n = adj.length;
        int[] to = new int[n]; Arrays.fill(to, -1);
        int[] from = new int[n]; Arrays.fill(from, -1);
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(visited, false);
            maxMatch(i, adj, to, from, visited);
        }
        return to;
    }

    private static boolean maxMatch(int cur, List<Integer>[] adj, int[] to, int[] from, boolean[] visited) {
        for (int nxt : adj[cur]) {
            if (visited[nxt])
                continue;
            if (from[nxt] == -1) {
                from[nxt] = cur;
                to[cur] = nxt;
                return true;
            }
            visited[nxt] = true;
            if (maxMatch(from[nxt], adj, to, from, visited)) {
                from[nxt] = cur;
                to[cur] = nxt;
                return true;
            }
        }
        return false;
    }
}
