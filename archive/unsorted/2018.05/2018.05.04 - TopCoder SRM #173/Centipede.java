package main;

import java.util.ArrayDeque;
import java.util.Deque;

public class Centipede {
    String[] screenLayout;

    class Node {
        int r, c, d;

        public Node(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        Node go() {
            int nr = r, nc = c, nd = d, n = screenLayout.length;
            if (r >= n) {
                nr = r + 1;
                nc = c;
                nd = d;
            } else {
                char next = screenLayout[r].charAt(c + d);
                char down = r + 1 >= n ? '.' : screenLayout[r + 1].charAt(c);
                if (next != '#')
                    nc = c + d;
                else if (down != '#') {
                    nd = -d;
                    nr = r + 1;
                } else {
//                    nc += -d;
                    nd = -d;
                }
            }
            return new Node(nr, nc, nd);
        }
    }

    public String[] simulate(String[] screenLayout, int timeUnits) {
        this.screenLayout = screenLayout;
        int length = 10;
        Deque<Node> worm = new ArrayDeque<>(length);
        for (int i = 0; i < length; ++i)
            worm.addFirst(new Node(0, i + 1, +1));
        int n = screenLayout.length;
        int cycle = 0;
        while (worm.getLast().r < n) {
            Node head = worm.getFirst();
            worm.addFirst(head.go());
            worm.removeLast();
            ++cycle;
        }
        timeUnits %= cycle;
        worm.clear();
        for (int i = 0; i < length; ++i)
            worm.addFirst(new Node(0, i + 1, +1));
        for (int t = 0; t < timeUnits; ++t) {
            Node head = worm.getFirst();
            worm.addFirst(head.go());
            worm.removeLast();
        }
        char[][] map = new char[n][];
        for (int i = 0; i < n; ++i)
            map[i] = screenLayout[i].toCharArray();
        for (Node node : worm) {
            if (node.r < n)
                map[node.r][node.c] = 'x';
        }
        String[] ans = new String[n];
        for (int i = 0; i < n; ++i)
            ans[i] = new String(map[i]);
        print(ans);
        return ans;
    }

    void print(String[] array) {
        for (int i = 0; i < array.length; ++i)
            System.out.println(array[i]);
    }
}
