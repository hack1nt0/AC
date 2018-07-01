package main;

//WA..unclear description
public class Percents {
    public int minSamples(String percent) {
        double ratio = Double.parseDouble(percent.substring(0, 5)) / 100;
        double nenom = 1;
        while (true) {
            double denom = Math.floor(nenom * ratio);
            while (true) {
                int cmp = (int) (round(denom / nenom) * 10000) - (int) (ratio * 10000);
                if (cmp == 0)
                    return (int) nenom;
                if (cmp > 0)
                    break;
                denom++;
            }
            nenom++;
        }
    }

    private double round(double d) {
        String s = String.format("%.4f", d);
        return Double.parseDouble(s);
    }
}
