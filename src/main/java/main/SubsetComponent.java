package main;



import template.UnionFind;

import java.math.BigInteger;
import java.util.*;
import java.io.PrintWriter;

public class SubsetComponent {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int nbit = 64;
        long[] d = new long[N];
        for (int i = 0; i < N; ++i) {
            d[i] = new BigInteger(in.next()).longValue();
        }
        long ret = 0;
//        out.println((long)Math.pow(2, 63));
//        out.println(Long.MAX_VALUE);

        List<Integer>[] ibs = new ArrayList[N];
        for (int i = 0; i < N; ++i) ibs[i] = elements(d[i]);
        List<Integer>[] ids = new ArrayList[1 << N];
        for (int i = 0; i < 1 << N; ++i) ids[i] = elements(i);
        //System.out.println("done");

        for (int s = 0; s < 1 << N; ++s) {
            //UnionFind uf = new UnionFind(nbit);
            long res = 0;
            boolean[] vis = new boolean[ids[s].size()];
            for (int i = 0; i < ids[s].size(); ++i) {
                long di = d[ids[s].get(i)];
                if (vis[i] || di == 0) continue;
                vis[i] = true;
                long or = di;
                for (int j = i + 1; j < ids[s].size(); ++j) {
                    long dj = d[ids[s].get(j)];
                    if (vis[j] || dj == 0) continue;
                    if ((or & dj) != 0) {
                        or |= dj;
                        vis[j] = true;
                    }
                }
                res += Math.min(0, - Long.bitCount(or) + 1);

                //res += Math.max(Long.MIN_VALUE, - Long.bitCount(or) + 1);
            }
            //System.out.println(res);
            ret += res + nbit;

//            for (int id : ids[s]) {
//                //if ((s >> id & 1) == 0) continue;
//                int preb = -1;
//                //for (int ib = d[id].nextSetBit(0); ib >= 0; ib = d[id].nextSetBit(ib + 1)) {
////                    for (int ib = 0; ib < nbit; ++ib)
////                     if ((d[id] >> ib & 1) != 0) {
//                for (int ib : ibs[id]) {
//                    if (preb == -1) preb = ib;
//                    uf.union(preb, ib);
//                    preb = ib;
//                }
//            }
            //System.out.println(uf.count());
            //ret += uf.count();
        }
        out.println(ret);
    }

    Map<Long, Integer> cacheLog; //= new HashMap<Double, Integer>();
    private List<Integer> elements(long s) {
        if (cacheLog == null) {
            cacheLog = new HashMap<Long, Integer>();
            for (int i = 0; i < 64; ++i) cacheLog.put(1L << i, i);
        }
        List<Integer> ret = new ArrayList<Integer>();
        while (true) {
            if (s == 0) break;
            long ns = s & (s - 1);
            ret.add(cacheLog.get(s - ns));
            s = ns;
        }
        return ret;
    }
}
