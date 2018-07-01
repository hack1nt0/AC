package main;

import template.debug.InputReader;
import template.numbers.IntUtils;
import template.string.IntTrieSet;

import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: prime3
*/
//timeout... a tedious search prob

public class Prime3 {
    int sumLimit;
    int digitRadix = 9 + 1;
    int len = 5;
    int[][] rstr = new int[len][len];
    int[][] cstr = new int[len][len];
    int[] diag2str = new int[len];
    int[] diag1str = new int[len];
    int[] rsum = new int[len];
    int[] csum = new int[len];
    int diag2sum, diag1sum;
    boolean found;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        sumLimit = in.readInt();
        IntTrieSet trieSet = new IntTrieSet(10);
        for (int prime : IntUtils.primes(100000)) {
            int[] xs = IntUtils.toArray(prime);
            if (xs.length == len) trieSet.add(xs);
        }
        int tl = in.readInt();
        rstr[0][0] = tl;
        cstr[0][0] = tl;
        diag2str[0] = tl;
        rsum[0] = tl;
        csum[0] = tl;
        diag2sum = tl;
        dfs(0, 1, trieSet, out);

        if (!found) out.println("NONE");
    }

    private void dfs(int r, int c, IntTrieSet trieSet, PrintWriter out) {
        if (c >= len) {
            dfs(r + 1, 0, trieSet, out);
            return;
        }
        if (r >= len) {
            found = true;
            System.err.println('d');
            for (int i = 0; i < len; ++i) {
                for (int j = 0; j < len; ++j) out.print(rstr[i][j]);
                out.println();
            }
            out.println();
            return;
        }
        for (int d = 0; d < digitRadix; ++d) {
            rstr[r][c] = d;
            cstr[c][r] = d;
            rsum[r] += d;
            csum[c] += d;
            if (r == c) {
                diag2str[r] = d;
                diag2sum += d;
            }
            if (r + c == len - 1) {
                diag1str[c] = d;
                diag1sum += d;
            }
            if (valid(d, r, c, trieSet)) dfs(r, c + 1, trieSet, out);
            rsum[r] -= d;
            csum[c] -= d;
            if (r == c) diag2sum -= d;
            if (r + c == len - 1) diag1sum -= d;
        }
    }

    private boolean valid(int d, int r, int c, IntTrieSet trieSet) {
        if ((r == 0 || c == 0) && d == 0) return false;
        if (r == len - 1) {
            if (csum[c] !=  sumLimit) return false;
            if (c == len - 1) {
                if (diag2sum != sumLimit) return false;
                if (rsum[r] != sumLimit) return false;
                if (!trieSet.contains(diag2str)) return false;
            }
            if (c == 0) {
                if (diag1sum != sumLimit) return false;
                if (!trieSet.contains(diag1str)) return false;
            }
        } else if (c == len - 1) {
            if (rsum[r] != sumLimit) return false;
        } else {
            boolean rok = rsum[r] < sumLimit && rsum[r] + (len - c - 1) * (digitRadix - 1) >= sumLimit;
            if (!rok) return false;
            boolean cok = csum[c] < sumLimit && csum[c] + (len - r - 1) * (digitRadix - 1) >= sumLimit;
            if (!cok) return false;
            if (r == c) {
                boolean diag2ok = diag2sum < sumLimit && diag2sum + (len - r - 1) * (digitRadix - 1) >= sumLimit;
                if (!diag2ok) return false;
                if (!trieSet.containsPrefix(diag2str, 0, r + 1)) return false;
            }
            if (r + c == len - 1) {
                boolean diag1ok = diag1sum < sumLimit && diag1sum + (len - r - 1) * (digitRadix - 1) >= sumLimit;
                if (!diag1ok) return false;
            }
        }
        if (!trieSet.containsPrefix(rstr[r], 0, c + 1)) return false;
        if (!trieSet.containsPrefix(cstr[c], 0, r + 1)) return false;
        return true;
    }
}
