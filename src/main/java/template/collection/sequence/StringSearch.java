package template.collection.sequence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dy on 17-1-13.
 *
 * Usage proposed: [start, isString)
 */
public abstract class StringSearch<T extends Comparable> {
    public abstract List<Occurrence> search(T[] text);
    public abstract Iterator<Occurrence> searchItr(T[] text);

    public static List<Occurrence> search(String text, String pattern) {
        List<Occurrence> res = new ArrayList<>();
        for (int i = 0; i + pattern.length() <= text.length();) {
            int p = text.indexOf(pattern, i);
            if (p == -1) {
                i++;
            } else {
                res.add(new Occurrence(p, p + pattern.length()));
                i = p + 1;
            }
        }
        return res;
    }

    static class Occurrence {
        int start, end;

        public Occurrence(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        @Override
        public String toString() {
            return "Occurrence{" +
                    "start=" + start +
                    ", isString=" + end +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Occurrence that = (Occurrence) o;

            if (start != that.start) return false;
            return end == that.end;

        }

        @Override
        public int hashCode() {
            int result = start;
            result = 31 * result + end;
            return result;
        }
    }

    /**
     * unit tests
     */
    static List<Occurrence> ans2 = null;
    static List<Occurrence> ans1 = null;
    static StringSearch stringSearch;
    public static void main(String[] args) {

//        while (true) {
//            String pattern = StringUtils.random(1000, 'a', 'z' + 1);
//            String text = StringUtils.random(150000, 'a', 'z' + 1);
//            new StopWatch() {{
//                ans2 = PatternSearch.search(text, pattern);
//            }}.toc();
//
//            //if (ans2.size() == 0) continue;
//            new StopWatch(){{
//                stringSearch = new KMP(pattern);
//                ans1 = stringSearch.search(text);
//            }}.toc();
//
//            Out.printlnV(pattern, text);
//            Out.printlnV(ans1, ans2);
//            if (!ans1.equals(ans2)) {
//                //PatternSearch.search(text, pattern);
//                //stringSearch.search(text);
//                throw new RuntimeException();
//
//            }
//        }
    }
}
