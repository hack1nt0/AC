package main;

import java.util.Arrays;

public class DengklekGaneshAndChains {
    public String getBestChains(String[] chains, int[] lengths) {
        int N = lengths.length, M = chains.length;
        To[][] tos = new To[N][M];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                tos[i][j] = new To(j, maxString(chains[j], lengths[i]));
            }
            Arrays.sort(tos[i]);
            for (int j = 0; j < M; ++j) {
                tos[i][j].rank = j == 0 ? 0 : (tos[i][j].compareTo(tos[i][j - 1]) == 0 ? tos[i][j - 1].rank : tos[i][j - 1].rank + 1);
            }
        }
        boolean[][] graph = new boolean[N][M];
        int[] to = new int[N]; Arrays.fill(to, -1);
        int[] from = new int[M]; Arrays.fill(from, -1);
        boolean[] visited = new boolean[M];
        for (int i = 0; i < N; ++i) {
            for (int rank = 0; rank < M; ++rank) {
                for (int j = 0; j < M; ++j) {
                    if (tos[i][j].rank == rank) {
                        graph[i][tos[i][j].which] = true;
                    }
                }
                Arrays.fill(visited, false);
                if (sat(i, graph, to, from, visited))
                    break;
                for (int j = 0; j < M; ++j) {
                    if (tos[i][j].rank == rank) {
                        graph[i][tos[i][j].which] = false;
                    }
                }
            }
        }
        String ans = "";
        for (int i = 0; i < N; ++i)
            ans += maxString(chains[to[i]], lengths[i]);
        return ans;
    }

    private boolean sat(int cur, boolean[][] graph, int[] to, int[] from, boolean[] visited) {
        for (int nxt = 0; nxt < graph[cur].length; ++nxt) {
            if (!graph[cur][nxt] || visited[nxt])
                continue;
            if (from[nxt] == -1) {
                from[nxt] = cur;
                to[cur] = nxt;
                return true;
            }
            visited[nxt] = true;
            if (sat(from[nxt], graph, to, from, visited)) {
                from[nxt] = cur;
                to[cur] = nxt;
                return true;
            }
//            visited[nxt] = false;
        }
        return false;
    }

    private String maxString(String string, int length) {
        String s = string + string;
        String max = s.substring(0, length);
        for (int i = 1; i < string.length(); ++i) {
            String cur = s.substring(i, i + length);
            if (cur.compareTo(max) > 0)
                max = cur;
        }
        return max;
    }

    class To implements Comparable<To> {
        int which, rank;
        String string;
        public To(int which, String string) {
            this.which = which;
            this.string = string;
        }

        @Override
        public int compareTo(To o) {
            return o.string.compareTo(string);
        }
    }
}
