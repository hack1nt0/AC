package main;

public class SubsetSumExtreme {
    public double getExpectation(int[] block, int[] face) {
        int MAXF = 1000;
        double[] minFace = new double[MAXF + 1];

        int S = (1 << block.length) - 1;
        double[] dp = new double[S + 1];
        for (int s = 1; s <= S; ++s) {
            double res = 0;
            int blocks = 0;
            for (int j = 0; (1 << j) <= s; ++j) if ((s >> j & 1) != 0) blocks += block[j];
            for (int i = 0; i < face.length; ++i) minFace[face[i]] = blocks;

            for (int i = s; i > 0; i = s & (i - 1)) {
                //if ((i & s) != i) continue;
                int fc = 0;
                for (int j = 0; (1 << j) <= i; ++j) if ((i >> j & 1) != 0) fc += block[j];
                if (fc > MAXF) continue;
                minFace[fc] = Math.min(minFace[fc], dp[s ^ i]);
            }
            for (int i = 0; i < face.length; ++i) {
                res += minFace[face[i]];
            }
            res /= face.length;
            dp[s] = res;
            //System.out.println(res);
        }
        return dp[S];
    }
}
