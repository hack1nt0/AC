package main;

import template.collection.sequence.ArrayQueue;
import template.collection.sequence.ArrayUtils;
import template.collection.sequence.IntArrayQueue;
import template.collection.tuple.Tuple2;
import template.debug.Stopwatch;

import java.util.*;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: camelot
*/

/**
 * Branch clipping is the trick here.
 */

public class Camelot {
    int R, C;

    public void solve2(int testNumber, Scanner in, PrintWriter out) {
        R = in.nextInt();
        C = in.nextInt();
        //int king = compress(in.next(), in.nextInt());

        List<Integer> knightsList = new ArrayList<>();
        while (true) {
            if (!in.hasNext()) break;
            knightsList.add((in.next().charAt(0) - 'A') + (in.nextInt() - 1) * C);
        }
        int[] knights = (int[])ArrayUtils.outbox(knightsList.toArray(new Integer[0]));
        int N = R * C;
        int[][] distKnight = new int[N][N];
        int[][] distKing = new int[N][N];
        int[] drKnight = new int[]{+2, +1, -1, -2, -2, -1, +1, +2};
        int[] dcKight = new int[]{+1, +2, +2, +1, -1, -2, -2, -1};
        int[] drKing = new int[]{+1, +1, 0, -1, -1, -1, 0, +1};
        int[] dcKing = new int[]{0, +1, +1, +1, 0, -1, -1, -1};

        Stopwatch.tic();
        int INF = 900;
        {
            for (int k = 0; k < 2; ++k) {
                int[][] dist = k > 0 ? distKnight : distKing;
                int[] dr = k > 0 ? drKnight : drKing;
                int[] dc = k > 0 ? dcKight : dcKing;
                IntArrayQueue que = new IntArrayQueue(N);
                for (int start = 0; start < N; ++start) {
                    Arrays.fill(dist[start], INF);
                    dist[start][start] = 0;
                    que.clear();
                    que.add(start);
                    while (!que.isEmpty()) {
                        int cur = que.poll();
                        int r = cur / C, c = cur % C;
                        for (int d = 0; d < dr.length; ++d) {
                            int nr = r + dr[d], nc = c + dc[d];
                            int next = nr * C + nc;
                            if (0 <= nr && nr < R && 0 <= nc && nc < C && dist[start][next] == INF) {
                                dist[start][next] = dist[start][cur] + 1;
                                que.add(next);
                            }
                        }
                    }
                }
            }
        }
        Stopwatch.toc();


        Stopwatch.tic();
        int minMoves = Integer.MAX_VALUE;
        for (int target = 0; target < N; ++target) {
            int pking = knights[0];
            if (knights.length <= 1) minMoves = Math.min(minMoves, distKing[pking][target]);
            int move1 = 0;
            int cnt = 0;
            for (int ki = 1; ki < knights.length; ++ki) {
                int d = distKnight[knights[ki]][target];
                if (d >= INF) cnt++;
                if (cnt >= 2) break;
                move1 += d;
            }
            if (cnt >= 2) continue;

            for (int knight = 1; knight < knights.length; ++knight) {
                int pknight = knights[knight];
                int move2 = move1 - distKnight[pknight][target];
                if (move2 >= minMoves) continue;

                for (int meet = 0; meet < N; ++meet) {
                    if (distKing[pking][meet] >= INF || distKnight[pknight][meet] >= INF || distKnight[meet][target] >= INF)
                        continue;
                    int move = distKing[pking][meet] + distKnight[pknight][meet] + distKnight[meet][target] + move2;
                    minMoves = Math.min(minMoves, move);
                }
            }
        }
        Stopwatch.toc();

        out.println(minMoves);
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        R = in.nextInt();
        C = in.nextInt();
        //int king = compress(in.next(), in.nextInt());

        List<Integer> knightsList = new ArrayList<>();
        while (true) {
            if (!in.hasNext()) break;
            knightsList.add((in.next().charAt(0) - 'A') + (in.nextInt() - 1) * C);
        }
        int N = R * C;
        int[][][] distKnight = new int[N][N][2];
        //int[][] distKing = new int[N][N];
        int[] drKnight = new int[]{+2, +1, -1, -2, -2, -1, +1, +2};
        int[] dcKight = new int[]{+1, +2, +2, +1, -1, -2, -2, -1};
        //int[] drKing = new int[]{+1, +1, 0, -1, -1, -1, 0, +1};
        //int[] dcKing = new int[]{0, +1, +1, +1, 0, -1, -1, -1};
        int INF = Integer.MAX_VALUE;

        int pking = knightsList.get(0);
        Stopwatch.tic();
        for (int ki = 1; ki < knightsList.size(); ++ki) {
            int start = knightsList.get(ki);
            //Arrays.fill(distKnight[start], INF);
            for (int i = 0; i < N; ++i) Arrays.fill(distKnight[start][i], INF);
            distKnight[start][start][0] = 0;
            distKnight[start][start][1] = dist(pking, start);
            ArrayQueue<Tuple2<Integer, Integer>> que = new ArrayQueue<>(N * 2);
            que.add(new Tuple2<>(start, 0));
            que.add(new Tuple2<>(start, 1));
            boolean[][] inque = new boolean[N][2];
            inque[start][0] = inque[start][1] = true;
            while (!que.isEmpty()) {
                int curp = que.peek().getFirst();
                int withKing = que.poll().getSecond();
                inque[curp][withKing] = false;
                int r = curp / C, c = curp % C;
                for (int d = 0; d < drKnight.length; ++d) {
                    int nr = r + drKnight[d];
                    int nc = c + dcKight[d];
                    if (!(0 <= nr && nr < R && 0 <= nc && nc < C)) continue;
                    int nextp = nr * C + nc;
                    if (withKing == 0) {
                        int dist0 = distKnight[start][curp][0] + 1;
                        if (dist0 < distKnight[start][nextp][0]) {
                            distKnight[start][nextp][0] = dist0;
                            if (!inque[nextp][0]) {
                                inque[nextp][0] = true;
                                que.add(new Tuple2<>(nextp, 0));
                            }
                        }
                        int dist1 = distKnight[start][curp][0] + 1 + dist(pking, nextp);
                        if (dist1 < distKnight[start][nextp][1]) {
                            distKnight[start][nextp][1] = dist1;
                            if (!inque[nextp][1]) {
                                inque[nextp][1] = true;
                                que.add(new Tuple2<>(nextp, 1));
                            }
                        }
                    } else {
                        int dist1 = distKnight[start][curp][1] + 1;
                        if (dist1 < distKnight[start][nextp][1]) {
                            distKnight[start][nextp][1] = dist1;
                            if (!inque[nextp][1]) {
                                inque[nextp][1] = true;
                                que.add(new Tuple2<>(nextp, 1));
                            }
                        }
                    }
                }
            }
        }
        Stopwatch.toc();

        Stopwatch.tic();
        int minMoves = Integer.MAX_VALUE;
        for (int target = 0; target < N; ++target) {
            int withoutKing = dist(pking, target);
            for (int ki = 1; ki < knightsList.size(); ++ki) withoutKing += distKnight[knightsList.get(ki)][target][0];

            int withKing = Integer.MAX_VALUE;
            for (int ki = 1; ki < knightsList.size(); ++ki) {
                withKing = Math.min(withKing, withoutKing - distKnight[knightsList.get(ki)][target][0] + distKnight[knightsList.get(ki)][target][1]);
            }
            withKing -= dist(pking, target);

            minMoves = Math.min(minMoves, Math.min(withKing, withoutKing));
        }
        Stopwatch.toc();

        out.println(minMoves);
    }

    private int dist(int from, int to) {
        int r1 = from / C, c1 = from % C;
        int r2 = to / C, c2 = to % C;
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

}
