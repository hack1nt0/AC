package main;

import java.util.ArrayList;

public class BearPasswordAny {
    public String findPassword(int[] x) {
        String NOVALID = "";
        int N = x.length;
        if (x[0] != N) return NOVALID;
        ArrayList<Integer> segs = new ArrayList<Integer>();
        for (int i = N - 1; i > 0; --i) {
            int curLen = i + 1;
            int minNum = 0;
            for (int segLen : segs) minNum += segLen - curLen + 1;
            if (x[i] == minNum) continue;
            if (x[i] < minNum) return NOVALID;
            int offN = curLen == N ? 0 : segs.size() + x[i] - minNum - 1;
            int totLen = (x[i] - minNum) * curLen + offN;
            for (int segLen: segs) totLen += segLen;
            if (totLen > N) return NOVALID;
            for (int j = 0; j < x[i] - minNum; ++j)
                segs.add(curLen);
        }
        String ans = "";
        for (int i = 0; i < segs.size(); ++i) {
            if (i > 0) ans += "b";
            ans += str("a", segs.get(i));
        }
        if (ans.length() < N) {
            ans += str("ba", N - ans.length());
        }
        return ans;
    }

    private String str(String a, int len) {
        if (len <= a.length())
            return a.substring(0, len);
        return a + str(a, len - a.length());
    }
}
