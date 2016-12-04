package main;

public class DivisorDigits {
    public int howMany(int number) {
        int nt = number;
        int ans = 0;
        while (nt > 0) {
            int d = nt % 10;
            if (d != 0 && number % d == 0) ans++;
            nt /= 10;
        }
        return ans;
    }
}
