package main;

public class FairWorkload {
    public int getMostWork(int[] folders, int workers) {
        int l = 0, r = 0;
        for (int i = 0; i < folders.length; ++i)
            r += folders[i];
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            boolean good = true;
            int nsection = workers;
            for (int i = 0; i < folders.length;) {
                if (nsection == 1) {
                    good &= sum(folders, i) <= mid;
                    break;
                } else {
                    int nfile = 0;
                    while (i < folders.length && nfile + folders[i] <= mid)
                        nfile += folders[i++];
                    --nsection;
                }
            }
            if (good)
                r = mid;
            else
                l = mid;
        }
        return r;
    }

    int sum(int[] f, int from) {
        int res = 0;
        for (int i = from; i < f.length; ++i)
            res += f[i];
        return res;
    }
}
