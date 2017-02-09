package main;

import java.util.Scanner;
import java.io.PrintWriter;
/*
 ID: hackint1
 LANG: JAVA
 TASK: ratios
*/
public class Ratios {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int MAX_UNITS = 100;
        int compN = 3;
        int[] target = new int[compN];
        int[] barley = new int[compN];
        int[] oats = new int[compN];
        int[] wheat = new int[compN];
        for (int i = 0; i < compN; ++i) target[i] = in.nextInt();
        for (int i = 0; i < compN; ++i) barley[i] = in.nextInt();
        for (int i = 0; i < compN; ++i) oats[i] = in.nextInt();
        for (int i = 0; i < compN; ++i) wheat[i] = in.nextInt();

        for (int sum = 0; sum <= MAX_UNITS * 3; ++sum) {
            for (int b = 0; b < MAX_UNITS && b <= sum; ++b) {
                for (int o = 0; o < MAX_UNITS && b + o <= sum; ++o) {
                    int w = sum - b - o;

                    int[] mix = new int[compN];
                    for (int i = 0; i < compN; ++i) mix[i] = barley[i] * b + oats[i] * o + wheat[i] * w;
                    boolean ok = true;
                    int multiple = 0;
                    for (int i = 0; i < compN; ++i) {
                        if (mix[i] == 0 && target[i] == 0) continue;
                        if (mix[i] == 0 || target[i] == 0) {ok = false; break;}
                        if (mix[i] % target[i] != 0) {
                            ok = false;
                            break;
                        }
                        mix[i] /= target[i];
                        if (multiple != 0 && mix[i] != multiple) {
                            ok = false;
                            break;
                        }
                        multiple = mix[i];
                    }
                    if (ok) {
                        out.println(b + " " + o + " " + w + " " + mix[0]);
                        return;
                    }
                }
            }
        }

        out.println("NONE");
    }
}
