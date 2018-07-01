package main;

import template.collection.tuple.Tuple2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Amoebae {
    class Amoeba {
        boolean[][] map;

        public Amoeba(List<Tuple2<Integer, Integer>> indexes) {
            int rmin = 50, rmax = 0;
            int cmin = 50, cmax = 0;
            for (Tuple2<Integer, Integer> t : indexes) {
                rmin = Math.min(rmin, t.getFirst());
                rmax = Math.max(rmax, t.getFirst());
                cmin = Math.min(cmin, t.getSecond());
                cmax = Math.max(cmax, t.getSecond());
            }
            map = new boolean[rmax - rmin + 1][cmax - cmin + 1];
            for (Tuple2<Integer, Integer> t : indexes) {
                map[t.getFirst() - rmin][t.getSecond() - cmin] = true;
            }
        }

        public Amoeba(boolean[][] map) {
            this.map = map;
        }

        public boolean equals(Amoeba o) {
            for (int rot = 0; rot < 4; ++rot) {
                for (int ref = 0; ref < 2; ++ref) {
                    for (boolean href : new boolean[]{false, true}) {
                        for (boolean vref : new boolean[]{false, true}) {
                            boolean[][] map = o.rotate(rot);
                            if (href) map = o.refection(map, 1);
                            if (vref) map = o.refection(map, 0);
                            if (Arrays.deepEquals(map, this.map))
                                return true;
                        }
                    }
                }
            }
            return false;
        }

        public boolean[][] rotate(int degree) {
            boolean[][] ans = map;
            int n = map.length, m = ans[0].length;
            for (int d = 0; d < degree; ++d) {
                int nn = m, nm = n;
                boolean[][] nans = new boolean[nn][nm];
                for (int r = 0; r < n; ++r)
                    for (int c = 0; c < m; ++c)
                        nans[c][nm - r - 1] = ans[r][c];
                ans = nans;
                n = nn;
                m = nm;
            }
            return ans;
        }

        public boolean[][] refection(boolean[][] old, int axis) {
            int n = old.length;
            int m = old[0].length;
            boolean[][] ans = new boolean[n][m];
            for (int r = 0; r < n; ++r) {
                for (int c = 0; c < m; ++c) {
                    int tr = axis == 0 ? r : n - r - 1;
                    int tc = axis == 0 ? m - c - 1 : c;
                    ans[tr][tc] = old[r][c];
                }
            }
            return ans;
        }

        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();
            int n = map.length, m = map[0].length;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    sb.append(map[i][j] ? 'X' : '.');
                }
                sb.append(System.lineSeparator());
            }
            return sb.toString();
        }
    }

    int n, m;
    public int cultureX(String[] known, String[] candidate) {
        n = known.length;
        m = known[0].length();
        List<Amoeba>[] amoebas = new ArrayList[2];
        for (int i = 0; i < 2; ++i) {
            amoebas[i] = new ArrayList<>();
            String[] culture = i == 0 ? known : candidate;
            boolean[][] visited = new boolean[n][m];
            for (int r = 0; r < n; ++r) {
                for (int c = 0; c < m; ++c) {
                    if (!visited[r][c] && culture[r].charAt(c) == 'X') {
                        List<Tuple2<Integer, Integer>> indexes = new ArrayList<>();
                        dfs(r, c, indexes, visited, culture);
                        amoebas[i].add(new Amoeba(indexes));
                    }
                }
            }
//            for (int j = 0; j < amoebas[i].size(); ++j)
//                System.out.println(amoebas[i].get(j));
        }
        int ans = amoebas[0].size() + amoebas[1].size();
        boolean[] visited = new boolean[amoebas[1].size()];
        for (int from = 0; from < amoebas[0].size(); ++from) {
            for (int to = 0; to < amoebas[1].size(); ++to) {
                if (visited[to])
                    continue;
                if (amoebas[0].get(from).equals(amoebas[1].get(to))) {
                    ans -= 2;
                    visited[to] = true;
                    break;
                }
            }
        }
        return ans;
    }

    int[] dr = {-1, +1, 0, 0};
    int[] dc = {0, 0, -1, +1};

    private void dfs(int r, int c, List<Tuple2<Integer, Integer>> indexes, boolean[][] visited, String[] culture) {
        visited[r][c] = true;
        indexes.add(new Tuple2<>(r, c));
        for (int d = 0; d < dr.length; ++d) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (0 <= nr && nr < n && 0 <= nc && nc < m && culture[nr].charAt(nc) == 'X' && !visited[nr][nc]) {
                dfs(nr, nc, indexes, visited, culture);
            }
        }
    }
}
