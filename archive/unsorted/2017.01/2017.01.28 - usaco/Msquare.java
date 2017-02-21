package main;

import scala.collection.mutable.HashTable$HashUtils$class;
import template.collection.sequence.ArrayUtils;
import template.collection.tuple.Tuple2;

import java.util.*;
import java.io.PrintWriter;
/*
 ID: hackint1
 LANG: JAVA
 TASK: msquare
*/

/**
 * DFS is not appropriated for min path searching (unit length). BFS should be first one be considered.
 * But... When the state space is very large. 'depth increasing dfs' maybe work, while costly.
 */
public class Msquare {
    byte[] target;
    static int N = 2, M = 4;
    String operations;
    Map<Integer, Integer> cache;
    int minDepth = Integer.MAX_VALUE;
    int INF = Integer.MAX_VALUE;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        target = new byte[N * M];
        for (int i = 0; i < N; ++i){
            for (int j = 0; j < M; ++j) {
                if (i == 0) target[j] = (byte) (in.nextByte() - 1);
                else        target[M + M - j - 1] = (byte) (in.nextByte() - 1);
            }
        }
        byte[] init = new byte[N * M];
        for (int i = 0; i < N; ++i){
            for (int j = 0; j < M; ++j) {
                if (i == 0) init[j] = (byte) j;
                else        init[M + M - j - 1] = (byte) (M + j);
            }
        }

//        for (int depth = 0;;++depth) {
//            StringBuilder operations = new StringBuilder();
//            Set<Integer> visited = new HashSet<>();
//            if (dfs(init, 0, depth, operations, visited)) {
//                out.println(operations.length());
//                ArrayUtils.printlnConcisely(operations, out, 60);
//                return;
//            }
//            //System.err.println(depth);
//        }

        class Pair {
            byte[] present;
            Pair pre;
            char op;

            public Pair(byte[] present, Pair pre, char op) {
                this.present = present;
                this.pre = pre;
                this.op = op;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Pair pair = (Pair) o;

                return Arrays.equals(present, pair.present);
            }

            @Override
            public int hashCode() {
                return Arrays.hashCode(present);
            }
        }

        Queue<Pair> que = new LinkedList<Pair>();
        que.add(new Pair(init, null, 'D'));
        Set<Pair> visited = new HashSet<Pair>();
        while (!que.isEmpty()) {
            Pair cur = que.poll();
            if (Arrays.equals(cur.present, target)) {
                StringBuilder ops = new StringBuilder();
                while (true) {
                    if (cur == null) break;
                    ops.append(cur.op);
                    cur = cur.pre;
                }
                out.println(ops.length() - 1);
                ArrayUtils.printlnConcisely(ops.reverse().substring(1), out, 60);
                return;
            }

            byte[] afterA = ArrayUtils.clone(cur.present);
            opA(afterA);
            Pair A = new Pair(afterA, cur, 'A');
            if (!visited.contains(A)) {
                visited.add(A);
                que.add(A);
            }

            byte[] afterB = ArrayUtils.clone(cur.present);
            opB(afterB);
            Pair B = new Pair(afterB, cur, 'B');
            if (!visited.contains(B)) {
                visited.add(B);
                que.add(B);
            }

            byte[] afterC = ArrayUtils.clone(cur.present);
            opC(afterC);
            Pair C = new Pair(afterC, cur, 'C');
            if (!visited.contains(C)) {
                visited.add(C);
                que.add(C);
            }
        }
    }

    private boolean dfs(byte[] cur, int curDepth, int maxDepth, StringBuilder operations, Set<Integer> visited) {
        int present = 0;
        for (int i = 0; i < cur.length; ++i) present |= cur[i] << (i * 3);
        if (visited.contains(present)) return false;
        visited.add(present);

        if (curDepth >= maxDepth) {
            return Arrays.equals(cur, target);
            //return Arrays.equals(cur, target);
        }
        opA(cur);
        operations.append('A');
        if (dfs(cur, curDepth + 1, maxDepth, operations, visited)) return true;
        operations.setLength(operations.length() - 1);
        opA(cur);

        opB(cur);
        operations.append('B');
        if (dfs(cur, curDepth + 1, maxDepth, operations, visited)) return true;
        operations.setLength(operations.length() - 1);
        opReverseB(cur);

        opC(cur);
        operations.append('C');
        if (dfs(cur, curDepth + 1, maxDepth, operations, visited)) return true;
        operations.setLength(operations.length() - 1);
        opReverseC(cur);

        return false;
    }

    private static void opA(byte[] arr) {
        for (int j = 0; j < M; ++j) {
            byte swap = arr[j];
            arr[j] = arr[j + M];
            arr[j + M] = swap;
        }
    }

    private static void opB(byte[] arr) {
        byte swap = arr[M - 1];
        for (int j = M - 1; j > 0; --j) arr[j] = arr[j - 1];
        arr[0] = swap;

        swap = arr[M + M - 1];
        for (int j = M + M - 1; j > M ; --j) arr[j] = arr[j - 1];
        arr[M] = swap;
    }

    private static void opReverseB(byte[] arr) {
        byte swap = arr[0];
        for (int j = 0; j < M - 1; ++j) arr[j] = arr[ j + 1];
        arr[M - 1] = swap;

        swap = arr[M];
        for (int j = M; j < M + M - 1; ++j) arr[j] = arr[j + 1];
        arr[M + M - 1] = swap;
    }

    public static void opC(byte[] arr) {
        byte swap = arr[1];
        arr[1] = arr[6];
        arr[6] = swap;

        swap = arr[1];
        arr[1] = arr[5];
        arr[5] = swap;
        swap = arr[2];
        arr[2] = arr[6];
        arr[6] = swap;
    }

    public static void opReverseC(byte[] arr) {
        byte swap = arr[2];
        arr[2] = arr[5];
        arr[5] = swap;

        swap = arr[1];
        arr[1] = arr[5];
        arr[5] = swap;
        swap = arr[2];
        arr[2] = arr[6];
        arr[6] = swap;
    }

    public static void main(String[] args) {
        byte[] init = new byte[] {1, 2, 3, 4, 8, 7, 6, 5};
        ArrayUtils.printlnConcisely(init, new PrintWriter(System.out), 4);
        opA(init);
        ArrayUtils.printlnConcisely(init, new PrintWriter(System.out), 4);
        opA(init);
        ArrayUtils.printlnConcisely(init, new PrintWriter(System.out), 4);

        System.out.println("===========");

        opB(init);
        ArrayUtils.printlnConcisely(init, new PrintWriter(System.out), 4);
        opReverseB(init);
        ArrayUtils.printlnConcisely(init, new PrintWriter(System.out), 4);
        System.out.println("===========");

        opC(init);
        ArrayUtils.printlnConcisely(init, new PrintWriter(System.out), 4);
        opReverseC(init);
        ArrayUtils.printlnConcisely(init, new PrintWriter(System.out), 4);
    }
}
