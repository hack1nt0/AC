package template;

import java.util.Scanner;
import java.io.PrintWriter;

public class JourneytotheMoon {
    public void solve(int testNumber, Scanner in, PrintWriter out) {

        int N = in.nextInt();
        int I = in.nextInt();
        UnionFind uf = new UnionFind(N);
        for (int i = 0; i < I; ++i) {
            int a = in.nextInt();
            int b = in.nextInt();
            uf.union(a, b);
        }
        int[] gcnt = new int[N];
        for (int i = 0; i < N; ++i) {
            gcnt[uf.find(i)]++;
        }
        long ret = 0;
        for (int i = 0, acc = 0; i < gcnt.length; ++i) {
            ret += gcnt[i] * acc;
            acc += gcnt[i];
        }
        out.println(ret);
    }

}
