package main;

import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: rockers
*/

public class Rockers {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int nSong = in.nextInt();
        int lenDisk = in.nextInt();
        int nDist = in.nextInt();
        int[] lenSong = new int[nSong];
        for (int i = 0; i < nSong; ++i) lenSong[i] = in.nextInt();

        int[][][] maxSongs = new int[nSong + 1][nDist + 1][lenDisk + 1];
        for (int song = 0; song < nSong; ++song) {
            for (int disk = nDist - 1; disk >= 0; --disk) {
                for (int len = 0; len <= lenDisk; ++len) {
                    int res = maxSongs[song][disk][len];
                    if (len < lenSong[song]) res = Math.max(res, maxSongs[song + 1][disk + 1][lenDisk]);
                    else res = Math.max(res, maxSongs[song][disk][len - lenSong[song]] + 1);
                    maxSongs[song + 1][disk][len] = res;
                }
            }
        }

        out.println(maxSongs[nSong][0][lenDisk]);
    }
}
