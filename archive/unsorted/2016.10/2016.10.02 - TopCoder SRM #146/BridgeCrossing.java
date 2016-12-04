package main;

import java.util.*;

public class BridgeCrossing {
    Map<BitSet, Integer> dp;
    int[] T;
    int N;

    public int minTime(int[] times) {
        T = times;
        dp = new HashMap<BitSet, Integer>();
        N = times.length + 1;
        int ans = dfs(new BitSet(N));
        return ans;
    }

    private int dfs(BitSet state) {
        if (dp.containsKey(state)) return dp.get(state);

        int res = Integer.MAX_VALUE;
        boolean ok = state.cardinality() == N;
        if (ok) {
            res = 0;
        } else {
            // -->
            if (!state.get(N - 1)) {
                state.set(N - 1);

                for (int i = 0; i < N - 1; ++i) if (!state.get(i))
                    for (int j = i + 1; j < N - 1; ++j) if (!state.get(j)) {
                        state.set(i);
                        state.set(j);
                        res = Math.min(res, dfs(state) + Math.max(T[i], T[j]));
                        state.clear(i);
                        state.clear(j);
                    }
                if (state.cardinality() + 2 > N) {
                    for (int i = 0; i < N - 1 ; ++i) if (!state.get(i)) {
                        state.set(i);
                        res = Math.min(res, dfs(state) + T[i]);
                        state.clear(i);
                        break;
                    }
                }

                state.clear(N - 1);
            } else {
                state.clear(N - 1);

                int minRId = -1;
                for (int i = 0; i < N - 1 ; ++i)
                    if (state.get(i) && (minRId == -1 || T[i] < T[minRId])) minRId = i;
                state.clear(minRId);
                res = Math.min(res, dfs(state) + T[minRId]);
                state.set(minRId);

                state.set(N - 1);
            }
        }

        dp.put((BitSet) state.clone(), res);
        return res;
    }

}
