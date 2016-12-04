package main;

import java.util.*;

public class WolfHockeyTeamEasy {
    int MOD = (int)1e9 + 7;
    int N;

    public int count(int N, int K) {
        this.N = N;
        long factN = 1;
        for (int i = 1; i <= N; ++i) factN = factN * i % MOD;

        if (2 * N - 1 <= K)
            return 0;

        //(i, k, j)
        //(i + 1, k, j) = (i, k, j - 1) + (i, k - 1, j + 1)
        // i = 2 * k + j
        //(k, j) = (k - 1, j - 1) + (k, j - 1)
        long[][] dp = new long[N][2 * N];
        Arrays.fill(dp[0], 1);

        long res = 0;
        if (K <= N - 1) res += 1;

/*        for (int i = 0; i < 2 * N - 1; ++i) {
            for (int k = 0; k < N; ++k) {
                for (int j = 0; j < 2 * N; ++j) {
                    if (k > 0 && j + 1 < 2 * N)
                        dp[k][j] += dp[k - 1][j + 1];
                    if (j > 0)
                        dp[k][j] += dp[k][j - 1];
                }

            }

            if (i >= K) {
                int k = i - N + 1;
                long acc = dp[k][i + 1 - 2 * k];
                res = (res + acc) % MOD;
            }
        }*/


/*        for (int k = 0; k < N - 1; ++k) {
            for (int i = 0; i < nxt.length; ++i)
                Arrays.fill(nxt[i], 0);

            for (int i = 0; i < 2 * N; ++i)
                for (int j = 0; j <= i + 1; ++j) {
                    if (j + 1 <= 2 * N)
                        nxt[i + 1][j] = cur[i][j + 1];
                    if (j > 0)
                        nxt[i + 1][j] = (nxt[i + 1][j] + nxt[i][j - 1]) % MOD;
                }

            long curCnt = nxt[k + N + 1][N + k + 1 - 2 * (k + 1)];
            //System.out.println(curCnt);
            if (k + N >= K)
                res = (res + curCnt) % MOD;

            long[][] tmp = cur;
            cur = nxt;
            nxt = tmp;
        }*/

        dp1 = new HashMap<Tuple, Long>();

        for (int fmax = N; fmax < 2 * N - 1; ++fmax) {
            int k = fmax - N + 1;
            long acc = rec(fmax + 1, k, fmax + 1 - 2 * k);
            if (fmax >= K)
                res = (res + acc) % MOD;
            //System.out.println(res);
        }

        res = res * 2 % MOD * factN % MOD;
        return (int)res;
    }

    private long rec(int i, int k, int j) {
        Tuple curTuple = new Tuple(i, k, j);

        if (i >= 0 && j >= 0 && k >= 0 && dp1.containsKey(curTuple))
            return dp1.get(curTuple);

        long res = 0;
        if (i >= 0 && k == 0 && j >= 0)
            res = 1;
        //else if (i != 2 * k + j)
         //   res = 0;
        else {
            if (j > 0)
                res = (res + rec(i - 1, k, j - 1)) % MOD;
            if (j + 1 < 2 * N)
                res = (res + rec(i - 1, k - 1, j + 1)) % MOD;

            dp1.put(curTuple, res);
        }
        return res;
    }

    Map<Tuple, Long> dp1;

    class Tuple {
        int i, k, j;

        public Tuple(int i, int k, int j) {
            this.i = i;
            this.k = k;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Tuple tuple = (Tuple) o;

            if (i != tuple.i) return false;
            if (k != tuple.k) return false;
            return j == tuple.j;

        }

        @Override
        public int hashCode() {
            int result = i;
            result = 31 * result + k;
            result = 31 * result + j;
            return result;
        }
    }

    static Set<String> set = new HashSet<String>();

    public static void main(String[] args) {
        int n = 3;
        boolean[] used = new boolean[n * 2];
        dfs(0, n * 2, "", used);
        ArrayList<String> lst = new ArrayList<String>(set);
        Collections.sort(lst);
        System.out.println("NUM: " + lst.size());
        for (String per: lst) System.out.println(per);
    }

    private static void dfs(int cur, int tot, String per, boolean[] used) {
        if (cur == tot) {
            String fh = per.substring(0, per.length() / 2);
            char[] fhc = fh.toCharArray();
            String lh = per.substring(per.length() / 2);
            char[] lhc = lh.toCharArray();
            String seq = "";
            Arrays.sort(fhc);
            seq += fhc[fhc.length - 1];
            Arrays.sort(lhc);
            seq += lhc[lhc.length - 1];

            for (int i = 0; i < fh.length(); ++i)
                seq += (char)Math.max(fh.charAt(i), lh.charAt(i));
            set.add(seq);
            return;
        }

        for (int i = 0; i < used.length; ++i) {
            if (used[i]) continue;
            used[i] = true;
            dfs(cur + 1, tot, per + (char)('0' + i), used);
            used[i] = false;
        }
    }
}
