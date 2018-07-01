package main;

import template.debug.InputReader;
import template.debug.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int R = in.readInt();
        int C = in.readInt();
        int N = in.readInt();
        long min = (C - 1) * R + (R - 1) * C;
        Node[][] grid = new Node[R][C];
        int[] dr = {-1, +1, 0, 0};
        int[] dc = {0, 0, -1, +1};
        BinaryHeap heap = new BinaryHeap(R * C);
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                int nwall = 0;
                for (int d = 0; d < dr.length; ++d) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (0 <= nr && nr < R && 0 <= nc && nc < C)
                        nwall++;
                }
                grid[r][c] = new Node(nwall, r, c);
                heap.add(grid[r][c]);
            }
        }
        long all = R * C;
        while (all > N) {
            Node top = heap.pop();
            top.removed = true;
            min -= top.nwall;
            for (int d = 0; d < dr.length; ++d) {
                int nr = top.r + dr[d];
                int nc = top.c + dc[d];
                if (0 <= nr && nr < R && 0 <= nc && nc < C) {
                    Node neighbor = grid[nr][nc];
                    if (!neighbor.removed) {
                        neighbor.nwall--;
                        heap.update(neighbor);
                    }
                }
            }
            all--;
        }
        out.printLine("Case #" + testNumber + ": " + min);
    }

    class Node {
        int nwall, pos = -1, r, c;
        boolean removed = false;
        Node (int nwall, int r, int c) {
            this.nwall = nwall;
            this.r = r;
            this.c = c;
        }
    }

    class BinaryHeap {

        Node[] array;
        int size;

        public BinaryHeap(int size) {
            array = new Node[size];
        }

        public void add (Node element) {
            if (size == array.length)
                throw new RuntimeException();
            array[size] = element;
            element.pos = size;
            if (size > 0) {
                int ielement = size;
                int iparent = (size - 1) / 2;
                while (true) {
                    Node parent = array[iparent];
                    if (parent.nwall < element.nwall) {
                        array[iparent] = element;
                        array[ielement] = parent;
                        parent.pos = ielement;
                        element.pos = iparent;
                        if (iparent == 0)
                            break;
                        ielement = iparent;
                        iparent = (ielement - 1) / 2;
                    } else {
                        break;
                    }
                }
            }
            size++;
        }

        public Node pop() {
            Node t = array[0];
            array[0] = array[size - 1];
            size--;
            down(0);
            return t;
        }

        public void update(Node element) {
            down(element.pos);
        }

        private void down(int pos) {
            Node cur = array[pos];
            while (pos * 2 + 1 < size) {
                Node maxChild = array[pos * 2 + 1];
                if (pos * 2 + 2 < size && maxChild.nwall < array[pos * 2 + 2].nwall)
                    maxChild = array[pos * 2 + 2];
                if (maxChild.nwall > cur.nwall) {
                    array[pos] = maxChild;
                    array[maxChild.pos] = cur;
                    int npos = maxChild.pos;
                    cur.pos = npos;
                    maxChild.pos = pos;
                    pos = npos;
                } else {
                    break;
                }
            }
        }
    }
}
