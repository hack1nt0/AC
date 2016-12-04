package main;

public class RepeatString {
    public int minimalModify(String s) {
        int N = s.length();
        int[][] dp = new int[N + 1][N + 1];
        for (int i = 0; i < dp.length; ++i)
            dp[i][0] = dp[0][i] = i;

        int ans = N;
        for (int sp = 1; sp < s.length(); ++sp) {
            int al = 0, bl = sp;
            int ac = sp, bc = N - sp;
            for (int i = 0; i < ac; ++i)
                for (int j = 0; j < bc; ++j) {
                    int res = N;
                    res = Math.min(res, dp[i][j + 1] + 1);
                    res = Math.min(res, dp[i + 1][j] + 1);
                    res = Math.min(res, dp[i][j] + 1);
                    if (s.charAt(al + i) == s.charAt(bl + j))
                        res = Math.min(res, dp[i][j]);
                    dp[i + 1][j + 1] = res;
                }
            ans = Math.min(ans, dp[ac][bc]);
        }
        return ans;
    }
}
