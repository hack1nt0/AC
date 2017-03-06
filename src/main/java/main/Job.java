package main;

import template.collection.sequence.ArrayUtils;
import template.debug.Stopwatch;
import template.numbers.IntUtils;
import template.operation.MaxFlow;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: job
*/

public class Job {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int nJobs = in.nextInt();
        int nA = in.nextInt();
        int nB = in.nextInt();
        int[] tA = new int[nA];
        for (int i = 0; i < nA; ++i) tA[i] = in.nextInt();
        int[] tB = new int[nB];
        for (int i = 0; i < nB; ++i) tB[i] = in.nextInt();
//        int MAX_T_A = 1000;
//        int l = 0, r = MAX_T_A;
//        while (true) {
//            //System.err.println(l + " " + r);
//            if (l + 1 >= r) break;
//            int mid = l + (r - l) / 2;
//            int n = nJobs;
//            for (int i = 0; i < tA.length; ++i) {
//                for (int j = 1; j * tA[i] <= mid; ++j) {
//                    if (n == 0) break;
//                    n--;
//                }
//            }
//            if (n == 0) r = mid;
//            else l = mid;
//        }
//        int minTA = r;
//        out.print(minTA + " ");

//        int MAX_T_B = 500 + 2;
//        int l = 0, r = MAX_T_B;
//        while (true) {
//            System.err.println(l + " " + r);
//            if (l + 1 >= r) break;
//            int mid = l + (r - l) / 2;
//            int MAXN = mid * (tA.length + tB.length) + 2;
//            int source = MAXN - 2;
//            int sink = source + 1;
//            Stopwatch.tic();
//            MaxFlow maxFlow = new MaxFlow(MAXN);
//            for (int ia = 0; ia < tA.length; ++ia) {
//                for (int i = 1; i * tA[ia] <= mid;++i) {
//                    int nodeA = ia * mid + i - 1;
//                    maxFlow.addEdge(source, nodeA, 1);
//                    for (int ib = 0; ib < tB.length; ++ib) {
//                        for (int j = 1; j * tB[ib] + i * tA[ia] <= mid; ++j) {
//                            int nodeB = mid * tA.length + ib * mid + j - 1;
//                            maxFlow.addEdge(nodeA, nodeB, 1);
//                            maxFlow.addEdge(nodeB, sink, 1);
//                        }
//                    }
//                }
//            }
//            Stopwatch.toc();
//            Stopwatch.tic();
//            int flow = maxFlow.maxFlow(source, sink);
//            Stopwatch.toc();
//            System.err.printf("mid %d flow %d\n", mid, flow);
//            if (flow >= nJobs) r = mid;
//            else l = mid;
//        }
//        int minTB = r;
//        out.println(minTB + 1);
//        int[] minJob = new int[nJobs];
//        int jobIdx = 0;
//        for (int ai = 0; ai < tA.length; ++ai) {
//
//            for (int i = 1; i * tA[ai] <= minTA; ++i) {
//                minJob[jobIdx++] = i * tA[ai];
//                if (jobIdx == nJobs) break;
//            }
//            if (jobIdx == nJobs) break;
//        }

//        int lB = 0, rB = 1000 * 2;
//        while (true) {
//            if (lB + 1 >= rB) break;
//            int mid = lB + (rB - lB) / 2;
//            /*
//            boolean ok = true;
//            int[] scheduleB = new int[nB];
//            for (int ji = 0; ji < nJobs; ++ji) {
//                //boolean matched = false;
//                int bellmanford = Integer.MAX_VALUE;
//                int whichB = -1;
//                for (int bi = 0; bi < nB; ++bi) {
//                    int cost = Math.max(scheduleB[bi], minJob[ji]) + tB[bi];
//                    if (cost < bellmanford) {
//                        bellmanford = cost;
//                        whichB = bi;
//                    }
//                }
//                if (bellmanford <= mid) {
//                    //matched = true;
//                    scheduleB[whichB] = bellmanford;
//                } else {
//                    ok = false;
//                    break;
//                }
//            }
//            */
//            int[] scheduleB = new int[nB];
//            boolean ok = dfs(0, minJob, scheduleB, tB, mid);
//            if (ok) rB = mid;
//            else lB = mid;
//            //System.err.println(lB + " " + rB);
//        }
//        out.println(rB);

        int[] minJobCostA = getMinJobCosts(tA, nJobs);
        Arrays.sort(minJobCostA);
        int[] minJobCostB = getMinJobCosts(tB, nJobs);
        Arrays.sort(minJobCostB);
        int minCost = 0;
        for (int i = 0; i < nJobs; ++i) minCost = Math.max(minCost, minJobCostA[i] + minJobCostB[nJobs - i - 1]);
        out.println(ArrayUtils.max(minJobCostA) + " " + minCost);
    }

    private int[] getMinJobCosts(int[] tA, int nJobs) {
        int[] minJobCosts = new int[nJobs];
        int[] scheduleA = new int[tA.length];
        for (int ji = 0; ji < nJobs; ++ji) {
            //boolean matched = false;
            int minCost = Integer.MAX_VALUE;
            int whichA = -1;
            for (int ai = 0; ai < tA.length; ++ai) {
                int cost = scheduleA[ai] + tA[ai];
                if (cost < minCost) {
                    minCost = cost;
                    whichA = ai;
                }
            }
            minJobCosts[ji] = minCost;
            scheduleA[whichA] = minCost;
        }
        return minJobCosts;
    }

    private boolean dfs(int cur, int[] minJob, int[] scheduleB, int[] tB, int maxT) {
        if (cur >= minJob.length) return true;

        int leftJobs = minJob.length - cur;
        int maxJobs = 0;
        for (int i = 0; i < scheduleB.length; ++i) {
            if (scheduleB[i] >= maxT) continue;
            maxJobs += (maxT - scheduleB[i]) / tB[i];
        }
        if (maxJobs < leftJobs) return false;

//        int[] scheduleB2 = ArrayUtils.clone(scheduleB);
//        for (int ji = cur; ji < minJob.length; ++ji) {
//            //boolean matched = false;
//            int bellmanford = Integer.MAX_VALUE;
//            int whichB = -1;
//            for (int bi = 0; bi < scheduleB2.length; ++bi) {
//                //if (scheduleB2[bi] >= maxT) continue;
//                int cost = Math.max(scheduleB2[bi], minJob[ji]) + tB[bi];
//                if (cost < bellmanford) {
//                    bellmanford = cost;
//                    whichB = bi;
//                }
//            }
//            //if (bellmanford > maxT) return false;
//            //if (whichB == -1) return false;
//            scheduleB2[whichB] = bellmanford;
//        }
//        int max = ArrayUtils.max(scheduleB2);
//        if (max <= maxT) return true;
//        //if (max > maxT + 1) return false;

        for (int bi = 0; bi < scheduleB.length; ++bi) {
            int cost = Math.max(scheduleB[bi], minJob[cur]) + tB[bi];
            if (cost <= maxT) {
                int t = scheduleB[bi];
                scheduleB[bi] = cost;
                boolean ok = dfs(cur + 1, minJob, scheduleB, tB, maxT);
                if (ok) return true;
                scheduleB[bi] = t;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int njobs = 1000;
        int na = 30, nb = 30;
        ArrayUtils.printlnConcisely(njobs + " " + na + " " + nb);
        ArrayUtils.printlnConcisely(IntUtils.randomInts(na, 1, 20 + 1));
        ArrayUtils.printlnConcisely(IntUtils.randomInts(nb, 1, 20 + 1));
    }
}
