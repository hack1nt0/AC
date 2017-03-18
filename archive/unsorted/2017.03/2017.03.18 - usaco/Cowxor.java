package main;

import template.collection.tuple.Tuple2;
import template.debug.InputReader;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: cowxor
*/

public class Cowxor {
    int BITS = 21;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.readInt();
        int[] cows = new int[n];
        for (int i = 0; i < n; ++i) cows[i] = in.readInt();
        int xorAcc, maxXor, maxFrom, maxTo;
        xorAcc = maxXor = maxFrom = maxTo = 0;
        Trie trie = new Trie();
        trie.add(0, 0);
        for (int i = 0; i < n; ++i) {
            xorAcc ^= cows[i];
            Tuple2<Integer, Integer> tuple2 = trie.find(((1 << BITS) - 1) ^ xorAcc);
            int xor = xorAcc ^ tuple2.getFirst();
            if (xor > maxXor || maxTo == 0) {
                maxXor = xor;
                maxFrom = tuple2.getSecond();
                maxTo = i + 1;
            }
            trie.add(xorAcc, i + 1);
        }

        out.println(maxXor + " " + (maxFrom + 1) + " " + maxTo);
    }

    class Trie {
        int R = 2;

        class Node {
            Node[] links;
            int to, xor;
            public Node() {links = new Node[R];}
            public boolean isLeaf() {
                return to > 0;
            }
        }

        Node root = new Node();

        public Tuple2<Integer, Integer> find(int targetXor) {
            Node p = root;
            for (int i = BITS - 1; i >= 0; --i) {
                int link = (targetXor >> i & 1);
                if (p.links[link] == null) link = 1 - link;
                p = p.links[link];
            }
            return new Tuple2<>(p.xor, p.to);
        }

        public void add(int newXor, int to) {
            Node p = root;
            for (int i = BITS - 1; i >= 0; --i) {
                int link = (newXor >> i & 1);
                if (p.links[link] == null) p.links[link] = new Node();
                p = p.links[link];
            }
            p.to = to;
            p.xor = newXor;
        }
    }
}
