package main;

public class Removal {
    public int finalPos(int n, int k, String[] remove) {
        long pos = k;
        for (int i = remove.length - 1; i >= 0; --i) {
            String[] tmp = remove[i].split("-");
            long lo = Integer.parseInt(tmp[0]);
            long hi = Integer.parseInt(tmp[1]);
            long ppos = pos;
            if (lo <= pos)
                ppos = pos + hi - lo + 1;
            pos = ppos;
        }
        return (int)(pos > n ? -1 : pos);
    }
}
