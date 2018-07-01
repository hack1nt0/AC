package main;

public class ThreeSameLetters {
    public int countStrings(int L, int S) {
        if (L <= 2)
            return 0;
        long[][][][] dp = new long[L + 1][2][S][S];
        for (int first = 0; first < S; ++first) {
            for (int second = 0; second < S; ++second) {
                dp[2][0][first][second] = 1;
            }
        }
        long MOD = (long)(1e9 + 7);
        for (int i = 2; i < L; ++i) {
            for (int nb = 0; nb <= 1; ++nb) {
                for (int first = 0; first < S; ++first) {
                    for (int second = 0; second < S; ++second) {
                        for (int third = 0; third < S; ++third) {
                            boolean oneBlock = third == first && first == second;
                            if (oneBlock && nb == 1)
                                continue;
                            int nnb = oneBlock ? nb + 1 : nb;
                            long ans = dp[i + 1][nnb][second][third];
                            ans = (ans + dp[i][nb][first][second]) % MOD;
                            dp[i + 1][nnb][second][third] = ans;
                        }
                    }
                }
            }
        }
        long ans = 0L;
        for (int first = 0; first < S; ++first)
            for (int second = 0; second < S; ++second)
                ans = (ans + dp[L][1][first][second]) % MOD;
        return (int)ans;
    }
}
