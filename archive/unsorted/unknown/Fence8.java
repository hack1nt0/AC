package main;

import template.debug.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;
/*
 ID: hackint1
 LANG: JAVA
 TASK: fence8
*/
public class Fence8 {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int nBoards = in.readInt();
        int[] boards = new int[nBoards];
        for (int i = 0; i < nBoards; ++i) boards[i] = in.readInt();
        Arrays.sort(boards);
        int nRails = in.readInt();
        int[] rails = new int[nRails];
        for (int i = 0; i < nRails; ++i) rails[i] = in.readInt();
        Arrays.sort(rails);
        int dep = 0;
        for (dep = 1; dep <= nRails; ++dep) {
            int[] tmpBoards = boards.clone();
            int[] tmpRails = Arrays.copyOfRange(rails, 0, dep);
//            for (int r = 0; r < tmpRails.length; ++r) {
//                int matchedBoardIdx = Arrays.binarySearch(tmpBoards, tmpRails[r]);
//                if (matchedBoardIdx >= 0) {
//                    tmpBoards[matchedBoardIdx] = 0;
//                    tmpRails[r] = 0;
//                }
//            }
            int sumBoards = 0;
            for (int b: tmpBoards) sumBoards += b;
            int sumRails = 0;
            for (int r: tmpRails) sumRails += r;
            if (sumRails > sumBoards) break;
            Arrays.sort(tmpBoards);
            Arrays.sort(tmpRails);
            if (!dfs(tmpRails.length - 1, tmpRails, tmpBoards, tmpBoards.length - 1, sumBoards - sumRails)) break;
            System.out.println(dep);
        }
        out.println(dep - 1);
    }

    private boolean dfs(int cur, int[] rails, int[] boards, int preMatchedBoard, int mustWasted) {
        if (cur < 0) return true;
        int accWasted = 0;
        for (int b = 0; b < boards.length; ++b) if (boards[b] < rails[0]) accWasted += boards[b];
        if (accWasted > mustWasted) return false;
        int b = cur < rails.length - 1 && rails[cur] == rails[cur + 1] ? preMatchedBoard : boards.length - 1;
        for (; b >= 0; --b) if (rails[cur] <= boards[b]) {
            boards[b] -= rails[cur];
            if (dfs(cur - 1, rails, boards, b, mustWasted)) return true;
            boards[b] += rails[cur];
        }
        return false;
    }
}
