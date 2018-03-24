package main;

public class ShortPalindromes {
    String ans = null;

    public String shortest(String base) {
        String[][] dp = new String[base.length() + 1][base.length() + 1];
        char[] left;
        char[] right;
        String pivot;

        for (int sp = 0; sp < base.length() + base.length() + 1; ++sp) {
            if (sp % 2 == 0) {
                left = base.substring(0, sp / 2).toCharArray();
                right = new StringBuffer(base.substring(sp / 2)).reverse().toString().toCharArray();
                pivot = "";
            } else {
                int mid = (sp - 1) / 2;
                left = base.substring(0, mid).toCharArray();
                right = new StringBuffer(base.substring(mid + 1)).reverse().toString().toCharArray();
                pivot = base.substring(mid, mid + 1);
            }
            for (int i = left.length; i >= 0; --i) {
                for (int j = right.length; j >= 0; --j) {
                    String res = null;
                    if (i == left.length)
                        res = new String(right, j, right.length - j);
                    else if (j == right.length)
                        res = new String(left, i, left.length - i);
                    else {
                        if (left[i] == right[j])
                            res = "" + left[i] + dp[i + 1][j + 1];
                        String res1 = "" + left[i] + dp[i + 1][j];
                        String res2 = "" + right[j] + dp[i][j + 1];
                        if (res == null || compare(res1, res) < 0)
                            res = res1;
                        if (res == null || compare(res2, res) < 0)
                            res = res2;
                    }
                    dp[i][j] = res;
                }
            }
            String curAns = dp[0][0] + pivot + new StringBuffer(dp[0][0]).reverse().toString();
            if (ans == null || compare(curAns, ans) < 0)
                ans = curAns;

        }
        return ans;
    }

    int compare(String a, String b) {
        if (a.length() != b.length())
            return a.length() - b.length();
        else
            return a.compareTo(b);
    }
}
