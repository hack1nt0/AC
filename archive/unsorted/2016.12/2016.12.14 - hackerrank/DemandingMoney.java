package main;

import template.Bits;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;

/**
 * Meet-in-the-Middle(MITM) attack (cipher)
 */
public class DemandingMoney {
    int[] moneys;
    long[] adj;
    Map<Long, Integer> maxMoney;
    Map<Long, Long> howMany;
    //int MAXN = 34;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int M = in.nextInt();
        moneys = new int[N];
        for (int i = 0; i < N; ++i) moneys[i] = in.nextInt();
        adj = new long[N];
        for (int i = 0; i < M; ++i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            adj[a] |= 1L << b;
            adj[b] |= 1L << a;
        }
        //for (int i = 0; i < N; ++i) adj[i] |= 1L << i;
        //FIRST
        long retMax = 0;
        long retCnt = 0;

        int K = N / 2;
        long[] firstMax = new long[1 << K];
        long[] firstCnt = new long[1 << K];
        boolean[] indepCache = new boolean[1 << K];
        for (int i = 0; i < 1 << K; ++i) indepCache[i] = indep(i);

        for (int s = 0; s < 1 << K; ++s) {
            //s is an Independent Set
            long tmp = 0;
            for (int house : Bits.elements(s)) {
                tmp += moneys[house];
            }
            if (indepCache[s]) {
                firstMax[s] = tmp;
                firstCnt[s] = 1;
            }
            for (int sub : Bits.subset(s)) {
                if (sub == s) continue;
                //if (sub == 0) continue;
                if (!indepCache[sub]) continue;

                if (firstMax[sub] > firstMax[s]) {
                    firstMax[s] = firstMax[sub];
                    //if (firstCnt[sub] != 1) throw new RuntimeException();
                    firstCnt[s] = 1;
                } else if (firstMax[sub] == firstMax[s]) {
                    firstCnt[s] += 1;
                }
            }
        }

        //SECOND
        int c = 0;
        for (int ts = 0; ts < 1 << N - K; ++ts) {
            long s = (long)ts << K;
            //System.err.println(s);
            //s is an Independent Set
            if (!indep(s)) continue;
            //System.err.println(++c);
            long invalid = 0;
            int max = 0;
            for (int house : Bits.elements(s)) {
                invalid |= adj[house];
                max += moneys[house];
            }


            int firstS = (int) ((~invalid) & ((1L << K) - 1));
            long res = max + firstMax[firstS];
            if (res > retMax) {
                retMax = res;
                retCnt = firstCnt[firstS];
                //System.err.println(Long.toBinaryString(s | firstS) + " " + retMax + " " + firstMax[firstS] + " " + firstCnt[firstS]);
            } else if (res == retMax) {
                retCnt += firstCnt[firstS];
                //System.err.println(Long.toBinaryString(s | firstS) + " " + retMax + " " + firstMax[firstS] + " " + firstCnt[firstS]);
            }
            //System.err.println(retMax + " " + retCnt);
        }

        out.println(retMax + " " + retCnt);
    }

    private boolean indep(long s) {
        long invalid = 0;
        for (int house : Bits.elements(s)) {
            invalid |= adj[house];
        }
        return (invalid & s) == 0;
    }

    public void solveDp(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int M = in.nextInt();
        moneys = new int[N];
        for (int i = 0; i < N; ++i) moneys[i] = in.nextInt();
        adj = new long[N];
        for (int i = 0; i < M; ++i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            adj[a] |= 1L << b;
            adj[b] |= 1L << a;
        }
        for (int i = 0; i < N; ++i) adj[i] |= 1L << i;
        maxMoney = new HashMap<Long, Integer>();
        int maxm = dfs(0);
        out.print(maxm + " ");
        howMany = new HashMap<Long, Long>();
        long roads = dfs1(0);
        out.println(roads);
    }

    private int dfs(long s) {
        if (maxMoney.containsKey(s))
            return maxMoney.get(s);

        int res = -1;
        int house = (int)(s >> 34);
        if (house >= adj.length) {
            res = 0;
        } else {

            long mask = s & ((1L << 34) - 1);

            int res1 = dfs(mask | (house + 1L) << 34);
            if ((mask & 1L << house) != 0) {
                res = res1;
            } else {

                mask |= adj[house];
                mask |= 1L << house;
                int res2 = moneys[house] + dfs(mask | (house + 1L) << 34);

                res = Math.max(res1, res2);
            }
        }
        maxMoney.put(s, res);
        return res;
    }

    private long dfs1(long s) {
        if (howMany.containsKey(s)) return howMany.get(s);

        int house = (int)(s >> 34);
        if (house >= adj.length) {
            howMany.put(s, 1L);
            return 1;
        }

        long mask = s & ((1L << 34) - 1);
        long ns = mask | (house + 1L) << 34;
        long res = 0;
        if (maxMoney.get(s).equals(maxMoney.get(ns)))
            res += dfs1(ns);
        if ((mask & 1L << house) != 0) {
            howMany.put(s, res);
            return res;
        }

        mask |= adj[house];
        mask |= 1L << house;
        ns = mask | (house + 1L) << 34;
//        try {
        if (maxMoney.get(s) == moneys[house] + maxMoney.get(ns))
            res += dfs1(ns);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        howMany.put(s, res);
        return res;
    }

}
