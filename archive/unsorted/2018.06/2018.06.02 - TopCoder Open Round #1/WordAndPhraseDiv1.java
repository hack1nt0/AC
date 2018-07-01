package main;

import java.util.ArrayList;
import java.util.List;

public class WordAndPhraseDiv1 {
    public int findNumberOfPhrases(String w) {
        char[] warray = w.toCharArray();
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < warray.length; ++i)
            if (warray[i] == '_' && i != 0 && i != warray.length - 1 && !Character.isDigit(warray[i + 1]))
                indexes.add(i);
        int n = indexes.size();
        long[][] dp = new long[n + 1][2];
        dp[0][0] = 1;
        int mod = (int)1e9 + 7;
        for (int i = 0; i < n; ++i) {
            dp[i + 1][0] = (dp[i][0] + dp[i][1]) % mod;
            dp[i + 1][1] = dp[i][0];
            if (i > 0 && indexes.get(i - 1) + 1 != indexes.get(i))
                dp[i + 1][1] = (dp[i + 1][1] + dp[i][1]) % mod;
        }
        return (int)((dp[n][0] + dp[n][1]) % mod);
    }
}
