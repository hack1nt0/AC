package main;

import template.debug.InputReader;
import template.string.StringUtils;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;

/*
 ID: hackint1
 LANG: JAVA
 TASK: cryptcow
*/
//timeout... tedious branch cutting prob
public class Cryptcow {
    String TARGET = "Begin the Escape execution at the Break of Dawn";
    String COW = "COW";
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String crypt = in.readLine().trim();
        int[] trimed = crypt.chars().filter(c -> COW.indexOf(c) < 0).sorted().toArray();
        int insertCnt = crypt.length() - trimed.length;
        if (insertCnt % 3 != 0 || !Arrays.equals(TARGET.chars().sorted().toArray(), trimed)) {
            out.println(0 + " " + 0);
            return;
        }
        boolean successful = dfs(0, insertCnt / 3, new LinkedChar(crypt));
        if (!successful) {
            out.println(0 + " " + 0);
        } else {
            out.println(1 + " " + insertCnt / 3);
        }
    }

    HashSet<LinkedChar> cache = new HashSet<>();
    private boolean dfs(int cur, int max, LinkedChar linkedChar) {
        if (cur >= max) {
//            System.out.println(linkedChar);
            return linkedChar.equals(TARGET);
        }

        if (cache.contains(linkedChar)) return false;

        int i = 0;
        for (LinkedChar.Node curNode = linkedChar.head.next; curNode != linkedChar.tail; curNode = curNode.next) {
            if (curNode.c == 'C') break;
            if (curNode.c == 'O') return false;
            if (curNode.c == 'W') return false;
            if (curNode.c != TARGET.charAt(i++)) return false;
        }
        int j = TARGET.length() - 1;
        for (LinkedChar.Node curNode = linkedChar.tail.prev; curNode != linkedChar.head; curNode = curNode.prev) {
            if (curNode.c == 'W') break;
            if (curNode.c == 'C') return false;
            if (curNode.c == 'O') return false;
            if (curNode.c != TARGET.charAt(j--)) return false;
        }

        for (LinkedChar.Node p = linkedChar.head.next; p != linkedChar.tail; p = p.next) if (COW.indexOf(p.c) != -1) {
            StringBuilder stringBuilder = new StringBuilder();
            LinkedChar.Node q = p.next;
            while(q != linkedChar.tail) {
                if (COW.indexOf(p.c) != -1) break;
                stringBuilder.append(q.c);
                q = q.next;
            }
            if (!TARGET.contains(stringBuilder.toString())) return false;
            p = q;
        }

//        System.out.println(linkedChar);


        for (LinkedChar.Node cNode = linkedChar.head.next; cNode != linkedChar.tail; cNode = cNode.next) if (cNode.c == 'C') {
            for (LinkedChar.Node oNode = cNode.next; oNode != linkedChar.tail; oNode = oNode.next) if (oNode.c == 'O') {
                for (LinkedChar.Node wNode = linkedChar.tail.prev; wNode != oNode.prev; wNode = wNode.prev) if (wNode.c == 'W') {
//                    System.out.println(linkedChar);
                    linkedChar.swap(cNode.next, oNode.prev, oNode.next, wNode.prev);
//                    System.out.println(linkedChar);
                    linkedChar.remove(cNode);
                    linkedChar.remove(oNode);
                    linkedChar.remove(wNode);
//                    System.out.println(linkedChar);
                    if (dfs(cur + 1, max, linkedChar)) return true;
                    linkedChar.restore(cNode);
                    linkedChar.restore(oNode);
                    linkedChar.restore(wNode);
//                    System.out.println(linkedChar);
                    linkedChar.swap(cNode.next, oNode.prev, oNode.next, wNode.prev);
//                    System.out.println(linkedChar);
                }
            }
        }
//        cache.add(new LinkedChar(linkedChar));
        cache.add(linkedChar);
        return false;
    }

    static class LinkedChar implements Cloneable {
        class Node {
            char c;
            Node next, prev;
            public Node(char c) {this.c = c;}
            public Node() {}
        }
        Node head = new Node();
        Node tail = new Node();
        int size = 0;

        public LinkedChar(LinkedChar that) {
            Node h = head;
            for (Node cur = that.head.next; cur != that.tail; cur = cur.next) {
                Node n = new Node(cur.c);
                connect(h, n);
                h = n;
            }
            connect(h, tail);
        }

        public LinkedChar(String input) {
            size = input.length();
            Node h = head;
            for (char c : input.toCharArray()) {
                Node n = new Node(c);
                connect(h, n);
                h = n;
            }
            connect(h, tail);
        }

        public boolean equals(String that) {
            Node h = head.next;
            int i = 0;
            for(; h != tail; h = h.next) if (h.c != that.charAt(i++)) return false;
            return true;
        }

        public void connect(Node a, Node b) {
            if (a != null) a.next = b;
            if (b != null) b.prev = a;
        }

        public boolean remove(Node a) {
            if (a == null) return false;
            size--;
            a.prev.next = a.next;
            if (a.next != null) a.next.prev = a.prev;
            return true;
        }

        public boolean restore(Node a) {
            if (a == null) return false;
            size++;
            a.prev.next = a;
            if (a.next != null) a.next.prev = a;
            return true;
        }

        // [from, to] inclusive
        public void swap(Node from1, Node to1, Node from2, Node to2) {
            if (to1.next == from1 || to2.next == from2) return;
            Node first = from1.prev;
            Node last = to2.next;
            if (to1.next == from2) {
                Node mid = from1;
                connect(first, from2);
                connect(to2, mid);
                connect(to1, last);
            } else {
                Node mid1 = to1.next;
                Node mid2 = from2.prev;
                connect(first, from2);
                connect(to2, mid1);
                connect(mid2, from1);
                connect(to1, last);
            }
        }

        public void swap(int from1, int to1, int from2, int to2) {

            Node from1Node = node(from1);
            Node to1Node = node(to1 - from1, from1Node);
            Node from2Node = node(from2 - to1, to1Node);
            Node to2Node = node(to2 - from2, from2Node);
            swap(from1Node, to1Node, from2Node, to2Node);
        }

        public Node node(int idx, Node from) {
            Node cur = from;
            for (int i = 0; i < idx; ++i) cur = cur.next;
            return cur;
        }

        public Node node(int idx) {return node(idx, head.next);}

        @Override
        public String toString() {
            Node h = head.next;
            StringBuilder sb = new StringBuilder();
            for(; h != tail; h = h.next) sb.append(h.c);
            return sb.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            LinkedChar that = (LinkedChar) o;
            if (this.size != that.size) return false;
            Node p = head.next, q = that.head.next;
            while (p != tail) {
                if (p.c != q.c) return false;
                p = p.next;
                q = q.next;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hash = 0;
            int seed = 131; // 31 131 1313 13131 131313 etc..
            for (Node cur = head.next; cur != tail; cur = cur.next) hash = hash * seed + cur.c;
            hash &= 0x7FFFFFFF;
            return hash;
        }
    }

    public static void main(String[] args) {
        LinkedChar linkedChar = new LinkedChar("111222333");
        System.out.println(linkedChar);
        linkedChar.swap(1, 2, 4, 6);
        System.out.println(linkedChar);

    }
}
