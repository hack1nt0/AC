package main;

public class BadClock {
    public double nextAgreement(String trueTime, String skewTime, int hourlyGain) {
        int startTrue = parse(trueTime);
        int startFalse = parse(skewTime);
        if (startTrue == startFalse)
            return 0;
        double speedFalse = 1. + hourlyGain / 3600.;
        double ans = 0;
        if (startTrue < startFalse) {
            if (speedFalse < 1)
                ans = (startFalse - startTrue) / (1 - speedFalse);
            else
                ans = (12 * 3600 - (startFalse - startTrue)) / (speedFalse - 1);
        } else {
            if (speedFalse < 1)
                ans = (12 * 3600 - (startTrue - startFalse)) / (1 - speedFalse);
            else
                ans = (startTrue - startFalse) / (speedFalse - 1);
        }
        return ans / 3600;
    }

    private int parse(String time) {
        String[] tmp = time.split(":");
        int h = Integer.parseInt(tmp[0]) % 12;
        int m = Integer.parseInt(tmp[1]);
        int s = Integer.parseInt(tmp[2]);
        return h * 3600 + m * 60 + s;
    }
}
