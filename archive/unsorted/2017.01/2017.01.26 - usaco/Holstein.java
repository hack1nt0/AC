package main;

import template.collection.sets.BitUtils;

import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: holstein
*/
public class Holstein {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int vn = in.nextInt();
        int[] vitamin = new int[vn];
        for (int i = 0; i < vn; ++i) vitamin[i] = in.nextInt();
        int fn = in.nextInt();
        int[][] feed = new int[fn][vn];
        for (int i = 0; i < fn; ++i)
            for (int j = 0; j < vn; ++j) feed[i][j] = in.nextInt();

        List<Integer> ans = null;
//        int minSize = fn + 1;
//        for (int i = 0; i < 1 << fn; ++i) {
//            List<Integer> feedtypes = BitUtils.elements(i);
//            int[] curVitamin = new int[vn];
//            for (int f : feedtypes)
//                for (int j = 0; j < vn; ++j) curVitamin[j] += feed[f][j];
//            boolean ok = true;
//            for (int j = 0; j < vn; ++j) if (curVitamin[j] < vitamin[j]) {ok = false; break;}
//            if (ok && feedtypes.size() < minSize) {
//                ans = feedtypes;
//                minSize = feedtypes.size();
//            }
//        }

        for (int i = 0; i <= fn; ++i) {
            List<Long> feedSet = BitUtils.kSubset(fn, i);
            for (long set : feedSet) {
                List<Integer> feedtypes = BitUtils.elements(set);
                int[] curVitamin = new int[vn];
                for (int f : feedtypes)
                    for (int j = 0; j < vn; ++j) curVitamin[j] += feed[f][j];
                boolean ok = true;
                for (int j = 0; j < vn; ++j) if (curVitamin[j] < vitamin[j]) {ok = false; break;}
                if (ok) {
                    out.print(i);
                    for (int f : feedtypes) out.print(" " + (f + 1));
                    out.println();
                    return;
                }
            }
        }

        out.print(ans.size());
        for (int f : ans) out.print(" " + (f + 1));
        out.println();
    }
}
