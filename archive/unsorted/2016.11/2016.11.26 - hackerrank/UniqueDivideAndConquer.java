package template;

import java.util.Scanner;
import java.io.PrintWriter;

//unsolved
public class UniqueDivideAndConquer {
    private static int MOD;
    private static long[] dp;
    private static long[] ret;
    private static long[][] C;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        MOD = in.nextInt();

        dp = new long[N + 1];
        C = new long[N][N];
        for (int i = 0; i < C.length; ++i) C[i][0] = 1;
        for (int i = 1; i < C.length; ++i)
            for (int j = 1; j < C[i].length; ++j) C[i][j] = (C[i - 1][j] + C[i - 1][j - 1]) % MOD;
//        fact = new BigInteger[N + 1]; fact[0] = BigInteger.ONE;
//        for (int i = 1; i < fact.length; ++i) fact[i] = fact[i - 1].multiply(BigInteger.valueOf(i));

        ret = new long[N + 1];
        ret[1] = 1;
        for (int k = 2; k <= N; ++k) {
            int maxp = (k - 1) / 2;
            //dp(i) = Sum(1<=j<=maxp) C(i - 1, j - 1) * ret(j) * j * dp(i - j); dp(0) = 1
            dp[0] = 1;
            for (int i = 1; i < k; ++i) {
                long res = 0;
                for (int j = 1; j <= Math.min(i, maxp); ++j)
                    res = (res + C[i - 1][j - 1] * ret[j] % MOD * j % MOD * dp[i - j] % MOD) % MOD;
                dp[i] = res;
            }
            ret[k] = dp[k - 1] * k % MOD;
            //System.out.println(k + ": " + ret[k]);

        }
        //out.println(Arrays.toString(ret));
        out.printf("%d\n", ret[N]);
        out.close();
    }

//    private static long dfs(int i, int j) {
//        if (dp[i][j] != -1) return dp[i][j];
//        long res = 0;
//        if (i == 0 && j >= 0) res = 1;
//        else if (i > 0 && j <= 0) res = 0;
//        else {
//            if (j >= 1) res = (res + dfs(i, j - 1)) % MOD;
//            for (int k = 1; i >= k * j; ++k) {
//                //if (k + 1 > k * j) throw new RuntimeException(k + 1 + ", " + k * j);
//                long partitions = fact[k * j].divide(fact[k].multiply(fact[j - 1].pow(k))).mod(mBig).longValue();
//                //System.out.println(k + ", " + j + ", " + partitions);
//                long chdRes = BigInteger.valueOf(ret[j]).pow(k).mod(mBig).longValue();
//                res = (res + dfs(i - k * j, j - 1) * chdRes % MOD * C[i][k * j] % MOD * partitions % MOD) % MOD;
//                //res = (res + dfs(i - k * j, j - 1) * chdRes % MOD ) % MOD;
//            }
//        }
//
//        dp[i][j] = res;
//        return res;
//    }

}
