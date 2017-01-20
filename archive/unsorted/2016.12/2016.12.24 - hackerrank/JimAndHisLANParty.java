package main;



import template.UnionFind;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;
import java.io.PrintWriter;

/**
 * An union-find approach with some tweaking can easily done.
 */
public class JimAndHisLANParty {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int M = in.nextInt();
        int Q = in.nextInt();
        int[] cgame = new int[M];
        int[] play = new int[N];
        for (int i = 0; i < N; ++i) {
            int game = in.nextInt() - 1;
            play[i] = game;
            cgame[game]++;
        }
        int[] when = new int[M];
        Arrays.fill(when, -1);
        Map<Integer, Integer>[] comp = new HashMap[N];
        for (int i = 0; i < N; ++i) {
            comp[i] = new HashMap<Integer, Integer>();
            comp[i].put(play[i], 1);
            if (cgame[play[i]] == 1) when[play[i]] = 0;
        }
        UnionFind uf = new UnionFind(N);
        for (int e = 0; e < Q; ++e) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            if (uf.connected(a, b)) continue;
            int pa = uf.find(a);
            int pb = uf.find(b);
            if (comp[pa].size() > comp[pb].size()) {int t = pa; pa = pb; pb = t;}

            for (int g : comp[pa].keySet()) {
                if (when[g] != -1) continue;
                if (comp[pb].containsKey(g)) {
                    int nc = comp[pa].get(g) + comp[pb].get(g);
                    if (nc == cgame[g]) {
                        when[g] = e + 1;
                        continue;
                    }
                    comp[pb].put(g, nc);
                } else comp[pb].put(g, comp[pa].get(g));
            }

            uf.union(a, b);
            pa = uf.find(a);
            comp[pa] = comp[pb];
        }

        for (int i = 0; i < when.length; ++i) out.println(when[i]);
    }

    public static void main(String[] args) throws FileNotFoundException {
        int MAX = (int)1e5;
        PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("Jim.in")));
        int N = MAX;
        int M = MAX / 2;
        int Q = MAX;
        out.println(N + " " + M + " " + Q);
        int[] cg = new int[M];
        Arrays.fill(cg, 1);
        int left = N - M;
        for (int i = 0; i < M; ) {
            if (left <= 0) break;
            if (Math.random() < 0.31) {
                cg[i]++;
                left--;
            }
            else i++;
        }
        if (left > 0) cg[(int)(Math.random() * (M - 1))] += left;
        int[] play = new int[N];
        List<Integer> player = new ArrayList<Integer>();
        for (int i = 0; i < N; ++i) player.add(i);
        Collections.shuffle(player);
        int curG = 0;
        for (int p : player) {
            if (cg[curG] == 0) curG++;
            play[p] = curG;
            cg[curG]--;
        }
        for (int i = 0; i < N; ++i) out.print((play[i] + 1) + " ");

        for (int i = 0; i < Q; ) {
            int a = (int)(Math.random() * N);
            if (a >= N) continue;
            int b = (int)(Math.random() * N);
            if (b >= N) continue;
            if (a == 0 || b == 0) {
                System.err.println(a);
            }
            a++; b++;
            out.println(a + " " + b);
            ++i;
        }
        out.close();

    }
}
