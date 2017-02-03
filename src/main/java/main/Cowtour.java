package main;

import template.collection.sequence.ArrayUtils;
import template.geometry.Point;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.io.PrintWriter;
/*
 ID: hackint1
 LANG: JAVA
 TASK: cowtour
*/
public class Cowtour {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        Point[] pastures = new Point[n];
        for (int i = 0; i < n; ++i) pastures[i] = new Point(in.nextDouble(), in.nextDouble());
        int[] field = new int[n];
        double[][] adj = new double[n][n];
        double MAXD = 200000;
        for (int i = 0; i < n; ++i) {
            String line = in.next();
            for (int j = 0; j < n; ++j) {
                if (i > j) { // for float stable
                    adj[i][j] = adj[j][i];
                    continue;
                }
                if (i == j) {
                    adj[i][j] = 0;
                    continue;
                }
                if (line.charAt(j) == '0') adj[i][j] = MAXD;
                else adj[i][j] = pastures[i].distance(pastures[j]);
            }
        }
        Arrays.fill(field, -1);
        int fieldN = 0;
        for (int i = 0; i < n; ++i) {
            if (field[i] != -1) continue;
            field[i] = fieldN;
            while (true) {
                boolean updated = false;
                for (int j = 0; j < n; ++j) {
                    if (field[j] != fieldN) continue;
                    for (int k = 0; k < n; ++k)
                        if (adj[j][k] != MAXD && field[k] == -1) {
                            field[k] = fieldN;
                            updated = true;
                        }
                }
                if (!updated) break;
            }
            fieldN++;
        }

        double[][] minDist = ArrayUtils.clone(adj);
        for (int k = 0; k < n; ++k) //if (field[k] == field[ni] || field[k] == field[nj])
            for (int i = 0; i < n; ++i) //if (field[i] == field[ni] || field[i] == field[nj])
                for (int j = 0; j < n; ++j) //if (field[j] == field[ni] || field[j] == field[nj])
                    if (minDist[i][k] < MAXD && minDist[k][j] < MAXD)
                        minDist[i][j] = Math.min(minDist[i][j], minDist[i][k] + minDist[k][j]);

        double[] fieldDiameter = new double[n];
        double[] pastureDiameter = new double[n];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j) if (field[i] == field[j]) {
                fieldDiameter[field[i]] = Math.max(fieldDiameter[field[i]], minDist[i][j]);
                pastureDiameter[i] = Math.max(pastureDiameter[i], minDist[i][j]);
            }

        double minDiameter = Double.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (field[i] == field[j]) continue;
                double diameter = ArrayUtils.max(pastureDiameter[i] + pastureDiameter[j] + pastures[i].distance(pastures[j]),
                        fieldDiameter[field[i]],
                        fieldDiameter[field[j]]);
                //System.err.println(diameter + " " + (char)(i + 'A') + " " + (char)(j + 'A'));
                minDiameter = Math.min(minDiameter, diameter);
            }
        }

        out.printf("%.6f\n", minDiameter);
    }
}
