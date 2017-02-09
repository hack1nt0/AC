package main;

import template.collection.sequence.ArrayUtils;

import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;
/*
 ID: hackint1
 LANG: JAVA
 TASK: stamps
*/
public class Stamps {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int k = in.nextInt();
        int n = in.nextInt();
        int[] stamps = new int[n];
        for (int i = 0; i < n; ++i) stamps[i] = in.nextInt();

        int MAX_POSTAGE = ArrayUtils.max(stamps) * k;
        System.err.println(MAX_POSTAGE);
        int[] minUsed = new int[MAX_POSTAGE + 1];
        int MAX_K = k + 1;
        Arrays.fill(minUsed, MAX_K);
        minUsed[0] = 0;
        ArrayUtils.mergeSort(stamps);
        int maxPostage = 0;
        for (int i = 0; i < n; ++i) {
            for (int postage = 1; postage <= MAX_POSTAGE; ++postage) {
                int res = minUsed[postage];
                if (postage >= stamps[i]) res = Math.min(res, minUsed[postage - stamps[i]] + 1);
                minUsed[postage] = res;
                if (i == n - 1) {
                    if (minUsed[postage] > k) break;
                    maxPostage = postage;
                }
            }
            //System.out.printlnConcisely(minUsed[postage][n]);
        }

        out.println(maxPostage);
    }
}
