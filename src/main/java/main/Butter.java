package main;

import template.collection.sequence.ArrayUtils;
import template.operation.ShortestPath;

import java.util.Scanner;
import java.io.PrintWriter;
/*
 ID: hackint1
 LANG: JAVA
 TASK: butter
*/
public class Butter {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int ncow = in.nextInt();
        int npasture = in.nextInt();
        int ne = in.nextInt();
        int[] pastureCows = new int[npasture];
        for (int i = 0; i < ncow; ++i) {
            int p = in.nextInt() - 1;
            pastureCows[p]++;
        }
        ShortestPath shortestPath = new ShortestPath(npasture);
        for (int e = 0; e < ne; ++e) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int cost = in.nextInt();
            shortestPath.addE(from, to, cost);
            shortestPath.addE(to, from, cost);
        }
        long ans = Integer.MAX_VALUE;
        long[] minTot = new long[npasture];
        for (int butter = 0; butter < npasture; ++butter) {
            if (pastureCows[butter] == 0) continue;
            long[] minDist = shortestPath.bellmanford(butter);
            for (int i = 0; i < npasture; ++i) {
                if (minTot[i] == shortestPath.INF) continue;
                if (minDist[i] == shortestPath.INF) {
                    minTot[i] = shortestPath.INF; continue;
                }
                minTot[i] += minDist[i] * pastureCows[butter];
            }
//            long totDist = 0;
//            for (int pasture = 0; pasture < npasture; ++pasture) {
//                if (minDist[pasture] == shortestPath.INF && pastureCows[pasture] != 0) {
//                    totDist = shortestPath.INF;
//                    break;
//                }
//                totDist += minDist[pasture] * pastureCows[pasture];
//            }
//            ans = Math.min(ans, totDist);
        }

        ans = ArrayUtils.min(minTot);
        out.println(ans);
    }
}
