package template.collection.tuple;

import java.util.Comparator;

/**
 * Created by dy on 17-1-15.
 */
public class Tuple2<T extends Comparable, U extends Comparable> {
    T first;
    U second;


    public static final Comparator<Tuple2> FIRST_ELEMENT_ORDER = new FirstComparator();
    public static final Comparator<Tuple2> SENCOND_ELEMENT_ORDER = new SecondComparator();


    public Tuple2(T first, U second) {
        this.first = first;
        this.second = second;
    }
    private static class FirstComparator implements Comparator<Tuple2> {
        public int compare(Tuple2 a, Tuple2 b) {
            int firstcmp = a.first.compareTo(b.first);
            if (firstcmp != 0) return firstcmp;
            int secondcmp = a.second.compareTo(b.second);
            if (secondcmp != 0) return secondcmp;
            return 0;
        }
    }

    private static class SecondComparator implements Comparator<Tuple2> {
        public int compare(Tuple2 a, Tuple2 b) {
            int secondcmp = a.second.compareTo(b.second);
            if (secondcmp != 0) return secondcmp;
            int firstcmp = a.first.compareTo(b.first);
            if (firstcmp != 0) return firstcmp;
            return 0;
        }
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }
}
