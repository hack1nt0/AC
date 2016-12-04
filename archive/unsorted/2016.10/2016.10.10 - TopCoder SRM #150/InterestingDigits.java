package main;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class InterestingDigits {
    public int[] digits(int base) {
        class Pair {
            int mul, sum;

            public Pair(int mul, int sum) {
                this.mul = mul;
                this.sum = sum;
            }
        }
        List<Pair> cands = new ArrayList<Pair>();
        for (int i = 0; i < base; ++i)
            for (int j = 0; j < base; ++j)
                for (int k = 0; k < base; ++k) {
                    int d = i * base * base + j * base + k;
                    cands.add(new Pair(d, i + j + k));
                }
        List<Integer> ans = new ArrayList<Integer>();
        for (int i = 2; i < base; ++i) {
            boolean ok = true;
            for (Pair cand : cands) {
                if (cand.mul % i != 0) continue;
                if (cand.sum % i != 0) ok = false;
            }
            if (ok) ans.add(i);
        }
        int[] ans1 = new int[ans.size()];
        for (int i = 0; i < ans1.length; ++i) ans1[i] = ans.get(i);
        return ans1;
    }
}
