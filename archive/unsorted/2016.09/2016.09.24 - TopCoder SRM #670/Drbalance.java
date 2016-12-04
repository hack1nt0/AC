package main;

public class Drbalance {
    public int lesscng(String s, int k) {
        int[] bal = new int[s.length()];
        int plusc = 0;
        int minusc = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '+') plusc++;
            else minusc++;
            bal[i] = plusc - minusc;
        }
        int ans = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (cntBal(i, bal) <= k) break;
            if (s.charAt(i) == '+') continue;
            ans++;
            for (int j = i; j < s.length(); ++j)
                bal[j] += 2;
        }
        return ans;
    }

    private int cntBal(int cur, int[] bal) {
        int res = 0;
        for (int i = cur; i < bal.length; ++i)
            if (bal[i] < 0) res++;
        return res;
    }
}
