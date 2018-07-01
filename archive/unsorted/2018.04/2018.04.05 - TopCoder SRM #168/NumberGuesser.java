package main;

public class NumberGuesser {
    public int guess(String leftOver) {
        for (int removed = 1; removed < 10; ++removed) {
            for (int p = 0; p <= leftOver.length(); ++p) {
                int c = Integer.parseInt(leftOver.substring(0, p) + removed + leftOver.substring(p));
                for (int b = 1; b <= 9998; ++b) {
                    int[] valid = new int[10];
                    int t = b;
                    while (t > 0) {
                        int d = t % 10;
                        if (d != 0) ++valid[d];
                        t /= 10;
                    }
                    int a = b + c;
                    boolean good = a != b && a > b;
                    t = a;
                    while (t > 0) {
                        int d = t % 10;
                        if (d != 0) {
                            if (valid[d] == 0) {
                                good = false;
                                break;
                            } else {
                                --valid[d];
                            }
                        }
                        t /= 10;
                    }
                    if (good && allZero(valid))
                        return removed;
                }
            }
        }
        return -1;
    }

    boolean allZero(int[] x) {
        boolean res = true;
        for (int i = 0; i < x.length; ++i) res &= x[i] == 0;
        return res;
    }
}
