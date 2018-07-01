package main;

public class TennisSet {
    public String firstSet(String[] points) {
        int[] pscore = new int[2];
        int[] gscore = new int[2];
        int server = 0;
        StringBuilder tot = new StringBuilder();
        for (String s : points) tot.append(s);
        for (int i = 0; i < tot.length(); ++i) {
            if (tot.charAt(i) == 'S')
                pscore[server]++;
            else
                pscore[server ^ 1]++;
            boolean pfinished = false;
            for (int p = 0; p < 2; ++p)
                if (pscore[p] >= 4 && pscore[p] - pscore[p ^ 1] >= 2) {
                    gscore[p]++;
                    pscore[p] = pscore[p ^ 1] = 0;
                    pfinished = true;
                    break;
                }
            if (pfinished) {
                server ^= 1;
                boolean gfinished = false;
                for (int p = 0; p < 2; ++p)
                    if (gscore[p] >= 6 && gscore[p] - gscore[p ^ 1] >= 2) {
                        gfinished = true;
                        break;
                    }
                if (gfinished)
                    break;
            }
        }
        return gscore[0] + "-" + gscore[1];
    }
}
