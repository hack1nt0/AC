package main;

import java.util.Scanner;
import java.io.PrintWriter;

import static java.time.Year.isLeap;

/*
 ID: hackint1
 LANG: JAVA
 TASK: friday
*/

public class Friday {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int[] freq = new int[7];
        int monthStart = 1;
        for (int y = 1900; y < 1900 + N; ++y) {
            for (int m = 1; m <= 12; ++m) {
                int w = (monthStart + 13 - 1) % 7;
                freq[w]++;
                monthStart += days(y, m);
            }
        }
        int i = 6;
        while (true) {
            out.print(freq[i] + " ");
            i = (i + 1) % 7;
            if (i == 6) break;
        }
        out.println();
    }

    private int days(int y, int m) {
        if (isLeap(y) && m == 2) return 29;

        switch (m) {
            case 2:
                return 28;

            case 1:case 3:case 5:case 7:case 8:case 10:case 12:
                return 31;

            default:
                return 30;
        }
    }
}
