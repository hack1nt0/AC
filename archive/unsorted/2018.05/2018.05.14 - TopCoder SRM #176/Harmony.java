package main;

import template.collection.CollectionUtils;
import template.collection.sequence.ArrayUtils;
import template.collection.sets.BitUtils;
import template.numbers.IntUtils;

import java.util.*;

public class Harmony {
    class Chord implements Comparable<Chord> {
        int[] notes;
        int[] denos;

        Chord(Collection<Integer> notes) {
            this.notes = ArrayUtils.unbox(notes.toArray(new Integer[0]));
        }

        int[] denominators() {
            if (denos != null)
                return denos;
            List<Integer> ans = new ArrayList<>();
            for (int S = 0; S < 1 << 3; ++S) {
                if (Integer.bitCount(S) != 2)
                    continue;
                int[] xs = new int[2];
                int pos = 0;
                for (int i = 0; i < 3; ++i) {
                    if ((S >> i & 1) != 0)
                        xs[pos++] = notes[i];
                }
                long tmp = IntUtils.gcd(xs[0], xs[1]);
                ans.add((int)Math.min(xs[0] / tmp, xs[1] / tmp));
            }
            Integer[] array = ans.toArray(new Integer[0]);
            Arrays.sort(array, Collections.reverseOrder());
            this.denos = ArrayUtils.unbox(array);
            return denos;
        }

        @Override
        public int compareTo(Chord o) {
            int[] deno1 = this.denominators();
            int[] deno2 = o.denominators();
            int compDeno = ArrayUtils.compare(deno1, deno2);
            if (compDeno != 0)
                return compDeno;
            else
                return ArrayUtils.compare(this.notes, o.notes);
        }

        @Override
        public String toString() {
            return Arrays.toString(notes);
        }
    }

    int tot = 0;
    public int[] mostHarmonious(int[] frequencies) {
        Arrays.sort(frequencies);
        Chord min = null;
        for (long S : BitUtils.kSubset(frequencies.length, 3)) {
            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < frequencies.length; ++i) {
                if ((S >> i & 1) != 0)
                    ans.add(frequencies[i]);
            }
            Chord chord = new Chord(ans);
            if (min == null || chord.compareTo(min) < 0)
                min = chord;
        }
        return min.notes;
    }
}
