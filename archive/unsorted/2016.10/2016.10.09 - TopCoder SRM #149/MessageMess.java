package main;

public class MessageMess {
    public String restore(String[] dictionary, String message) {
        String MORETHANONE = "AMBIGUOUS!";
        String NONE = "IMPOSSIBLE!";
        int N = message.length();
        long[] dp = new long[N + 1];
        dp[N] = 1;

        for (int L = N - 1; L >= 0; --L) {
            long res = 0;
            String s = message.substring(L);
            for (int i = 0; i < dictionary.length; ++i) {
                if (!s.startsWith(dictionary[i])) continue;
                res += dp[L + dictionary[i].length()];
            }
            dp[L] = res;
        }
        if (dp[0] == 0) return NONE;
        if (dp[0] > 1) return MORETHANONE;

        String ans = "";
        for (int L = 0; L < N;) {
            String s = message.substring(L);
            for (int i = 0; i < dictionary.length; ++i) {
                if (!s.startsWith(dictionary[i])) continue;
                if (dp[L] == dp[L + dictionary[i].length()]) {
                    if (ans.length() > 0) ans += " ";
                    ans += dictionary[i];
                    L += dictionary[i].length();
                    break;
                }
            }
        }

        return ans;
    }
}
