package main;

import template.debug.InputReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;

/*
 ID: hackint1
 LANG: JAVA
 TASK: vans
*/

/**
 * TEDIOUS!
 */

public class Vans {
    int r = 4;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.readInt();
        if (n == 1) {
            out.println(0);
            return;
        }
        if (n == 2) {
            out.println(2);
            return;
        }
        long[][] count = new long[n][1 << 3 * 4];
        count[n - 1][0] = 1;

        BigInteger[][] bigCount = new BigInteger[2][1 << 3 * 4];
        Arrays.fill(bigCount[(n - 1) % 2], BigInteger.ZERO);
        bigCount[(n - 1) % 2][0] = BigInteger.ONE;
        for (int i = n - 2; i >= 0; --i) {
            int cur = i % 2;
            int nxt = (i + 1) % 2;
            for (int s = 0; s < 1 << 3 * 4; ++s) {
//                long res = 0;
//                int existed = 0;
//                int[] cc = new int[4];
//                for (int b = 0; b < 4; ++b) {
//                    int whichcc = s >> (b * 2) & 3;
//                    if (whichcc != 0) existed |= 1 << b;
//                    cc[b] = whichcc;
//                }
                //if (cc[0] == cc[1] && cc[1] == cc[2] && cc[2] == cc[3]) continue;

//                if (s == 0_0011) res += count[i + 1][0_1001] + count[i + 1][0_2211];
//                if (s == 0_0101) res += count[i + 1][0_1010];
//                if (s == 0_0110) res += count[i + 1][0_1001];
//                if (s == 0_1001) res += count[i + 1][0_0011] + count[i + 1][0_0110] + count[i + 1][0_1100] + count[i + 1][0_1221] + count[i + 1][0_0000];
//                if (s == 0_1010) res += count[i + 1][0_0101];
//                if (s == 0_1100) res += count[i + 1][0_1122] + count[i + 1][0_1001];
//                if (s == 0_2211) res += count[i + 1][0_1001] + count[i + 1][0_2211];
//                if (s == 0_1221) res += count[i + 1][0_1221] + count[i + 1][0_0000] + count[i + 1][0_0011] + count[i + 1][0_1100];
//
//                count[i][s] = res;

                BigInteger bigRes = BigInteger.ZERO;
                if (s == 0_0011) bigRes = bigRes.add(bigCount[nxt][0_1001]).add(bigCount[nxt][0_2211]);
                if (s == 0_0101) bigRes = bigRes.add(bigCount[nxt][0_1010]);
                if (s == 0_0110) bigRes = bigRes.add(bigCount[nxt][0_1001]);
                if (s == 0_1001) bigRes = bigRes.add(bigCount[nxt][0_0011]).add(bigCount[nxt][0_0110]).add(bigCount[nxt][0_1100]).add(bigCount[nxt][0_1221]).add(bigCount[nxt][0_0000]);
                if (s == 0_1010) bigRes = bigRes.add(bigCount[nxt][0_0101]);
                if (s == 0_1100) bigRes = bigRes.add(bigCount[nxt][0_2211]).add(bigCount[nxt][0_1001]);
                if (s == 0_2211) bigRes = bigRes.add(bigCount[nxt][0_1001]).add(bigCount[nxt][0_2211]);
                if (s == 0_1221) bigRes = bigRes.add(bigCount[nxt][0_1221]).add(bigCount[nxt][0_0000]).add(bigCount[nxt][0_0011]).add(bigCount[nxt][0_1100]);
                //if (s == 0_1122) bigRes = bigRes.add(bigCount[nxt][0_1001]).add(bigCount[nxt][0_1122]);

                bigCount[cur][s] = bigRes;

            }
        }

        //long res = count[0][0_2211] + count[0][0_1001];
//        out.println(res * 2);
        BigInteger res = bigCount[0][0_2211].add(bigCount[0][0_1001]);
        res = res.add(res);
        out.println(res);
    }
}
