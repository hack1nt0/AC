package main;

import java.util.ArrayList;

public class RandomFA {
    double totProb, tot;
    int nstate, nrule, finalState;

    public double getProbability(String[] rulesa, String[] rulesb, String[] rulesc, int finalState, int maxLength) {
        nstate = rulesa.length + 1;
        nrule = 3;
        this.finalState = finalState == 999 ? 0 : finalState + 1;
        double[][][] trans = new double[nrule][nstate][nstate];
        String[][] rules = {rulesa, rulesb, rulesc};
        for (int i = 0; i < nrule; ++i) {
            for (int cs = 1; cs < nstate; ++cs) {
                double sum = 0;
                for (String pair : rules[i][cs - 1].split(" ")) {
                    if (pair.trim().length() == 0)
                        continue;
                    String[] tmp = pair.split(":");
                    int ns = Integer.parseInt(tmp[0]) + 1;
                    double p = Double.parseDouble(tmp[1]) / 100;
                    trans[i][cs][ns] = p;
                    sum += p;
                }
                trans[i][cs][0] = 1.0 - sum;
            }
        }
        permutate(0, maxLength, new ArrayList<>(), trans);
        return totProb / tot;
    }

    private void permutate(int count, int maxLength, ArrayList<Integer> input, double[][][] trans) {
        double[][] p = new double[2][nstate];
        int current = 0;
        p[0][1] = 1.0;
        double curProb = 0;
        for (int i = 0; i < input.size(); ++i) {
            if (finalState == 0)
                curProb += p[current][finalState];
            int next = current ^ 1;
            for (int cs = 0; cs < nstate; ++cs) {
                p[next][cs] = 0;
                for (int ps = 1; ps < nstate; ++ps) {
                    p[next][cs] += p[current][ps] * trans[input.get(i)][ps][cs];
                }
            }
            current = next;
        }
        curProb += p[current][finalState];
        totProb += curProb;
        tot += 1;
        if (count == maxLength)
            return;
        for (int i = 0; i < nrule; ++i) {
            input.add(i);
            permutate(count + 1, maxLength, input, trans);
            input.remove(input.size() - 1);
        }
    }
}
