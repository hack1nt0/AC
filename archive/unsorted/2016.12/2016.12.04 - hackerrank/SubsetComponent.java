package main;



import template.UnionFind;

import java.math.BigInteger;
import java.util.*;
import java.io.PrintWriter;

//one may use some d[] based union find, not bits based. O(N*64) -> O(N*N)
public class SubsetComponent {
    int N;
    long[] d;
    int nbits;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        N = in.nextInt();
        nbits = 64;
        d = new long[N];
        for (int i = 0; i < N; ++i) {
            d[i] = new BigInteger(in.next()).longValue();
        }
        long ret = 0;
//        out.println((long)Math.pow(2, 63));
//        out.println(Long.MAX_VALUE);

//        List<Integer>[] ibs = new ArrayList[N];
//        for (int i = 0; i < N; ++i) ibs[i] = elements(d[i]);
//        List<Integer>[] ids = new ArrayList[1 << N];
//        for (int i = 0; i < 1 << N; ++i) ids[i] = elements(i);
//        //System.out.println("done");
//
//        for (int s = 0; s < 1 << N; ++s) {
//            //UnionFind uf = new UnionFind(nbit);
//            long res = 0;
//            boolean[] vis = new boolean[ids[s].size()];
//            for (int i = 0; i < ids[s].size(); ++i) {
//                long di = d[ids[s].get(i)];
//                if (vis[i] || di == 0) continue;
//                vis[i] = true;
//                long or = di;
//                for (int j = i + 1; j < ids[s].size(); ++j) {
//                    long dj = d[ids[s].get(j)];
//                    if (vis[j] || dj == 0) continue;
//                    if ((or & dj) != 0) {
//                        or |= dj;
//                        vis[j] = true;
//                    }
//                }
//                res += Math.min(0, - Long.bitCount(or) + 1);
//
//                //res += Math.max(Long.MIN_VALUE, - Long.bitCount(or) + 1);
//            }
//            //System.out.println(res);
//            ret += res + nbits;

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
        ret = dfs(0, new ArrayList<Integer>());
        out.println(ret);
    }


    private long dfs(int cur, ArrayList<Integer> ids) {
        if (cur >= N) {
            long res = 0;
            boolean[] vis = new boolean[ids.size()];
            for (int i = 0; i < ids.size(); ++i) {
                long di = d[ids.get(i)];
                if (vis[i] || di == 0) continue;
                vis[i] = true;
                long or = di;
                for (int j = i + 1; j < ids.size(); ++j) {
                    long dj = d[ids.get(j)];
                    if (vis[j] || dj == 0) continue;
                    if ((or & dj) != 0) {
                        or |= dj;
                        vis[j] = true;
                    }
                }
                res += Math.min(0, -Long.bitCount(or) + 1);
            }
            return res + nbits;
        }

        long res = dfs(cur + 1, ids);
        ids.add(cur);
        res += dfs(cur + 1, ids);
        ids.remove(ids.size() - 1);

        return res;
    }
}
