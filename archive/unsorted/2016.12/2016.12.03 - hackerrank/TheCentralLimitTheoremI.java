package main;

import main.template.Statistics;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.io.PrintWriter;

public class TheCentralLimitTheoremI {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        double upper = in.nextDouble();
        double n = in.nextDouble();
        double sm = in.nextDouble();
        double sv = in.nextDouble();
        //X = x1+x2+...+xn ~ N(n*sm, sqrt(n)*sv)
        //g(x) = (x-n*sm)/(sqrt(n)*sv); Y = g(X) ~ N(0, 1); U = g(upper)
        //P(X <= upper) = P(Y <= U)

        double m = n * sm, v = Math.sqrt(n) * sv;
        upper = (upper - m) / v;
        double prob = Statistics.cdfNormal(upper);
        //out.printf("%.4f\n", prob);
        out.println(new DecimalFormat("#.####").format(prob));
        out.close();
    }

}
