package main;

import template.debug.InputReader;
import template.debug.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int N = in.readInt();
        int K = in.readInt();
        int[] ratings = new int[N];
        for (int i = 0; i < N; ++i) ratings[i] = in.readInt();
        int[] indexes = new int[K];
        int count = 0;
        for (int i = 0; i < N; ++i) {
            boolean dup = false;
            for (int j = 0; j < count; ++j)
                if (ratings[indexes[j] - 1] == ratings[i])
                    dup = true;
            if (!dup)
                indexes[count++] = i + 1;
            if (count == K)
                break;
        }
        if (count < K) {
            out.printLine("NO");
        } else {
            out.printLine("YES");
            out.printLine(indexes);
        }
    }
}
