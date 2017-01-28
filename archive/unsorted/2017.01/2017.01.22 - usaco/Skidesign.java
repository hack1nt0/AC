package main;

import template.collection.sequence.ArrayUtils;

import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: skidesign
*/

/**
 * sum of square of values, not sum of values, so accumulation(or scanning window) not proverty.
 */
public class Skidesign {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] hill = new int[n];
        for (int i = 0; i < n; ++i) hill[i] = in.nextInt();
        ArrayUtils.mergeSort(hill);
        long ans = 100L * 100 * 1000;
//        for (int lowestId = 0; lowestId < n; ++lowestId) {
//            long min1 = 0;
//            for (int i = 0; i < lowestId; ++i) {
//                long diff = hill[lowestId] - hill[i];
//                min1 += diff * diff;
//            }
//            for (int i = lowestId + 1; i < n; ++i) {
//                long diff = hill[i] - hill[lowestId];
//                if (diff <= 17) continue;
//                diff -= 17;
//                min1 += diff * diff;
//            }
//            ans = Math.min(ans, min1);
//
//            int lowest = hill[lowestId] - 17;
//            if (lowest < 0) continue;
//            long min2 = 0;
//            for (int i = 0; i < n; ++i) {
//                long diff = Math.abs(hill[i] - lowest);
//                if (hill[i] < lowest) {
//                    min2 += diff * diff;
//                    continue;
//                }
//                if (diff <= 17) continue;
//                diff -= 17;
//                min2 += diff * diff;
//            }
//            ans = Math.min(ans, min2);
//        }

        for (int lowest = 0; lowest <= 100; ++lowest) {
            long min = 0;
            for (int i = 0; i < n; ++i) {
                long diff = Math.abs(hill[i] - lowest);
                if (hill[i] < lowest) min += diff * diff;
                if (hill[i] > lowest && diff > 17) {
                    min += (diff - 17) * (diff - 17);
                }
            }
            ans = Math.min(ans, min);
        }
        out.println(ans);
    }
}
