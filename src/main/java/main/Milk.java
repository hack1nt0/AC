package main;

import template.collection.tuple.Tuple2;

import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: milk
*/

public class Milk {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int M = in.nextInt();
        PriorityQueue<Tuple2> qp = new PriorityQueue<>(Tuple2.FIRST_ELEMENT_ORDER);
        for (int i = 0; i < M; ++i) {
            int p = in.nextInt();
            int a = in.nextInt();
            qp.add(new Tuple2(p, a));
        }
        int minm = 0;
        while (true) {
            if (N <= 0) break;
            Tuple2<Integer, Integer> farmer = qp.poll();
            minm += farmer.getFirst() * Math.min(farmer.getSecond(), N);
            N -= farmer.getSecond();
        }
        out.println(minm);
    }
}
