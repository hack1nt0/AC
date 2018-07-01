package main;

import template.debug.InputReader;
import template.debug.OutputWriter;

import java.util.*;

//WA
public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int nRow = 4;
        int nCol = in.readInt();
        int nCar = in.readInt();
        int[][] park = new int[4][nCol];
        for (int i = 0; i < 4; ++i)
            for (int j = 0; j < nCol; ++j)
                park[i][j] = in.readInt();
        Car[] cars = new Car[nCar];
        for (int ic = 1; ic <= nCar; ++ic) {
            int sr = -1;
            int sc = -1;
            int tr = -1;
            int tc = -1;
            for (int i = 0; i < 4; ++i)
                for (int j = 0; j < nCol; ++j)
                    if (park[i][j] == ic) {
                        if (i == 0 || i == 3) {
                            tr = i; tc = j;
                        } else {
                            sr = i; sc = j;
                        }
                    }
            cars[ic - 1] = new Car(sr, sc, tr, tc);
        }
        int[] dr = {-1, +1, 0, 0};
        int[] dc = {0, 0, -1, +1};
        int min = 0;
        int left = nCar;
        List<String> moves = new ArrayList<>();
        while (true) {
            if (left == 0)
                break;
            if (min > (int) 2e4)
                break;
            Queue<State> queue = new LinkedList<>();
            for (int ic = 0; ic < nCar; ++ic)
                if (!cars[ic].ok)
                    queue.add(new State(ic, cars[ic].sr, cars[ic].sc, 0, null));
            Car minCar = null;
            boolean[][][] visited = new boolean[nCar][nRow][nCol];
            while (queue.size() > 0) {
                State top = queue.poll();
                int cr = top.r, cc = top.c, ic = top.which;
                visited[ic][cr][cc] = true;
                Car car = cars[top.which];
                if (cr == car.tr && cc == car.tc) {
                    minCar = car;
                    min += top.moves;
                    List<String> curMoves = new ArrayList<>();
                    State cur = top;
                    while (cur.pre != null) {
                        curMoves.add("" + (top.which + 1) + " " + (cur.r + 1) + " " + (cur.c + 1));
                        cur = cur.pre;
                    }
                    for (int i = curMoves.size() - 1; i >= 0; --i)
                        moves.add(curMoves.get(i));
                    park[minCar.sr][minCar.sc] = 0;
                    minCar.ok = true;
                    left--;
                    break;
                }
                if (cr == 0 || cr == 3)
                    continue;
                for (int d = 0; d < dr.length; ++d) {
                    int nr = top.r + dr[d];
                    int nc = top.c + dc[d];
                    if (0 <= nr && nr < nRow && 0 <= nc && nc < nCol && (park[nr][nc] == 0 || park[nr][nc] == ic + 1) && !visited[ic][nr][nc])
                        queue.add(new State(top.which, nr, nc, top.moves + 1, top));
                }
            }
            if (minCar == null)
                break;
        }
        if (left == 0 && min <= (int) 2e4) {
            out.printLine(min);
            for (String move : moves)
                out.printLine(move);
        } else
            out.printLine(-1);
    }

    class Car {
        int sr, sc, tr, tc;
        boolean ok;

        public Car(int sr, int sc, int tr, int tc) {
            this.sr = sr;
            this.sc = sc;
            this.tr = tr;
            this.tc = tc;
        }
    }

    class State {
        int which, r, c, moves;
        State pre;

        public State(int which, int r, int c, int moves, State pre) {
            this.which = which;
            this.r = r;
            this.c = c;
            this.moves = moves;
            this.pre = pre;
        }
    }
}
