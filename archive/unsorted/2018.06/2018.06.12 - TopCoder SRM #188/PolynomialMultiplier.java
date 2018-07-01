package main;

import template.numbers.Convolution;

public class PolynomialMultiplier {
    public String product(String f1, String f2) {
        long[][] polys = new long[2][10];
        String[] fs = {f1, f2};
        for (int i = 0; i < fs.length; ++i) {
            String[] e = fs[i].split(" [+] ");
            for (String ee : e) {
                if (ee.length() == 1 && Character.isDigit(ee.charAt(0))) {
                    polys[i][0] += Integer.parseInt(ee);
                }
                else if (ee.length() == 1 && ee.charAt(0) == 'x') {
                    polys[i][1] += 1;
                }
                else if (ee.length() == 3 && ee.charAt(0) == 'x') {
                    polys[i][ee.charAt(2) - '0'] += 1;
                }
                else if (ee.length() == 3 && Character.isDigit(ee.charAt(0))) {
                    polys[i][1] += ee.charAt(0) - '0';
                }
                else {
                    int pow = ee.charAt(4) - '0';
                    int num = ee.charAt(0) - '0';
                    polys[i][pow] += num;
                }
            }
        }
        long[] c = Convolution.fft(polys[0], polys[1]);
        StringBuilder ans = new StringBuilder();
        for (int i = c.length - 1; i >= 0; --i) {
            if (c[i] == 0)
                continue;
            if (ans.length() > 0)
                ans.append(" + ");
            if (i == 0) {
                ans.append(c[i]);
            } else {
                if (c[i] > 1)
                    ans.append(c[i] + "*");
                if (i > 0)
                    ans.append("x");
                if (i > 1)
                    ans.append("^" + i);
            }
        }
        return ans.toString();
    }
}
