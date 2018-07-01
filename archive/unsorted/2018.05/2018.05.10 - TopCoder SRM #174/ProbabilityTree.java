package main;

import template.collection.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProbabilityTree {
    class Node {
        List<Integer> childs = new ArrayList<>();
        double[] prob;
    }

    Node[] nodes;
    double lb, ub;

    public int[] getOdds(String[] tree, int lowerBound, int upperBound) {
        this.lb = lowerBound / 100.;
        this.ub = upperBound / 100.;
        int N = tree.length;
        nodes = new Node[N];
        for (int i = 0; i < N; ++i) {
            String[] tmp = tree[i].split(" ");
            nodes[i] = new Node();
            if (i == 0)
                nodes[i].prob = new double[]{Integer.parseInt(tmp[0]) / 100.};
            else
                nodes[i].prob = new double[]{Integer.parseInt(tmp[1]) / 100., Integer.parseInt(tmp[2]) / 100.};
        }
        for (int i = 1; i < N; ++i) {
            String[] tmp = tree[i].split(" ");
            int parent = Integer.parseInt(tmp[0]);
            nodes[parent].childs.add(i);
        }
        List<Integer> ans = new ArrayList<>();
        dfs(0, -1, ans);
        Collections.sort(ans);
        return CollectionUtils.toIntArray(ans);
    }

    private void dfs(int cur, double pp, List<Integer> ans) {
        double prob = cur == 0 ? nodes[0].prob[0] :
                (pp * nodes[cur].prob[0] + (1 - pp) * nodes[cur].prob[1]);
        if (lb < prob && prob < ub)
            ans.add(cur);
        for (int ichild : nodes[cur].childs)
            dfs(ichild, prob, ans);
    }
}
