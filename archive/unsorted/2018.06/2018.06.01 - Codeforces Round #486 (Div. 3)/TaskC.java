package main;

import template.debug.InputReader;
import template.debug.OutputWriter;

import java.util.HashMap;
import java.util.Map;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Map<Long, Where> sumSet = new HashMap<>();
        int K = in.readInt();
        for (int seq = 0; seq < K; ++seq) {
            int N = in.readInt();
            int[] ints = new int[N];
            long sum = 0;
            for (int ind = 0; ind < N; ++ind) {
                ints[ind] = in.readInt();
                sum += ints[ind];
            }
            for (int remove = 0; remove < N; ++remove) {
                long los = sum - ints[remove];
                Where old = sumSet.get(los);
                if (old != null && old.seq != seq) {
                    out.printLine("YES");
                    out.printLine((old.seq + 1) + " " + (old.ind + 1));
                    out.printLine((seq + 1) + " " + (remove + 1));
                    return;
                } else if (old == null) {
                    sumSet.put(los, new Where(seq, remove));
                }
            }
        }
        out.printLine("NO");
    }

    class Where {
        int seq, ind;

        public Where(int seq, int ind) {
            this.seq = seq;
            this.ind = ind;
        }
    }
}
