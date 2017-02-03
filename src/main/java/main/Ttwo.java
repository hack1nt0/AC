package main;

import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: ttwo
*/
public class Ttwo {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = 10;
        char[][] forest = new char[n][];
        int farmerI, farmerJ, farmerDir, cowsI, cowsJ, cowsDir;
        farmerDir = cowsDir = 0;
        farmerI = farmerJ = cowsI = cowsJ = -1;
        for (int i = 0; i < n; ++i) {
            forest[i] = in.next().toCharArray();
            for (int j = 0; j < n; ++j) {
                if (forest[i][j] == 'C') {
                    cowsI = i;
                    cowsJ = j;
                }
                if (forest[i][j] == 'F') {
                    farmerI = i;
                    farmerJ = j;
                }
            }
        }
        int dirN = 4;
        int[] di = new int[]{-1, 0, +1, 0};
        int[] dj = new int[]{0, +1, 0, -1};
        boolean[][][][][][] visited = new boolean[n][n][dirN][n][n][dirN];
        int minutes = 0;
        while (true) {
            if (farmerI == cowsI && farmerJ == cowsJ) {
                break;
            }
            if (visited[farmerI][farmerJ][farmerDir][cowsI][cowsJ][cowsDir]) {
                minutes = 0; break;
            }
            visited[farmerI][farmerJ][farmerDir][cowsI][cowsJ][cowsDir] = true;

            {
                int ni = farmerI + di[farmerDir];
                int nj = farmerJ + dj[farmerDir];
                if (0 <= ni && ni < n && 0 <= nj && nj < n && forest[ni][nj] != '*') {
                    farmerI = ni;
                    farmerJ = nj;
                } else {
                    farmerDir = (farmerDir + 1) % dirN;
                }
            }

            {
                int ni = cowsI + di[cowsDir];
                int nj = cowsJ + dj[cowsDir];
                if (0 <= ni && ni < n && 0 <= nj && nj < n && forest[ni][nj] != '*') {
                    cowsI = ni;
                    cowsJ = nj;
                } else {
                    cowsDir = (cowsDir + 1) % dirN;
                }
            }
            minutes++;
        }

        out.println(minutes);
    }
}
