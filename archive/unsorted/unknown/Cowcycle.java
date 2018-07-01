package main;

import java.io.PrintWriter;
import java.util.Arrays;
import template.collection.sequence.ArrayUtils;
import template.debug.InputReader;
import template.stat.StatisticUtils;
/*
 ID: hackint1
 LANG: JAVA
 TASK: cowcycle
*/

public class Cowcycle {

    int F;
    int R;
    int FFROM;
    int FTO;
    int RFROM;
    int RTO;
    double minVar;
    int[] ansFs, ansRs;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        F = in.readInt();
        R = in.readInt();
        FFROM = in.readInt();
        FTO = in.readInt() + 1;
        RFROM = in.readInt();
        RTO = in.readInt() + 1;
        minVar = Double.MAX_VALUE;
        for (int fMin = FFROM; fMin < FTO; fMin++) {
            for (int fMax = FTO - 1; fMax - fMin + 1 >= F; fMax--) {
                for (int rMin = RFROM; rMin < RTO; rMin++) {
                    for (int rMax = RTO - 1; rMax - rMin + 1 >= R; rMax--) {
                        //fmax / rmin >= fmin / rmax * 3
                        if (fMax * rMax < fMin * rMin * 3) continue;

                        int[] fs = new int[F]; fs[0] = fMin; if (F > 1) fs[1] = fMax;
                        int[] rs = new int[R]; rs[0] = rMin; if (R > 1) rs[1] = rMax;
                        dfs(fMin + 1, fMax, 2, fMin + 1, rMin + 1, rMax, 2, rMin + 1, fs, rs);

//                        System.out.println(fMin + " " + fMax + " " + rMin + " " + rMax);
//                        System.out.println(minVar);
                    }
                }
            }
        }

        Arrays.sort(ansFs);
        ArrayUtils.printlnConcisely(ansFs, " " , out, 100);
        Arrays.sort(ansRs);
        ArrayUtils.printlnConcisely(ansRs, " ", out, 100);

    }

    private void dfs(int fMin, int fMax, int fIdx, int fFrom, int rMin, int rMax, int rIdx, int rFrom, int[] fs, int[] rs) {
        if (fIdx >= fs.length && rIdx >= rs.length) {
            double[] ratios = new double[F * R];
            int c = 0;
            for (int f : fs) for (int r : rs) ratios[c++] = (double) f / r;
            Arrays.sort(ratios);
            double[] diffs = new double[F * R - 1];
            for (int i = 0; i < diffs.length; ++i) diffs[i] = ratios[i + 1] - ratios[i];
            double curVar = StatisticUtils.var(diffs);
//            if (fMin == 39 + 1 && fMax == 53) {
//                System.out.println(curVar);
//                ArrayUtils.printlnConcisely(fs);
//                ArrayUtils.printlnConcisely(rs);
//                System.out.println();
//            }
            if (curVar < minVar || Math.abs(curVar - minVar) < 1e-6 && (ArrayUtils.compare(fs, ansFs) < 0 || ArrayUtils.compare(fs, ansFs) == 0 && ArrayUtils.compare(rs, ansRs) < 0)) {
                minVar = curVar;
                ansFs = fs.clone();
                ansRs = rs.clone();
            }
            return;
        }
        if (fIdx < fs.length) {
            for (int size = fFrom; size < fMax && fMax - 1 - size >= fs.length - fIdx - 1; size++) {
                fs[fIdx] = size;
                dfs(fMin, fMax, fIdx + 1, size + 1, rMin, rMax, rIdx, rFrom, fs, rs);
            }
        } else {
            for (int size = rFrom; size < rMax && rMax - 1 - size >= rs.length - rIdx - 1; size++) {
                rs[rIdx] = size;
                dfs(fMin, fMax, fIdx, fFrom, rMin, rMax, rIdx + 1, size + 1, fs, rs);
            }
        }
    }


}
