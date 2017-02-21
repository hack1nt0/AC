package main;

import template.collection.sequence.ArrayUtils;
import template.debug.Stopwatch;

import java.math.BigInteger;
import java.util.*;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: buylow
*/

public class Buylow {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        if (n == 0) {
            out.println(0 + " " + 0);
            return;
        }
        int[] stock = new int[n];
        for (int i = 0; i < n; ++i) stock[i] = in.nextInt();

        int[] whichDay = new int[n];
        int[] maxLens = new int[n];
        Arrays.fill(whichDay, -1);
        whichDay[0] = 0;
        maxLens[0] = 1;
        int lenWhichDay = 1;
        Stopwatch.tic();
        for (int i = 1; i < n; ++i) {
            int l = 0, r = lenWhichDay;
            while (l + 1 < r) {
                int mid = l + (r - l) / 2;
                if (stock[whichDay[mid]] <= stock[i]) r = mid;
                else l = mid;
            }
            int p = stock[whichDay[l]] > stock[i] ? r : l;
            whichDay[p] = i;
            maxLens[i] = p + 1;
            lenWhichDay = Math.max(lenWhichDay, p + 1);
        }
        int maxLen = ArrayUtils.max(maxLens);
        out.print(maxLen + " "); out.flush();
        Stopwatch.toc();

        Stopwatch.tic();
        BigInteger[] unique = new BigInteger[n + 1];

        //for (int i = 0; i < unique.length; ++i) Arrays.fill(unique[i], -1);
        BigInteger uniqueSeqs = dfs(n, maxLen, maxLens, stock, unique);
        Stopwatch.toc();
        out.println(uniqueSeqs);
    }

    private BigInteger dfs(int to, int maxLen, int[] maxLens, int[] stock, BigInteger[] unique) {
        if (unique[to] != null) return unique[to];
        if (maxLen == 0) {
            //System.err.println(list);
            return BigInteger.ONE;
        }
        long next = Long.MAX_VALUE;
        BigInteger res = BigInteger.ZERO;
        for (int i = to - 1; i >= 0; --i) if (maxLens[i] == maxLen && stock[i] < next && (to == stock.length || stock[i] > stock[to])) {
            next = stock[i];
            //list.add(stock[i]);
            res = res.add(dfs(i, maxLen - 1, maxLens, stock, unique));
            //list.remove(list.size() - 1);
        }
        //System.err.println(to + " " + maxLen + " " + res);
        unique[to] = res;
        return res;
    }

    class IntArray {
        int[] arr;
        public IntArray(int[] arr) {
            this.arr = arr;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            IntArray intArray = (IntArray) o;

            return Arrays.equals(arr, intArray.arr);

        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(arr);
        }
    }
}
