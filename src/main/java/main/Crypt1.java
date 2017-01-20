package main;

import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: crypt1
*/

public class Crypt1 {
    boolean[] valid;
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        valid = new boolean[10];
        for (int i = 0; i < N; ++i) valid[in.nextInt()] = true;
        int ans = 0;
        for (int i = 1; i < 10; ++i) if (valid[i])
            for (int j = 1; j < 10; ++j) if (valid[j])
                for (int k = 1; k < 10; ++k) if (valid[k])
                    for (int l = 1; l < 10; ++l) if (valid[l])
                        for (int m = 1; m < 10; ++m) if (valid[m]) {
                            int a = i * 100 + j * 10 + k;
                            int b = l * 10 + m;
                            int partial1 = a * l;
                            if (!validate(partial1, 3)) continue;
                            int partial2 = a * m;
                            if (!validate(partial2, 3)) continue;
                            int mul = a * b;
                            if (!validate(mul, 4)) continue;
                            //Out.printlnV(a, b, partial1, partial2, mul);
                            ans++;
                        }

        out.println(ans);
    }

    private boolean validate(int num, int lenMust) {
        int len = 0;
        while (true) {
            if (num == 0) break;
            int lastd = num % 10;
            if (!valid[lastd]) return false;
            num /= 10;
            len++;
        }
        return len == lenMust;
    }

}
