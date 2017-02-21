package main;

import template.collection.map.BidirectionalMap;
import template.operation.ShortestPath;

import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: comehome
*/
public class Comehome {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int en = in.nextInt();
        BidirectionalMap<Character, Integer> symbolToId = new BidirectionalMap<>();
        for (char c = 'a'; c <= 'z'; ++c) symbolToId.put(c, symbolToId.size());
        for (char c = 'A'; c <= 'Z'; ++c) symbolToId.put(c, symbolToId.size());
        int n = symbolToId.size();
        ShortestPath shortestPath = new ShortestPath(n);
        boolean[] hasCow = new boolean[n];
        for (int i = 0; i < en; ++i) {
            char from = in.next().charAt(0);
            char to = in.next().charAt(0);
            int dist = in.nextInt();
            shortestPath.addE(symbolToId.get(from), symbolToId.get(to), dist);
            shortestPath.addE(symbolToId.get(to), symbolToId.get(from), dist);
        }

        char start = 'Z';
        long[] shortest = shortestPath.bellmanford(symbolToId.get(start));
        char pasture = 'Z' + 1;
        long minDist = Long.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            char c = symbolToId.getFromValue(i);
            if (c == start || Character.isLowerCase(c)) continue;
            if (shortest[i] < minDist) {
                pasture = c;
                minDist = shortest[i];
            }
        }

        out.println(pasture + " " + minDist);
    }
}
