package main;

public class DistinguishableSetDiv1 {
    public int count(String[] answer) {
        int N = answer.length;
        int M = answer[0].length();
        boolean[] bad = new boolean[1 << M];
        for (int i = 0; i < N; ++i)
            for (int j = i + 1; j < N; ++j) {
                int badMask = 0;
                for (int k = 0; k < M; ++k) {
                    if (answer[i].charAt(k) == answer[j].charAt(k))
                        badMask += 1 << k;
                }
                bad[badMask] = true;
            }

        int notDisNum = 0;
        for (int mask = (1 << M) - 1; mask >= 0; mask--) {
            if (!bad[mask]) continue;
            notDisNum++;
            for (int k = 0; k < M; ++k)
                if (((mask >> k) & 1) > 0)
                    bad[mask ^ (1 << k)] = true;
        }
        int res = (1 << M) - notDisNum;
        return res;
    }
}
