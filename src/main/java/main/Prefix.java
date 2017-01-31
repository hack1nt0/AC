package main;

import scala.concurrent.java8.FuturesConvertersImpl;
import template.string.StringUtils;
import template.string.TrieSet;

import java.util.Scanner;
import java.io.PrintWriter;
/*
 ID: hackint1
 LANG: JAVA
 TASK: prefix
*/
public class Prefix {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        TrieSet primitives = new TrieSet();
        while (true) {
            String p = in.next();
            if (p.equals(".")) break;
            primitives.add(StringUtils.reverse(p));
        }
        StringBuilder tmp = new StringBuilder();
        while (true) {
            if (!in.hasNext()) break;
            tmp.append(in.next());
        }
        String seq = tmp.toString();
        int[] prefixLen = new int[seq.length() + 1];
        int maxPrefixLen = 0;

        for (int i = 0; i < seq.length(); ++i) {
            for (int j = i; j >= 0; --j) {
                int signal = primitives.accept(seq.charAt(j));
                if (signal == -1) break;
                if (signal == +1) prefixLen[i + 1] = Math.max(prefixLen[i + 1], i - j + 1 + prefixLen[j]);
            }
            primitives.backToRoot();
            if (prefixLen[i + 1] == i + 1) maxPrefixLen = i + 1;
        }

        out.println(maxPrefixLen);

    }
}
