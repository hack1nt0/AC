package main;

public class RepeatStringEasy {
    public int maximalLength(String s) {
        int ans = 0;
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < dp.length; ++i)
            dp[i][0] = dp[0][i] = 0;

        for (int sp = 1; sp < s.length(); ++sp) {
            int al = 0, ar = sp, ac = ar - al;
            int bl = sp, br = s.length(), bc = br - bl;
            //(i, j) = max((i - 1, j), (i, j - 1), (i - 1, j - 1))
            for (int i = 0; i < ac; ++i)
                for (int j = 0; j < bc; ++j) {
                    int res = 0;
                    res = Math.max(dp[i][j + 1], dp[i + 1][j]);
                    if (s.charAt(al + i) == s.charAt(bl + j))
                        res = Math.max(res, dp[i][j] + 1);
                    dp[i + 1][j + 1] = res;
                }
            ans = Math.max(ans, dp[ac][bc] * 2);
        }
        return ans;
    }
}
