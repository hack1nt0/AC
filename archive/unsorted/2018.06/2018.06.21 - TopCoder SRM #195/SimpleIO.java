package main;

import template.string.TrieSet;

public class SimpleIO {
    int oo = Integer.MAX_VALUE;
    public int worstCase(String pattern, int targetState) {
        int n = pattern.length();
        String[] P = new String[n];
        for (int i = 0; i < n; ++i) {
            P[i] = "";
            for (int o = 0; o < n; ++o)
                P[i] += pattern.charAt((i + 1 + o) % n);
        }
        Trie tree = new Trie();
        for (int i = 0; i < n; ++i) {
            tree.add(P[i]);
        }
        int max = 0;
        for (int i = 0; i < n; ++i) {
            int min = tree.depth(P[i]);
            if (min == oo) {
                max = -1;
                break;
            }
            int j = (i + min) % n;
            max = Math.max(max, min + (j <= targetState ? targetState - j : n - (j - targetState)));
        }
        return max;
    }

    class Trie {
        public void add(String s) {
            Node cur = root;
            for (char c : s.toCharArray()) {
                int ic = c == 'B' ? 0 : 1;
                if (cur.childs[ic] == null)
                    cur.childs[ic] = new Node();
                cur.childs[ic].depth = cur.depth + 1;
                cur.childs[ic].count++;
                cur = cur.childs[ic];
            }
        }

        public int depth(String s) {
            int min = oo;
            Node cur = root;
            for (char c : s.toCharArray()) {
                int ic = c == 'B' ? 0 : 1;
                if (cur.childs[ic].count == 1)
                    min = Math.min(min, cur.childs[ic].depth);
                cur = cur.childs[ic];
            }
            return min;
        }

        class Node {
            Node[] childs = new Node[2];
            int depth;
            int count;
        }
        Node root = new Node();

    }
}
