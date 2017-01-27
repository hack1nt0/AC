package main;

import template.Graph;
import template.MinCostFlow;

import java.util.*;
import java.io.PrintWriter;

/*
Quote: When in doubt, use brute forces.
Here, Doubt means whether the greed strategy works or not; Brute forces means min cost max flow.

To guarantee all passengers to be choosen here, you need a -INF cost edge between two splited nodes of one passenger.
 */
public class Ticket {
    class TK {
        String dest;
        double cost;
        int leftCnt;

        public TK (String dest, double cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    int N, S, T;
    int qcnt;
    int[] que;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        N = in.nextInt();
        int M = in.nextInt();
        int K = in.nextInt();

        Map<String, Double> dest2Price = new HashMap<String, Double>();
        String[] dest = new String[N];
        for (int i = 0; i < K; ++i) dest2Price.put(in.next(), in.nextDouble());
        for (int i = 0; i < N; ++i) dest[i] = in.next();
        MinCostFlow minCostFlow = new MinCostFlow(2 * N + 2);
        double INF = 50000;
        S = N * 2;
        T = N * 2 + 1;
        for (int i = 0; i < N; ++i) {
            int ia = 2 * i, ib = ia + 1;
            minCostFlow.addE(ia, ib, 1, -INF);
            double c = dest2Price.get(dest[i]);
            minCostFlow.addE(S, ia, 1, 0);
            minCostFlow.addE(ib, T, 1, c);
            for (int j = i - 1; j >= 0; --j) {
                int ja = 2 * j, jb = ja + 1;
                if (dest[i].equals(dest[j])) minCostFlow.addE(ib, ja, 1, c * 0.8);
                else minCostFlow.addE(ib, ja, 1, c);
            }
        }
        double ret = minCostFlow.minCost(S, T, M);
        out.println(ret + INF * N);
        que = new int[2 * N + 2];
        dfs(S, minCostFlow.adj);
        for (int i = 0; i < N; ++i) out.println(que[i * 2]);

//        TK[] tks = new TK[K];
//        for (int i = 0; i < K; ++i) {
//            tks[i] = new TK(in.next(), in.nextDouble());
//        }
//        TK[] passengers = new TK[N];
//        for (int i = 0; i < N; ++i) {
//            String dest = in.next();
//            for (int j = 0; j < K; ++j) if (dest.equals(tks[j].dest)) {
//                passengers[i] = tks[j];
//                tks[j].leftCnt++;
//                break;
//            }
//        }
//        TK[] queTail = new TK[M];
//        int[] p2q = new int[N];
//        double ret = 0;
//        for (int i = 0; i < N; ++i) {
//            passengers[i].leftCnt--;
//            int pos = -1;
//            for (int j = 0; j < queTail.length; ++j)
//                if (queTail[j] != null && queTail[j].dest.equals(passengers[i].dest)) pos = j;
//            if (pos != -1) {
//                ret += passengers[i].cost * 0.8;
//                p2q[i] = pos;
//                continue;
//            }
//
//            pos = -1;
//            for (int j = 0; j < queTail.length; ++j)
//                if (queTail[j] == null) { pos = j; break;}
//            if (pos != -1) {
//                p2q[i] = pos;
//                queTail[pos] = passengers[i];
//                ret += passengers[i].cost;
//                continue;
//            }
//
//            pos = -1;
//            for (int j = 0; j < queTail.length; ++j) {
//                if (queTail[j].leftCnt == 0) {
//                    pos = j;
//                    break;
//                }
//                if (pos == -1 || queTail[j].cost < queTail[pos].cost) pos = j;
//            }
//            if (pos != -1) {
//                queTail[pos] = passengers[i];
//                p2q[i] = pos;
//                ret += passengers[i].cost;
//                continue;
//            }
//        }
//
//        out.printlnTable(ret);
//        for (int i = 0; i < N; ++i) out.printlnTable(p2q[i] + 1);
    }

    private int dfs(int cur, ArrayList<MinCostFlow.Edge>[] adj) {
        if (cur == T) {
            return que[cur] = ++qcnt;
        }
        int cnt = 0;
        if (cur != S && cur % 2 == 0) {
            return que[cur] = dfs(cur + 1, adj);
        }
        for (MinCostFlow.Edge e : adj[cur]) if (e.cap == 0 && (e.b < cur || e.b == T)) {
            if (cur != S && cnt >= 1) {
                throw new RuntimeException();
            }
            que[cur] = dfs(e.b, adj);
            cnt++;
        }
        return que[cur];
    }
}
