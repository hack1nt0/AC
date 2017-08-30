package main;

import template.collection.sequence.ArrayUtils;
import template.combinatorics.CombinatoricsUtils;

import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;
/*
 ID: hackint1
 LANG: JAVA
 TASK: sort3
*/
public class Sort3 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) arr[i] = in.nextInt();
//        int p = 0, q = 0, r = n - 1;
//        int pivot = 2;
//        int ans = 0;
//        while (true) {
//            if (r < q) break;
//            if (arr[q] == pivot) q++;
//            else if (arr[q] < pivot) {
//                if (p < q) ans++;
//                ArrayUtils.swap(arr, p++, q++);
//            }
//            else if (arr[q] > pivot) {
//                while (q <= r && arr[r] > pivot) r--;
//                if (r < q) break;
//                if (p < r) ans++;
//                ArrayUtils.swap(arr, q, r--);
//            }
//        }
//        if (!ArrayUtils.sorted(arr, 0, arr.length)) {
//            throw new RuntimeException();
//        }
//        out.println(ans);
//
        int R = 3;
        int ans = 0;
        int[] cnt = new int[4];
        for (int i : arr) cnt[i]++;
        int[] from = new int[R + 1];
        int[] to = new int[R + 1];
        int[][] evil = new int[R + 1][R + 1];
        for (int i = 1; i <= R; ++i) {
            from[i] = to[i - 1];
            to[i]   = from[i] + cnt[i];
            for (int j = from[i]; j < to[i]; ++j)
                if (arr[j] != i) evil[i][arr[j]]++;
        }
        int one2Two = Math.min(evil[1][2], evil[2][1]);
        ans += one2Two;
        evil[1][2] -= one2Two;
        evil[2][1] -= one2Two;
        int one2Three = Math.min(evil[1][3], evil[3][1]);
        ans += one2Three;
        evil[1][3] -= one2Three;
        evil[3][1] -= one2Three;
        int two2Three = Math.min(evil[2][3], evil[3][2]);
        ans += two2Three;
        evil[2][3] -= two2Three;
        evil[3][2] -= two2Three;

        int s1 = ArrayUtils.sum(evil[1]);
        int s2 = ArrayUtils.sum(evil[2]);
        int s3 = ArrayUtils.sum(evil[3]);
        if (!(s1 == s2 && s2 == s3)) {
            throw new RuntimeException();
        }

        ans += s1 * 2;
        out.println(ans);
//        int ans = 0;
//        for (List<Integer> ring : CombUtils.rings(CombUtils.permutationForSorting(arr))) {
//            System.err.println(ring);
//            if (ring.size() == 1) continue;
//            ans += (ring.size() - 1);
//        }
//        out.println(ans);
    }

}
