package main;

import java.util.Arrays;

public class KiloManX {
    public int leastShots(String[] damageChart, int[] bossHealth) {
        int N = damageChart.length;
        int[] dp = new int[1 << N];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int set = 0; set < (1 << N) - 1; ++set) {
            int cur = dp[set];
            if (cur == Integer.MAX_VALUE)
                continue;
            if (set == 0) {
                for (int nb = 0; nb < N; ++nb) {
                    int shots = bossHealth[nb];
                    int nset = 1 << nb;
                    dp[nset] = Math.min(dp[nset], shots + cur);
                }
            } else {
                for (int nb = 0; nb < N; ++nb) {
                    if ((set >> nb & 1) == 0) {
                        int nset = set | (1 << nb);
                        for (int from = 0; from < N; ++from) {
                            if ((set >> from & 1) != 0) {
                                int damage = damageChart[from].charAt(nb) - '0';
                                if (damage != 0) {
                                    int shots = (bossHealth[nb] + damage - 1) / damage;
                                    dp[nset] = Math.min(dp[nset], shots + cur);
                                }
                            }
                        }
                    }
                }
            }
        }
        return dp[(1 << N) - 1];
    }
}
