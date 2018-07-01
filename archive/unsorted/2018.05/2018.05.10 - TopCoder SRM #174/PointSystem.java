package main;

//Sparsity of the event space
public class PointSystem {
    int pointsToWin, pointsToWinBy;

    public double oddsOfWinning(int pointsToWin, int pointsToWinBy, int skill) {
        this.pointsToWin = pointsToWin;
        this.pointsToWinBy = pointsToWinBy;
        double pWin = skill / 100.;
        int MAX = 1000;
        double[][] dp = new double[MAX][MAX];
        dp[0][0] = 1;
        double ans = 0;
        for (int turns = 1; turns < 2 * MAX; ++turns) {
            for (int undergo = 0; undergo < MAX; ++undergo) {
                int favorite = turns - undergo;
                if (0 <= favorite && favorite < MAX) {
                    double res = 0;
                    if (loss(undergo, favorite)) {
                        res = 0;
                    } else {
                        if (0 <= undergo - 1 && !won(undergo - 1, favorite))
                            res += dp[undergo - 1][favorite] * pWin;
                        if (0 <= favorite - 1 && !won(undergo, favorite - 1))
                            res += dp[undergo][favorite - 1] * (1 - pWin);
                        if (won(undergo, favorite)) {
                            ans += res;
                        }
                    }
                    dp[undergo][favorite] = res;
                }
            }
        }
        return ans;
    }

    private boolean won(int undergo, int favorite) {
        return pointsToWin <= undergo && pointsToWinBy <= undergo - favorite;
    }

    private boolean loss(int undergo, int favorite) {
        return pointsToWin <= favorite && pointsToWinBy <= favorite - undergo;
    }
}
