package main;

public class Salary {
    public int howMuch(String[] arrival, String[] departure, int wage) {
        int last1 = 0;
        int last2 = 0;
        for (int i = 0; i < arrival.length; ++i) {
            int L = seconds(arrival[i]);
            int R = seconds(departure[i]);
            int l1 = intersect(L, R, 0, 6 * 3600) + intersect(L, R, 18 * 3600, 24 * 3600);
            last1 += l1;
            last2 += R - L - l1;
        }
        return (int)((1.5 * last1 + last2) / 3600 * wage);
    }

    private int intersect(int L1, int R1, int L2, int R2) {
        return Math.max(0, Math.min(R1, R2) - Math.max(L1, L2));
    }

    private int seconds(String time) {
        String[] t = time.split(":");
        int s = 0;
        for (int i = 0; i < 3; ++i) {
            s += ((t[i].charAt(0) - '0') * 10 + t[i].charAt(1) - '0') * Math.pow(60, 2 - i);
        }
        return s;
    }


}
