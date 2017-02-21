package main;

import template.collection.tuple.Tuple2;

import java.util.Scanner;
import java.io.PrintWriter;
/*
 ID: hackint1
 LANG: JAVA
 TASK: inflate
*/
public class Inflate {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int minutes = in.nextInt();
        int categorieN = in.nextInt();
        Tuple2<Integer, Integer>[] categories = new Tuple2[categorieN];
        for (int i = 0; i < categorieN; ++i) categories[i] = new Tuple2<>(in.nextInt(), in.nextInt());
        int[] maxPoints = new int[minutes + 1];
        for (int category = 0; category < categorieN; ++category) {
            for (int minute = 1; minute <= minutes; ++minute) {
                int res = maxPoints[minute];
                if (minute >= categories[category].getSecond())
                    res = Math.max(res, maxPoints[minute - categories[category].getSecond()] + categories[category].getFirst());
                maxPoints[minute] = res;
                //if (category == categorieN - 1) System.err.printlnConcisely(maxPoints[minute]);
            }
        }

        out.println(maxPoints[minutes]);
    }
}
