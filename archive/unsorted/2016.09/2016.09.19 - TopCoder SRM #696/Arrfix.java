package main;

public class Arrfix {
    public int mindiff(int[] A, int[] B, int[] F) {

        int validop = 0;
        boolean[] vis = new boolean[A.length];
        for (int f: F) {
            boolean ok = false;
            for (int i = 0; i < A.length; ++i)
                if (B[i] == f && A[i] != f && !vis[i]) {
                    vis[i] = true;
                    ok = true;
                    A[i] = f;
                    break;
                }
            if (!ok) {
                for (int i = 0; i < A.length; ++i)
                    if (B[i] == f && !vis[i]) {
                        vis[i] = true;
                        ok = true;
                        break;
                    }
            }
            if (ok) validop++;
        }
        int ans = 0;
        for (int i = 0; i < A.length; ++i)
            if (A[i] != B[i]) ans++;
        ans = Math.max(ans, F.length - validop);
        return ans;
    }
}
