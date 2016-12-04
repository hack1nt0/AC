package main;

import java.util.Arrays;
import java.util.Comparator;

public class Lottery {
    public String[] sortByOdds(String[] rules) {
        final String[] names = new String[rules.length];
        final double[] validc = new double[rules.length];
        for (int i = 0; i < rules.length; ++i) {
            names[i] = rules[i].substring(0, rules[i].indexOf(":"));

            String[] tmp = rules[i].substring(rules[i].indexOf(":") + 1).trim().split("[ ]");
            if (tmp.length != 4) throw new RuntimeException();
            int choices = Integer.valueOf(tmp[0]);
            int plots = Integer.valueOf(tmp[1]);
            boolean sorted = tmp[2].equals("T") ? true : false;
            boolean unique = tmp[3].equals("T") ? true : false;
            if (sorted && unique) validc[i] = C(choices, plots);
            if (sorted && !unique) validc[i] = unlpack(choices, plots);
            if (!sorted && unique) validc[i] = fact(choices, plots);
            if (!sorted && !unique) validc[i] = Math.pow(choices, plots);
        }

        Integer[] rank = new Integer[rules.length];
        for (int i = 0; i < rank.length; ++i) rank[i] = i;
        Arrays.sort(rank, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                if (validc[o1] > validc[o2]) return 1;
                if (validc[o1] < validc[o2]) return -1;
                return names[o1].compareTo(names[o2]);
            }
        });

        String[] ans = new String[rank.length];
        for (int i = 0; i < ans.length; ++i) ans[i] = names[rank[i]];

        return ans;
    }

    private double unlpack(int n, int c) {
        double[][] cnt = new double[n + 1][c + 1];
        cnt[0][0] = 1;
        for (int i = 0; i < n; ++i)
            for (int j = 0; j <= c; ++j) {
                double res = 0;
                for (int k = 0; k <= j; ++k)
                    res += cnt[i][j - k];
                cnt[i + 1][j] = res;
            }
        //System.out.println(Arrays.deepToString(cnt));
        return cnt[n][c];
    }

    private long fact(long n, long m) {
        long res = 1;
        for (int i = 0; i < m; ++i) res *= n - i;
        return res;
    }

    private long C(long n, long m) {
        return fact(n, m) / fact(m, m);
    }
}
