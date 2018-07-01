package main;

public class Cubism {
    public int lines(String[] lattice, String color) {
        int n = 4;
        char[][][] cube = new char[n][n][n];
        for (int z = 0; z < n; ++z) {
            for (int y = 0; y < n; ++y) {
                for (int x = 0; x < n; ++x) {
                    cube[x][y][z] = lattice[z].charAt(y * n + x);
                }
            }
        }
        char target = color.equals("black") ? 'B' : 'W';
        int ans = 0;
        for (int z = 0; z < n; ++z) {
            for (int y = 0; y < n; ++y) {
                for (int x = 0; x < n; ++x) {
                    for (int dx = -1; dx <= 1; ++dx) {
                        for (int dy = -1; dy <= 1; ++dy) {
                            for (int dz = -1; dz <= 1; ++dz) {
                                if (dx == 0 && dy == 0 && dz == 0) continue;
                                boolean good = true;
                                int nx = x;
                                int ny = y;
                                int nz = z;
                                int i = 0;
                                do {
                                    if (valid(nx) && valid(ny) && valid(nz) && cube[nx][ny][nz] == target) {
                                        nx += dx;
                                        ny += dy;
                                        nz += dz;
                                        ++i;
                                    } else {
                                        good = false;
                                        break;
                                    }
                                } while (i < n);
                                if (good)
                                    ++ans;
                            }
                        }
                    }
                }
            }
        }
        return ans / 2;
    }

    private boolean valid(int x) {
        return 0 <= x && x < 4;
    }
}
