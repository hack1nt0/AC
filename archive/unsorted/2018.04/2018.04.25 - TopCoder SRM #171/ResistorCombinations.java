package main;

import template.combinatorics.CombUtils;

import java.util.*;

public class ResistorCombinations {
    public double closestValue(String[] resistances, double target) {
        int n = resistances.length;
        List<Double> resists = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            resists.add(Double.parseDouble(resistances[i]));
        }
        if (n == 1)
            return resists.get(0);
        double closest = 2e6;
        for (int S = 1; S < 1 << n; ++S) {
            List<Double> subset = new ArrayList<>();
            for (int i = 0; i < n; ++i)
                if ((S >> i & 1) != 0)
                    subset.add(resists.get(i));
            Set<Double> ohmss = new HashSet<>();
            for (boolean serail : new boolean[]{true, false})
                ohmss.addAll(dfs(subset, serail));
            for (double ohms : ohmss) {
                if (Math.abs(ohms - target) < Math.abs(closest - target))
                    closest = ohms;
            }
        }
        return closest;
    }

    private Set<Double> dfs(List<Double> resists, boolean serial) {
        int n = resists.size();
        if (n <= 0)
            throw new RuntimeException();
        if (n == 1)
            return new HashSet<>(resists);
        Set<Double> ans = new HashSet<>();
        for (int[] index : CombUtils.groups(n)) {
            int ngroup = Arrays.stream(index).max().getAsInt() + 1;
            if (Arrays.stream(index).min().getAsInt() == ngroup - 1)
                continue;
            List<Double>[] group = new ArrayList[ngroup];
            for (int i = 0; i < ngroup; ++i)
                group[i] = new ArrayList<>();
            for (int i = 0; i < n; ++i)
                group[index[i]].add(resists.get(i));
            Set<Double>[] sets = new HashSet[ngroup];
            for (int i = 0; i < ngroup; ++i)
                sets[i] = dfs(group[i], !serial);
            Deque<Double> acc = new LinkedList<>();
            combine(0, sets, ans, acc, serial);
        }
        return ans;
    }

    private void combine(int cur, Set<Double>[] sets, Set<Double> ans, Deque<Double> acc, boolean serial) {
        if (cur == sets.length) {
            if (serial)
                ans.add(add(acc));
            else
                ans.add(parallel(acc));
            return;
        }
        for (double ohms : sets[cur]) {
            acc.push(ohms);
            combine(cur + 1, sets, ans, acc, serial);
            double t = acc.pop();
            if (t != ohms) {
                throw new RuntimeException();
            }
        }
    }


    private double parallel(Collection<Double> resists) {
        double res = 0;
        boolean first = true;
        for (double ohms : resists) {
            res = first ? ohms : res * ohms / (res + ohms);
            if (first) first = false;
        }
        return res;
    }

    private double add(Collection<Double> resists) {
        double res = 0;
        for (double ohms : resists)
            res += ohms;
        return res;
    }
}
