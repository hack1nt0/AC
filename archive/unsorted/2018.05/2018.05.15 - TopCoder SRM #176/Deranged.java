package main;

import template.combinatorics.CombUtils;
import template.numbers.IntUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Deranged {
    public long numDerangements(int[] nums) {
        int N = nums.length;
        long[][] dp = new long[N + 1][1 << N];
        dp[0][0] = 1L;
        for (int i = 0; i < nums.length; ++i) {
            for (int set = 0; set < 1 << N; ++set) {
                if (Long.bitCount(set) == i) {
                    long cur = dp[i][set];
                    int bad = 0;
                    for (int pos = 0; pos < N; ++pos) {
                        if (nums[pos] == nums[i])
                            bad |= 1 << pos;
                    }
                    int good = ~(bad | set);
                    for (int pos = 0; pos < N; ++pos) {
                        if ((good >> pos & 1) != 0) {
                            dp[i + 1][set | (1 << pos)] += cur;
                        }
                    }
                }
            }
        }
        long ordered = dp[N][(1 << N) - 1];
        Arrays.sort(nums);
        for (int i = 0; i < N; ) {
            int j = i;
            while (j < N && nums[j] == nums[i]) ++j;
            ordered /= CombUtils.fact(j - i);
            i = j;
        }
        return ordered;
    }
}
