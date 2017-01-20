package main;

import template.collection.sequence.SuffixArray;

import java.util.Scanner;
import java.io.PrintWriter;

public class MilkPatterns {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int K = in.nextInt();
        Integer[] milk = new Integer[N];
        for (int i = 0; i < N; ++i) milk[i] = in.nextInt();
        SuffixArray<Integer> suffixArray = new SuffixArray<Integer>(milk);
        int ans = suffixArray.longestRepeatedSubstrings(K, null);
        out.println(ans);

    }
}
