package template.numbers;

/**
 * Created by dy on 17-1-19.
 */
public class DoubleUtils {

    public static double epsilon = 1e-9;

    public static int compare(double a, double b, double epsilon) {
        return Math.abs(a - b) < epsilon ? 0 : Double.compare(a, b);
    }

    public static int compare(double a, double b) {
        return compare(a, b, epsilon);
    }

}
