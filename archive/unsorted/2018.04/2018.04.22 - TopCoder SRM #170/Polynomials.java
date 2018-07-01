package main;

import java.util.ArrayList;
import java.util.List;

public class Polynomials {
    class Pow {
        int a, p;
        Pow(int a, int p) {
            this.a = a;
            this.p = p;
        }
    }
    List<Pow>[] poly = new ArrayList[2];
    public long integralPoints(int ymax, int xmax, String equation) {
        String[] fg = equation.split("=");
        for (int i = 0; i < 2; ++i) {
            poly[i] = new ArrayList<>();
            String[] pows = fg[i].split("[+]");
            for (int j = 0; j < pows.length; ++j) {
                String s = pows[j].trim();
                if (s.length() == 0)
                    continue;
                if (s.length() == 1 && s.charAt(0) != '0')
                    poly[i].add(new Pow(Integer.parseInt(s), 0));
                else if (s.length() > 1)
                    poly[i].add(new Pow(s.charAt(0) - '0', s.charAt(3) - '0'));
            }
        }
        long x = 0, y = 0;
        long xvalue = g(x), yvalue = f(y);
        long ans = 0;
        while (x <= xmax && y <= ymax) {
            if (xvalue == yvalue) {
//                System.out.println(x + " " + xmax + ", " + y + " " + ymax);
                ++ans;
                ++x;
                ++y;
                xvalue = g(x);
                yvalue = f(y);
            } else if (xvalue < yvalue) {
                ++x;
                xvalue = g(x);
            } else if (yvalue < xvalue) {
                ++y;
                yvalue = f(y);
            }
        }
        boolean[] isConst = new boolean[2];
        for (int i = 0; i < 2; ++i) {
            isConst[i] = true;
            for (Pow pow : poly[i])
                if (pow.a != 0 && pow.p != 0) isConst[i] = false;
        }
        if (isConst[0] && isConst[1] && ans > 0)
            ans = (xmax + 1L) * (ymax + 1L);
        else if (isConst[0] && !isConst[1] && ans > 0)
            ans = ymax + 1L;
        else if (!isConst[0] && isConst[1] && ans > 0)
            ans = xmax + 1L;
        return ans;
    }

    long g(long x) {
        long res = 0;
        for (Pow pow : poly[1])
            res += pow.a * Math.pow(x, pow.p);
        return res;
    }

    long f(long y) {
        long res = 0;
        for (Pow pow : poly[0])
            res += pow.a * Math.pow(y, pow.p);
        return res;
    }
}
