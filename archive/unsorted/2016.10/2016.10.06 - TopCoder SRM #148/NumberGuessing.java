package main;

import java.util.*;

public class NumberGuessing {
    int[] G;
    int MAXN = 0;
    int MINN = 0;
    int ans = -1;
    int ansId = -1;
    int MAXV = 0;
    Integer[] rank;
    public int bestGuess(int range, int[] guesses, int numLeft) {
        MAXN = range + 1;
        G = new int[numLeft + 1 + guesses.length + 2];
        G[0] = 0;
        G[G.length - 1] = MAXN;
        Arrays.sort(guesses);
        for (int i = 0; i < guesses.length; ++i) G[i + 1] = guesses[i];
        rank = new Integer[G.length];
        for (int i = 0; i < rank.length; ++i) rank[i] = i;

        ansId = guesses.length + 1;
        ans = dfs(ansId, G.length - 2);

        return ans;
    }

    private int dfs(int cur, int TOT) {
        if (cur == TOT) {
            int res = -1;
            int maxv = 0;
            G[cur] = MAXN + 1;
            Arrays.sort(rank, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return G[o1] - G[o2];
                }
            });
            for (int i = 0; i < rank.length - 2; ++i) {
                int from = G[rank[i]], to = G[rank[i + 1]];
                if (to - from - 1 == 0) continue;
                if (from == 0 && to == MAXN) {
                    int curv = MAXN - 1;
                    if (res == -1 || maxv < curv) {
                        res = 1;
                        maxv = curv;
                    }
                }
                else if (from == 0 && to > 1) {
                    int curv = to - 1;
                    if (res == -1 || maxv < curv) {
                        res = to - 1;
                        maxv = curv;
                    }
                }
                else if (from < MAXN - 1 && to == MAXN) {
                    int curv = MAXN - from - 1;
                    if (res == -1 || maxv < curv) {
                        res = from + 1;
                        maxv = curv;
                    }
                }
                else {
                    int curv = 1 + (to - from - 2) / 2;
                    if (res == -1 || maxv < curv) {
                        res = from + 1;
                        maxv = curv;
                    }
                }
            }
            if (res == -1) throw new RuntimeException();
            G[cur] = res;

            return res;
        }

        int maxv = 0;
        int res = -1;
        //bottom -> top
        for (int i = 1; i < MAXN; ++i) {
            boolean ok = true;
            for (int j = 1; j < cur; ++j) if (G[j] == i) ok = false;
            if (!ok) continue;
            G[cur] = i;
            dfs(cur + 1, TOT);

            Arrays.sort(rank, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return G[o1] - G[o2];
                }
            });
            int curv = 0;
            for (int j = 1; j < rank.length - 1; ++j) {
                if (G[rank[j]] != i) continue;
                int from = G[rank[j - 1]], to = G[rank[j + 1]];
                if (from == 0) curv += G[rank[j]] - 1;
                else curv += (G[rank[j]] - from - 1) / 2;
                if (to == MAXN) curv += MAXN - G[rank[j]] - 1;
                else curv += (to - G[rank[j]] - 1) / 2;
            }
            if (res == -1 || maxv < curv) {
                res = i;
                maxv = curv;
            }
        }
        G[cur] = res;

        //top -> bottom
        dfs(cur + 1, TOT);

        return res;
    }

}
