package main;

import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;
/*
 ID: hackint1
 LANG: JAVA
 TASK: nocows
*/
public class Nocows {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        long[][] cnt1 = new long[n + 1][k + 1];
        long[][] acc1 = new long[n + 1][k + 1];
        cnt1[1][1] = 1;
        Arrays.fill(acc1[1], 1); acc1[1][0] = 0;
        //long[] cnt2 = new long[n + 1];
        //cnt2[1] = 1;
        long mod = 9901;
        for (int i = 2; i <= n; ++i) {
            for (int j = 2; j <= k; ++j) {
                //if (i > Math.pow(2, j) - 1) continue;
                long res = 0;
                for (int a = 1; i - a - 1 >= 1; ++a) res = (res + cnt1[a][j - 1] * acc1[i - a - 1][j - 1] % mod) % mod;
                res = res * 2 % mod;
                for (int a = 1; i - a - 1 >= 1; ++a) res = ((res - cnt1[a][j - 1] * cnt1[i - a - 1][j - 1] % mod) % mod + mod) % mod;
                cnt1[i][j] = res;
                acc1[i][j] = (acc1[i][j - 1] + cnt1[i][j]) % mod;
            }

            //for (int j = 1; i - j - 1 >= 1; ++j) cnt2[i] = (cnt2[i] + cnt2[j] * cnt2[i - j - 1] % mod) % mod;
        }

        out.println(cnt1[n][k]);
    }
}
