package main;

import template.collection.sequence.ArrayUtils;
import template.debug.InputReader;
import template.numbers.IntUtils;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 ID: hackint1
 LANG: JAVA
 TASK: milk4
*/

public class Milk4 {
    int minPails = Integer.MAX_VALUE;
    List<Integer> pailsRequired;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int quarts = in.readInt();
        int nPail = in.readInt();
        int[] pails1 = new int[nPail];
        for (int i = 0; i < nPail; ++i) pails1[i] = in.readInt();
        Arrays.sort(pails1);
        //pails1 = new int[]{1025, 1033};
        int[] pails = ArrayUtils.unique(pails1);

        dfs(0, quarts, pails, new ArrayList<Integer>());
        out.print(minPails + " ");
        ArrayUtils.printlnConcisely(pailsRequired, out, 100);
    }

    private void dfs(int cur, int quarts, int[] pails, ArrayList<Integer> acc) {
//        if (acc.size() == 1 && acc.contains(1025) && cur < pails.length && pails[cur] == 1033 && quarts % 1033 == 0) {
//            System.out.println(quarts + " " + quarts % 1033);
//        }
        if (quarts == 0) {
            //System.out.println(acc);
            if (acc.size() < minPails || acc.size() == minPails && ArrayUtils.compare(acc, pailsRequired) < 0) {
                minPails = acc.size();
                pailsRequired = (List<Integer>) acc.clone();
                //ArrayUtils.printlnConcisely(pailsRequired);
            }
            return;
        }
        if (cur == pails.length) return;
        int lb = acc.size() + 1;
        if (minPails < lb) return;

//        long d = pails[cur];
//        for (int i = cur + 1; i < pails.length; ++i) d = IntUtils.gcd(d, pails[i]);
//        if (d != quarts) return;

        if (quarts % pails[cur] == 0) {
            acc.add(pails[cur]);
            dfs(cur + 1, 0, pails, acc);
            acc.remove(acc.size() - 1);
            return;
        }

        for (int i = 1; i * pails[cur] < quarts; ++i) {
            acc.add(pails[cur]);
            dfs(cur + 1, quarts - i * pails[cur], pails, acc);
            acc.remove(acc.size() - 1);
        }

        dfs(cur + 1, quarts, pails, acc);
    }
}
