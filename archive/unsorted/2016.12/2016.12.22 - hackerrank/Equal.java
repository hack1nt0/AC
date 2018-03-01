package main;



import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;

/**
 * Equality: adding t equal = minus t equal; All equals = diff of all elements is zero.
 *
 * WA: dont got it.
 */
public class Equal {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int[] cho = new int[N];
        for (int i = 0; i < N; ++i) cho[i] = in.nextInt();
        Arrays.sort(cho);
        long pre = cho[0];
        long ret = 0;
        long acc = 0;
        for (int i = 0; i < N;) {
            while (i < N && cho[i] + acc == pre) i++;
            if (i >= N) break;
            long cur = cho[i] + acc;
            long diff = cur - pre;
            ret += diff / 5 + diff % 5 / 2 + diff % 5 % 2;

            pre = cur;
            acc += diff;
            i++;
        }
        out.println(ret);
    }
}
