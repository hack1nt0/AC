package template;

import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;

public class Candies {
    private static int[] rating;
    private static int[] candies;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        rating = new int[N];
        for (int i = 0; i < N; ++i) rating[i] = in.nextInt();
        candies = new int[N]; //Arrays.fill(candies, 1);
        boolean[] vis = new boolean[N];
//        while (true) {
//            boolean upd = false;
//            for (int i = 0; i < N; ++i) {
//                if (vis[i]) continue;
//                int res = 1;
//                boolean gl = false, gr = false;
//                if (0 < i && rating[i] > rating[i - 1]) {
//                    res = Math.max(res, candies[i - 1] + 1);
//                    gl = true;
//                }
//                if (i + 1 < N && rating[i] > rating[i + 1]) {
//                    res = Math.max(res, candies[i + 1] + 1);
//                    gr = true;
//                }
//                if (res > candies[i]) {
//
//                    candies[i] = Math.max(candies[i], res);
//                    upd = true;
//                    if (!(gl && gr)) vis[i] = true;
//                }
//                if (res < candies[i]) throw new RuntimeException();
//            }
//            if (!upd) break;
//        }

//        for (int i = 0; i < N; ++i) {
//            boolean ll = i == 0 || rating[i - 1] > rating[i];
//            boolean lr = i == N - 1 || rating[i] < rating[i + 1];
//            if (!(ll && lr)) continue;
//            for (int j = i - 1; j >= 0; --j) {
//                if (rating[j] < candies[j + 1]) break;
//                if (rating[j] > rating[j + 1])
//                    candies[j] = Math.max(candies[j], candies[j + 1] + 1);
//            }
//            for (int j = i + 1; j < N; ++j) {
//                if (rating[j] < candies[j - 1]) break;
//                if (rating[j] > rating[j - 1])
//                    candies[j] = Math.max(candies[j], candies[j - 1] + 1);
//            }
//        }

        Arrays.fill(candies, 1);
        for (int i = 0; i < N - 1;) {
            if (i + 1 < N && rating[i + 1] == rating[i]) {
                ++i;
                continue;
            }
            if (i + 1 < N && rating[i + 1] > rating[i]) {
                int j = i + 1;
                while (j < N && rating[j] > rating[j - 1]) ++j;
                for (int k = i + 1; k < j; ++k) candies[k] = Math.max(candies[k], candies[k - 1] + 1);
                i = j - 1;
            }
            if (i + 1 < N && rating[i + 1] < rating[i]) {
                int j = i + 1;
                while (j < N && rating[j] < rating[j - 1]) ++j;
                for (int k = j - 2; k >= i; --k) candies[k] = Math.max(candies[k], candies[k + 1] + 1);
                i = j - 1;
            }
        }

        long ans = 0;
        for (int i : candies) ans += i;
        out.println(ans);
    }

    public static void main(String[] args) {
        int N = (int)1e5;
        int[] rating = new int[N];
        for (int i = 0; i < N; ++i) rating[i] = (int)(Math.random() * N);
    }
}
