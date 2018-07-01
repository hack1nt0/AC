package main;

public class NumericalIntegral {
    public String integrate(double x1, double x2) {
        //e ^ -x^2
        double lx = Math.min(x1, x2);
        double rx = Math.max(x1, x2);
        double integral = 0;
        int N = (int) 1e6;
        double width = (rx - lx) / N;
        for (int i = 0; i < N; ++i) {
            double x = lx + width * i;
//            integral += - x * x * x / 3 * width;
            integral += Math.exp(- x * x) * width;
        }
//        integral = Math.exp(integral);
        return String.format("%.5f", integral);
    }
}
