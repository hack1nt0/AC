
import java.math.BigInteger;
import java.util.Scanner;
import java.io.PrintWriter;

public class FibonacciModified {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t1 = in.nextInt();
        int t2 = in.nextInt();
        int n = in.nextInt();
        BigInteger[] t = new BigInteger[n + 1];
        t[1] = BigInteger.valueOf(t1);
        t[2] = BigInteger.valueOf(t2);
        for (int i = 3; i <= n; ++i) t[i] = t[i - 2].add(t[i - 1].multiply(t[i - 1]));
        out.println(t[n]);
    }
}
