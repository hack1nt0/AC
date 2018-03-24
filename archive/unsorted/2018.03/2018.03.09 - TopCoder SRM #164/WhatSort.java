package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WhatSort {
    class Interval {
        int s, t;
        Interval(int s, int t) {
            this.s = s;
            this.t = t;
        }
    }
    String[] name;
    int[] age, wt;
    public String sortType(String[] name, int[] age, int[] wt) {
        this.name = name;
        this.age = age;
        this.wt = wt;
        String variable = "NAW";
        String ans = null;
        int[] perm = {0, 1, 2};
        do {
            List<Interval> intervals = new ArrayList<>();
            intervals.add(new Interval(0, name.length));
            boolean failed = false;
            for (int i = 0; i < perm.length; ++i) {
                List<Interval> newIntervals = new ArrayList<>();
                for (Interval interval : intervals) {
                    int j = interval.s + 1;
                    int s = interval.s;
                    while (j < interval.t) {
                        int cmp = compare(j, perm[i]);
                        if (cmp > 0) {
                            failed = true;
                            break;
                        }
                        if (cmp < 0) {
                            if (j - s > 1)
                                newIntervals.add(new Interval(s, j));
                            s = j;
                        }
                        ++j;
                    }
                    if (failed) break;
                    if (j - s > 1)
                        newIntervals.add(new Interval(s, j));
                }
                if (failed) break;
                intervals = newIntervals;
            }
            if (!failed) {
                if (ans == null) {
                    ans = "";
                    for (int i = 0; i < perm.length; ++i) ans += variable.charAt(perm[i]);
                } else {
                    return "IND";
                }
            }
        } while (next(perm));
        return ans == null ? "NOT" : ans;
    }

    boolean next(int[] perm) {
        for (int i = perm.length - 1; i > 0; --i)
            if (perm[i - 1] < perm[i]) {
                for (int j = perm.length - 1; j > i - 1; --j)
                    if (perm[j] > perm[i - 1]) {
                        int t = perm[i - 1];
                        perm[i - 1] = perm[j];
                        perm[j] = t;
                        Arrays.sort(perm, i, perm.length);
                        return true;
                    }
            }
        return false;
    }

    int compare(int i, int v) {
        if (v == 0)
            return name[i - 1].compareTo(name[i]);
        else if (v == 1)
            return age[i - 1] - age[i];
        else
            return -wt[i - 1] + wt[i];
    }
}
