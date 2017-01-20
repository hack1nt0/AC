package template.collection.sequence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dy on 17-1-13.
 *
 * todo involve Knuth's optim.
 */
public class KMP<T extends Comparable> extends StringSearch<T> {
    int[] back;
    T[] pattern;

    public KMP(T[] pattern) {
        assert pattern != null && pattern.length > 0;
        this.pattern = pattern;
        int N = pattern.length;
        back = new int[N + 1];
        back[0] = 0;
        if (N == 1) return;

        back[1] = 0;
        for (int i = 2; i <= N; ++i) {
            int b = back[i - 1];
            while (true) {
                if (b == 0 || pattern[i - 1].compareTo(pattern[b]) == 0) break;
                b = back[b];
            }

            if (pattern[i - 1].compareTo(pattern[b]) == 0)
                back[i] = b + 1;
            else
                back[i] = b;
            //avoid loop
            if (back[i] == i) back[i] = i - 1;
        }

    }

    public List<Occurrence> search(T[] text) {
        List<Occurrence> res = new ArrayList<>();
        int pp = 0;
        for (int pt = 0; pt < text.length; ++pt) {
            while (true) {
                if (pp == 0 || text[pt].compareTo(pattern[pp]) == 0) break;
                pp = back[pp];
            }
            if (text[pt].compareTo(pattern[pp]) == 0)
                pp++;
            else if (pp == 0)
                continue;

            if (pp == pattern.length) {
                res.add(new Occurrence(pt + 1 - pattern.length, pt + 1));
                pp = back[pattern.length];
            }
        }

        return res;
    }

    public Iterator<Occurrence> searchItr(T[] text) {
        return new Iterator<Occurrence>() {
            private Occurrence res;
            private int pp, pt;
            @Override
            public boolean hasNext() {
                for (;pt < text.length; ++pt) {
                    while (true) {
                        if (pp == 0 || text[pt].compareTo(pattern[pp]) == 0) break;
                        pp = back[pp];
                    }
                    if (text[pt].compareTo(pattern[pp]) == 0)
                        pp++;
                    else if (pp == 0)
                        continue;

                    if (pp == pattern.length) {
                        res = new Occurrence(pt + 1 - pattern.length, pt + 1);
                        pp = back[pattern.length];
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
