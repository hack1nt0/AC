package main;

public class VendingMachine {
    public int motorUse(String[] prices, String[] purchases) {
        int[][] P = new int[prices.length][];
        for (int i = 0; i < prices.length; ++i) {
            String[] tmp = prices[i].split("[ ]");
            P[i] = new int[tmp.length];
            for (int j = 0; j < tmp.length; ++j) P[i][j] = Integer.valueOf(tmp[j]);
        }

        Purchase[] purs = new Purchase[purchases.length];
        for (int i = 0; i < purchases.length; ++i) {
            String[] tmp = purchases[i].split("[,:]");
            purs[i] = new Purchase(Integer.valueOf(tmp[0]), Integer.valueOf(tmp[1]), Integer.valueOf(tmp[2]));
        }

        int N = P.length, M = P[0].length;
        int ans = 0;
        int curm = 0;
        int[] mtot = new int[M];
        for (int i = 0; i < P.length; ++i)
            for (int j = 0; j < P[i].length; ++j) mtot[j] += P[i][j];
        for (int i = 0; i < purs.length; ++i) {
            if (i == 0) {
                int nm = 0;
                for (int j = 0; j < M; ++j) if (mtot[j] > mtot[nm])
                    nm = j;
                ans += Math.min(Math.abs(nm - curm), M - Math.abs(nm - curm));
                curm = nm;
            }

            int nn = purs[i].n;
            int nm = purs[i].m;
            if (P[nn][nm] == 0) return -1;

            ans += Math.min(Math.abs(nm - curm), M - Math.abs(nm - curm));
            curm = nm;
            mtot[nm] -= P[nn][nm];
            P[nn][nm] = 0;

            if (i + 1 >= purs.length || purs[i + 1].t - purs[i].t >= 5) {
                nm = 0;
                for (int j = 0; j < M; ++j) if (mtot[j] > mtot[nm])
                    nm = j;
                ans += Math.min(Math.abs(nm - curm), M - Math.abs(nm - curm));
                curm = nm;
            }

        }
        return ans;
    }

    static class Purchase {
        int n, m, t;

        public Purchase(int n, int m, int t) {
            this.n = n;
            this.m = m;
            this.t = t;
        }
    }
}
