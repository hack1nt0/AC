package main;

public class Stairs {
    public int designs(int maxHeight, int minWidth, int totalHeight, int totalWidth) {
        // (n + 1) * h = totalH
        // h = totalH / (n + 1) <= maxHeight
        // n * w = totalW
        // w = totalW / n >= minWidth
        maxHeight = Math.min(maxHeight, totalHeight);
        int lb = (int)(Math.ceil((double)totalHeight / maxHeight) - 1);
        lb = Math.max(1, lb);
        int ub = (int)((double)totalWidth / minWidth);
        int ans = 0;
        for (int n = lb; n <= ub; ++n) {
            if (totalHeight % (n + 1) == 0 && totalWidth % n == 0)
                ans++;
        }
        return ans;
    }
}
