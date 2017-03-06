package main;

import template.collection.tuple.Tuple2;
import template.linear_algebra.MatrixUtils;

import java.util.*;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: starry
*/

public class Starry {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        char[][] sky = new char[n][m];
        boolean[][] visited = new boolean[n][m];
        char[][] ans = new char[n][m];
        for (int i = 0; i < n; ++i) sky[i] = in.next().toCharArray();
        int[] di = new int[]{-1, +1, 0, 0, -1, +1, -1, +1};
        int[] dj = new int[]{0, 0, -1, +1, -1, +1, +1, -1};
        char letter = 'a';
        Map<Cluster, Character> clusterMap = new HashMap<>();
        for (int lui = 0; lui < n; ++lui) {
            for (int luj = 0; luj < m; ++luj) {
                if (sky[lui][luj] != '1') continue;
                List<Tuple2<Integer, Integer>> que = new ArrayList<>();
                que.add(new Tuple2<>(lui, luj));
                int pQue = 0;
                while (pQue < que.size()) {
                    int i = que.get(pQue).getFirst();
                    int j = que.get(pQue).getSecond();
                    for (int d = 0; d < di.length; ++d) {
                        int ni = i + di[d];
                        int nj = j + dj[d];
                        if (0 <= ni && ni < n && 0 <= nj && nj < m && sky[ni][nj] == '1' && !visited[ni][nj]) {
                            visited[ni][nj] = true;
                            que.add(new Tuple2(ni, nj));
                        }
                    }
                    pQue++;
                }

                int minI = Integer.MAX_VALUE, minJ = Integer.MAX_VALUE;
                int maxI = Integer.MIN_VALUE, maxJ = Integer.MIN_VALUE;
                for (Tuple2<Integer, Integer> ij : que) {
                    int i = ij.getFirst(), j = ij.getSecond();
                    minI = Math.min(minI, i);
                    minJ = Math.min(minJ, j);
                    maxI = Math.max(maxI, i);
                    maxJ = Math.max(maxJ, j);
                }
                int h = maxI - minI + 1, w = maxJ - minJ + 1;
                int[][] cluster = new int[h][w];
                for (Tuple2<Integer, Integer> ij : que) {
                    int i = ij.getFirst() - minI, j = ij.getSecond() - minJ;
                    cluster[i][j] = 1;
                }
                Cluster cls = new Cluster(cluster);
                if (!clusterMap.containsKey(cls)) {
                    clusterMap.put(cls, letter++);
                }
                char curLetter = clusterMap.get(cls);
                for (Tuple2<Integer, Integer> ij : que) {
                    int i = ij.getFirst(), j = ij.getSecond();
                    sky[i][j] = curLetter;
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) out.print(sky[i][j]);
            out.println();
        }
    }

    static class Cluster {
        int[][] sky;
        int stars;

        public Cluster(int[][] sky) {
            this.sky = sky;
            for (int i = 0; i < sky.length; ++i) for (int j = 0; j < sky[i].length; ++j) if (sky[i][j] == 1) stars++;
        }

        public List<int[][]> transform() {
            List<int[][]> ans = new ArrayList<>();
            ans.add(sky);
            for (int i = 0; i < 3; ++i) ans.add(MatrixUtils.rotate90Right(ans.get(ans.size() - 1)));
            for (int i = 0; i < 4; ++i) ans.add(MatrixUtils.mirrorHorizontally(ans.get(i)));
            for (int i = 0; i < 4; ++i) ans.add(MatrixUtils.mirrorVertically(ans.get(i)));
            return ans;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Cluster cluster = (Cluster) o;

            if (stars != cluster.stars) return false;
            for (int[][] sky : cluster.transform()) if (Arrays.deepEquals(this.sky, sky)) return true;
            return false;
        }

        @Override
        public int hashCode() {
            int result = 0;
            result = 31 * result + stars;
            int h = Math.min(sky.length, sky[0].length);
            int w = Math.max(sky.length, sky[0].length);
            result = 31 * result + h * w;
            return result;
        }
    }

    public static void main(String[] args) {
        int[][] m = new int[][] {
                {1, 1, 1, 0},
                {1, 0, 0, 1},
                {1, 0, 0, 0}};
        for (int[][] mm : new Cluster(m).transform()) {
            for (int i = 0; i < mm.length; ++i) {
                for (int j = 0; j < mm[i].length; ++j) System.out.print(mm[i][j]);
                System.out.println();
            }
            System.out.println();
        }
    }
}
