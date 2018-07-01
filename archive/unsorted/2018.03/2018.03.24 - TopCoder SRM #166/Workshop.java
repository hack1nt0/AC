package main;

import java.util.Arrays;

public class Workshop {
    public int pictureFrames(int[] pieces) {
        Arrays.sort(pieces);
        int ans = 0;
        for (int i = 2; i < pieces.length; ++i) {
            int p = 0, q = i - 1;
            while (p < q) {
                int cmp = pieces[p] + pieces[q] - pieces[i];
                if (cmp > 0) {
                    ans += q - p;
                    --q;
                }
                else if (cmp <= 0)
                    ++p;
            }
        }
        return ans;
    }
}
