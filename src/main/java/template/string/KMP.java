package template.string;

import java.util.*;

/**
 * Created by dy on 17-1-13.
 *
 * todo involve Knuth's optim.
 */
public class KMP extends PatternSearch {
    int[] back;
    String pattern;

    public KMP(String pattern) {
        assert pattern != null && pattern.length() > 0;
        this.pattern = pattern;
        int N = pattern.length();
        back = new int[N + 1];
        back[0] = 0;
        if (N == 1) return;

        back[1] = 0;
        for (int i = 2; i <= N; ++i) {
            int b = back[i - 1];
            while (true) {
                if (b == 0 || pattern.charAt(i - 1) == pattern.charAt(b)) break;
                b = back[b];
            }

            if (pattern.charAt(i - 1) == pattern.charAt(b))
                back[i] = b + 1;
            else
                back[i] = b;
            //avoid loop
            if (back[i] == i) back[i] = i - 1;
        }

    }

    public List<Occurrence> search(String text) {
        List<Occurrence> res = new ArrayList<>();
        int pp = 0;
        for (int pt = 0; pt < text.length(); ++pt) {
            while (true) {
                if (pp == 0 || text.charAt(pt) == pattern.charAt(pp)) break;
                pp = back[pp];
            }
            if (text.charAt(pt) == pattern.charAt(pp))
                pp++;
            else if (pp == 0)
                continue;

            if (pp == pattern.length()) {
                res.add(new Occurrence(pt + 1 - pattern.length(), pt + 1));
                pp = back[pattern.length()];
            }
        }

        return res;
    }

    public Iterator<Occurrence> searchItr(String text) {
        return new Iterator<Occurrence>() {
            private Occurrence res;
            private int pp, pt;
            @Override
            public boolean hasNext() {
                for (;pt < text.length(); ++pt) {
                    while (true) {
                        if (pp == 0 || text.charAt(pt) == pattern.charAt(pp)) break;
                        pp = back[pp];
                    }
                    if (text.charAt(pt) == pattern.charAt(pp))
                        pp++;
                    else if (pp == 0)
                        continue;

                    if (pp == pattern.length()) {
                        res = new Occurrence(pt + 1 - pattern.length(), pt + 1);
                        pp = back[pattern.length()];
                        return true;
                    }
                }
                return true;
            }

            @Override
            public Occurrence next() {
                return res;
            }
        };
    }

}
