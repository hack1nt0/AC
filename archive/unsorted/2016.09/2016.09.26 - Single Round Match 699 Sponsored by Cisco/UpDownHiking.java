package main;

public class UpDownHiking {
    public int maxHeight(int N, int A, int B) {
        double x = (double)B * N / (A + B);
        double ans = A * (int)x;
        if ((int)x != x) {
            ans = Math.max(ans, B * (N - (int)x - 1));
        }
        return (int)ans;
    }
}
