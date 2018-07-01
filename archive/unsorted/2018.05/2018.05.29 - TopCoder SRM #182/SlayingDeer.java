package main;

public class SlayingDeer {
    public int getTime(int A, int B, int C) {
        int da = A * 45;
        int db = B * 30 + C;
        int dd = db - da;
        if (dd >= C)
            return -1;
        else {
            int ans = 0;
            if (dd > 0) {
                int delta = C - dd;
                ans += C / delta * 45;
                dd = C % delta;
            } else {
                dd = C;
            }
            if (dd != 0) {
                if (A > B) {
                    int t = (dd + (A - B - 1)) / (A - B);
                    if (t <= 30) {
                        ans += t;
                    } else {
                        dd -= (A - B) * 30;
                        ans += 30 + (dd + A - 1) / A;
                    }
                } else {
                    dd += (B - A) * 30;
                    int t = (dd + A - 1) / A;
                    ans += 30 + t;
                }
            }
            return ans;
        }
    }
}
