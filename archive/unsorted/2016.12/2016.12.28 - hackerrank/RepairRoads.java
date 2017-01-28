package main;

import java.util.*;
import java.io.PrintWriter;
import concurrency.Scheduler;
import concurrency.Task;
import template.graph_theory.Edge;
import template.graph_theory.Graph;

public class RepairRoads {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int N;
            Graph G;
            int[] crossRoot;
            int[] startWithRoot;
            int[] ret;
            int[] cn;
            @Override
            public void read(Scanner in) {
                N = in.nextInt();
                G = new Graph(N);
                for (int i = 0; i < N - 1; ++i) {
                    int a = in.nextInt();
                    int b = in.nextInt();
                    G.addEdge(new Graph.Edge(a, b, 1));
                    G.addEdge(new Graph.Edge(b, a, 1));
                }
                crossRoot = new int[N];
                startWithRoot = new int[N];
                ret = new int[N];
                cn = new int[N];
            }

            @Override
            public void solve() {
                dfs(0, -1);
            }

            private void dfs(int cur, int fa) {
                crossRoot[cur] = 0;
                startWithRoot[cur] = 0;
                cn[cur] = 1;
                int totChds = 0;
                List<Integer> chds = new ArrayList<>();
                int base = 0;
                for (Edge e : G.adj[cur]) {
                    int chd = e.getTo();
                    if (chd == fa) continue;
                    dfs(chd, cur);
                    cn[cur] += cn[chd];
                    totChds++;
                    if (cn[chd] == 1) {
                        continue;
                    }
                    chds.add(chd);
                    base += ret[cur];
                }

                if (chds.size() == 0) {
                    if (cn[cur] > 1)
                        ret[cur] = crossRoot[cur] = startWithRoot[cur] = 1;
                } else {
                    ret[cur] = base;

                    int[] delta = new int[chds.size()];
                    for (int i = 0; i < delta.length; ++i) delta[i] = -ret[chds.get(i)] + startWithRoot[chds.get(i)];
                    Arrays.sort(delta);
                    int i = 0;
                    int withoutLastTwo = 0;
                    while (true) {
                        if (i + 1 >= delta.length) break;

                        if (delta[i] + delta[i + 1] - 1 < 0) {
                            if (i + 2 == delta.length) withoutLastTwo = ret[cur];
                            ret[cur] += delta[i] + delta[i + 1] - 1;
                        } else break;

                        i += 2;
                    }

                    if (totChds > chds.size() || i < delta.length) {
                        startWithRoot[cur] = ret[cur];
                    } else {
                        startWithRoot[cur] = withoutLastTwo;
                    }

                    if (chds.size() % 2 == 0 || totChds > chds.size()) {
                        crossRoot[cur] = base - chds.size() / 2;
                    } else crossRoot[cur] = Integer.MAX_VALUE;
                }
            }

            @Override
            public void write(PrintWriter out, int testNumber) {
                //out.printlnTable("Case #" + testNumber + ":");
                out.println(ret[0]);
            }
        }, 4);
    }
}