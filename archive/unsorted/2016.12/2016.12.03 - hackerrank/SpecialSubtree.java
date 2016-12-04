package template;

import java.util.Scanner;
import java.io.PrintWriter;

public class SpecialSubtree {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int M = in.nextInt();
        MinSpanningTree mst = new MinSpanningTree(N);
        for (int i = 0; i < M; ++i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int c = in.nextInt();
            mst.addE(a, b, c);
            mst.addE(b, a, c);
        }
        //int S = in.nextInt();
        long ret = mst.cost();
        out.println(ret);
    }
}
