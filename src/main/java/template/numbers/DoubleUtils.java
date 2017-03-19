package template.numbers;

import java.util.Comparator;

/**
 * Created by dy on 17-1-19.
 */
public class DoubleUtils {
    public static double epsilon = 1e-9;

    public static int compare(double a, double b) {
        // TODO: 17-1-19  NaN ? Inf?
        if (Math.abs(a - b) <= epsilon) return 0;
        if (a < b) return -1;
        return 1;
    }
}
