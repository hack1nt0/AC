package main;

import java.util.Arrays;

public class BadSubstring {
    public long howMany(int length) {
        if (length == 0)
            return 1L;
        long[][] dp = new long[length][3];
        Arrays.fill(dp[0], 1);
        for (int i = 1; i < length; ++i) {
            for (int c = 0; c < 3; ++c) {
                long ans = -1;
                if (c == 0 || c == 2)
                    ans = dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2];
                if (c == 1)
                    ans = dp[i - 1][1] + dp[i - 1][2];
                dp[i][c] = ans;
            }
        }
        return dp[length - 1][0] + dp[length - 1][1] + dp[length - 1][2];
    }
}
