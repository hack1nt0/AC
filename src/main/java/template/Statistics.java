package template;

/**
 * Created by dy on 16-11-27.
 */
public class Statistics {
    public static double cdfNormal(double x) {
        double res = x;
        double acc = x;
        for (int i = 1; i <= 100; ++i) {
            acc = acc * x * x / (2 * i + 1);
            res += acc;
        }
        res = 0.5 + res / Math.sqrt(Math.PI * 2) * Math.exp(-x * x / 2);
        return res;
    }


}
