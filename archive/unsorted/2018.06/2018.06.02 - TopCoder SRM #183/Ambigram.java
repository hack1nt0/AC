package main;

public class Ambigram {
    public String ambiword(String word) {
        State oo = new State(Integer.MAX_VALUE, null);
        State ans = oo;
        String MW = "MW";
        String HINOSXZ = "HINOSXZ";
        for (int split = 0; split < word.length() * 2 - 1; ++split) {
            int n = split % 2 == 0 ? split / 2 : (split - 1) / 2 + 1;
            int m = word.length() - n - (split % 2 == 0 ? 1 : 0);
            State[][][] dp = new State[n + 1][m + 1][2];
            if (split % 2 == 0) {
                char c = word.charAt(split / 2);
                if (HINOSXZ.indexOf(c) != -1) {
                    dp[0][0][0] = dp[0][0][1] = new State(0, "" + c);
                } else {
                    int cost = Integer.MAX_VALUE;
                    String symbol = "";
                    for (char t : HINOSXZ.toCharArray())
                        if (change(c, t) < cost) {
                            cost = change(c, t);
                            symbol = "" + t;
                        }
                    dp[0][0][1] = new State(cost, symbol);
                    if (remove(c) < cost) {
                        cost = remove(c);
                        symbol = "";
                    }
                    dp[0][0][0] = new State(cost, symbol);
                }
            } else {
                dp[0][0][0] = new State(0, "");
                dp[0][0][1] = oo;
            }
            for (int i = 0; i <= n; ++i) {
                for (int j = 0; j <= m; ++j) {
                    if (i == 0 && j == 0)
                        continue;
                    State[] res = {oo, oo};
                    int oi = i - 1, oj = j - 1;
                    char ci = oi >= 0 ? word.charAt(split % 2 == 0 ? split / 2 - 1 - oi : (split - 1) / 2 - oi) : 0;
                    char cj = oj >= 0 ? word.charAt(split % 2 == 0 ? split / 2 + 1 + oj : (split + 1) / 2 + oj) : 0;
                    if (i > 0) {
                        res[0] = min(res[0], new State(dp[i - 1][j][0].cost + remove(ci), dp[i - 1][j][0].symbol));
                        if (dp[i - 1][j][1] != oo)
                            res[1] = min(res[1], new State(dp[i - 1][j][1].cost + remove(ci), dp[i - 1][j][1].symbol));
                    }
                    if (j > 0) {
                        res[0] = min(res[0], new State(dp[i][j - 1][0].cost + remove(cj), dp[i][j - 1][0].symbol));
                        if (dp[i][j - 1][1] != oo)
                            res[1] = min(res[1], new State(dp[i][j - 1][1].cost + remove(cj), dp[i][j - 1][1].symbol));
                    }
                    if (i > 0 && j > 0) {
                        for (char nci : (HINOSXZ + MW).toCharArray()) {
                            char ncj = nci;
                            if (nci == 'M') ncj = 'W';
                            if (nci == 'W') ncj = 'M';
                            res[0] = min(res[0], new State(dp[i - 1][j - 1][0].cost + change(ci, nci) + change(cj, ncj), nci + dp[i - 1][j - 1][0].symbol + ncj));
                            res[1] = min(res[1], new State(dp[i - 1][j - 1][0].cost + change(ci, nci) + change(cj, ncj), nci + dp[i - 1][j - 1][0].symbol + ncj));
                        }
//                        res[0] = min(res[0], new State(dp[i - 1][j - 1][0].cost + remove(ci) + remove(cj), dp[i - 1][j - 1][0].symbol));
//                        if (dp[i - 1][j - 1][1] != oo)
//                            res[1] = min(res[1], new State(dp[i - 1][j - 1][1].cost + remove(ci) + remove(cj), dp[i - 1][j - 1][1].symbol));
                    }
                    dp[i][j] = res;
                }
            }
            for (int i = 0; i < 2; ++i) {
                if (dp[n][m][i].symbol.length() == 0) {
                    if (i == 1)
                        throw new RuntimeException();
                    continue;
                }
                if (ans.compareTo(dp[n][m][i]) > 0) {
                    ans = dp[n][m][i];
                }
            }
        }
        return ans.symbol;
    }

    private State min(State a, State b) {
        return a.compareTo(b) < 0 ? a : b;
    }

    int remove(char c) {
        return Math.min(c - 'A' + 1, 'Z' - c + 1);
    }

    int change(char s, char t) {
        return Math.abs(t - s);
    }

    class State implements Comparable<State> {
        int cost;
        String symbol;

        public State(int cost, String symbol) {
            this.cost = cost;
            this.symbol = symbol;
        }

        @Override
        public int compareTo(State o) {
            if (cost != o.cost)
                return cost - o.cost;

            if (symbol.length() != o.symbol.length())
                return -symbol.length() + o.symbol.length();
            return symbol.compareTo(o.symbol);
        }
    }
}
