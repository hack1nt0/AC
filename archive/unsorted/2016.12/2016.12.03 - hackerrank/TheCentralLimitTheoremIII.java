package main;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.io.PrintWriter;

public class TheCentralLimitTheoremIII {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        double sm = in.nextDouble();
        double sv = in.nextDouble();
        double interval = in.nextDouble();
        double z = in.nextDouble();
        double m = sm, v = sv / Math.sqrt(n);
        //mx ~ N(m, v), then (mx - m)/v ~ N(0,1)
        //ans = P(0.25 < mx <= 0.975)


        double lower = m - z * v;
        double upper = m + z * v;
        out.println(new DecimalFormat("#.##").format(lower));
        out.println(new DecimalFormat("#.##").format(upper));
        out.close();
    }
}
