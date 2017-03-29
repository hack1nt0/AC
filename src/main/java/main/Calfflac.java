package main;

import template.collection.sequence.ImmutableIntList;
import template.collection.tuple.Tuple2;
import template.debug.InputReader;
import template.string.Palindromic;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/*
 ID: hackint1
 LANG: JAVA
 TASK: calfflac
*/

public class Calfflac {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        in.setFilter(new InputReader.SpaceCharFilter() {
            @Override
            public boolean isSpaceChar(int ch) {
                return ch == -1;
            }
        });
        String origin = in.readString();
        List<Integer> validIndex = new ArrayList<>();
        for (int i = 0; i < origin.length(); ++i) {
            char c = origin.charAt(i);
            if ('A' <= c && c <= 'Z' || 'a' <= c && c <= 'z') validIndex.add(i);
        }

        Tuple2<Integer, Integer> lp = Palindromic.manacher(new ImmutableIntList() {
            @Override
            public int size() {
                return validIndex.size();
            }

            @Override
            public int get(int index) {
                return Character.toUpperCase(origin.charAt(validIndex.get(index)));
            }
        });

        out.println(lp.getSecond() - lp.getFirst());
        int from = validIndex.get(lp.getFirst());
        int to = validIndex.get(lp.getSecond() - 1) + 1;
        for (int i = from; i < to; ++i) out.print(origin.charAt(i));
        out.println();
    }
}
