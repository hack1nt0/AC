package main;

import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;
/*
 ID: hackint1
 LANG: JAVA
 TASK: kimbits
*/
public class Kimbits {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int bitN = in.nextInt();
        int oneN = in.nextInt();
        long ith = in.nextLong();
        long[][] cnt = new long[bitN + 1][oneN + 1];
        Arrays.fill(cnt[0], 1);
        for (int i = 0; i < bitN; ++i) {
            cnt[i + 1][0] = 1;
            for (int j = 1; j <= oneN; ++j) {
                cnt[i + 1][j] = cnt[i][j] + cnt[i][j - 1];
            }
        }


        StringBuilder ans = new StringBuilder();
        int leftOneN = oneN;
        int leftBitN = bitN;
        long margin = ith;
        for (int b = bitN - 1; b >= 0; --b) {
            leftBitN--;
//            if (leftBitN < 0 || leftOneN < 0) {
//                throw new RuntimeException();
//            }
            if (margin > 0 && leftOneN > 0 && cnt[leftBitN][leftOneN] < margin) {
                ans.append(1);
                margin -= cnt[leftBitN][leftOneN];
                leftOneN--;
            } else {
                ans.append(0);
            }
        }
        out.println(ans);
    }
}
