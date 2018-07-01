package main;

import java.math.BigInteger;

public class BirthdayOdds {
    public int minPeople(int minOdds, int daysInYear) {
        int ans = 0;
        for (int i = 1; i <= daysInYear + 1; ++i) {
            double odds = 1;
            for (int j = 0; j < i; ++j)
                odds *= (daysInYear - j + 0.) / daysInYear;
            if (1. - odds >= minOdds / 100.) {
                ans = i;
                break;
            }
        }
        return ans;
    }
}
