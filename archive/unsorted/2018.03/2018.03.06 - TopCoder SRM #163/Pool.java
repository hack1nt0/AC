package main;

public class Pool {
    public int rackMoves(int[] triangle) {
        int n = 15;
        int[][] target = {
                {1, -1, -1, 1, 0, 1, -1, 1, -1, 1, 1, -1, 1, -1, -1},
                {}};
        target[1] = new int[n];
        for (int i = 0; i < n; ++i) target[1][i] = -target[0][i];
        int ans = Integer.MAX_VALUE;
        for (int t = 0; t < 2; ++t) {
            int mistakes = 0;
            for (int i = 0; i < n; ++i) {
                int encode = triangle[i] < 8 ? -1 : (triangle[i] == 8 ? 0 : +1);
                if (encode != target[t][i]) mistakes++;
            }
            ans = Math.min(ans, (mistakes + 1) / 2);
            if (mistakes % 2 == 1) {
                if (mistakes < 3) throw new RuntimeException();
            }
        }
        return ans;
    }
}
