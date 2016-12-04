package template;

import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dy on 16-12-3.
 */
public class Bits implements Iterable<List<Integer>> {
    BitSet ibitset;

    public Bits(int nbits) {
        ibitset = new BitSet(nbits);
    }

    //power set
    public Iterator<List<Integer>> iterator() {
        return new Iterator<List<Integer>>() {

            public boolean hasNext() {
                return false;
            }

            public List<Integer> next() {
                return null;
            }

            public void remove() {

            }
        };
    }
}
