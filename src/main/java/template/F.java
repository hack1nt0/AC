package template;

import java.lang.reflect.Constructor;
import java.util.*;

/**
 * Created by dy on 16-12-22.
 *
 * Functional Programming Thing
 */

public class F {

    public static abstract class Lambda1<T1, T2> {
        public void igo(T1 a) {
            throw new RuntimeException("Need to be implemented.");
        }
        public T2 go(T1 a) {
            throw new RuntimeException("Need to be implemented.");
        }
    }

    public static abstract class Lambda2<T1, T2, T3> {
        public void igo(T1 a, T2 b) {
            throw new RuntimeException("Need to be implemented.");
        };
        public T3 go(T1 a, T2 b) {
            throw new RuntimeException("Need to be implemented.");
        }
    }

    public final static Lambda2 addInt = new Lambda2<Integer, Integer, Integer>() {
        @Override
        public Integer go(Integer a, Integer b) {
            return a + b;
        }
    };

    public final static class addLong extends Lambda2<Long, Long, Long> {
        @Override
        public Long go(Long a, Long b) {
            return a + b;
        }
    }

    public final static class addFloat extends Lambda2<Float, Float, Float> {
        @Override
        public Float go(Float a, Float b) {
            return a + b;
        }
    }

    public final static class addDouble extends Lambda2<Double, Double, Double> {
        @Override
        public Double go(Double a, Double b) {
            return a + b;
        }
    }

    public static <T1, T2> ArrayList<T2> map(ArrayList<T1> lst, Lambda1<T1, T2> f) {
        ArrayList<T2> res = new ArrayList<T2>();
        for (T1 e : lst) res.add(f.go(e));
        return res;
    }
    public static <T1, T2> void imap(Collection<T1> lst, Lambda1<T1, T2> f) {
        for (T1 e : lst) f.igo(e);
    }

//    public static <T1, T2> T2[] map(T1[] arr, Lambda1<T1, T2> f) {
//        T2[] res = new T2[arr.length];
//
//        for (T1 e : lst) res.add(f.go(e));
//        return res;
//    }

    public static <T1, T2> void imap(T1[] arr, Lambda1<T1, T2> f) {
        for (T1 e : arr) f.igo(e);
    }

    public static <T1, T2> T1 fold(T1 init, List<T2> lst, Lambda2<T1, T2, T1> f) {
        for (T2 e : lst) init = f.go(init, e);
        return init;
    }

    public static <T1, T2> void ifold(T1 init, List<T2> lst, Lambda2<T1, T2, Object> f) {
        for (T2 e : lst) f.igo(init, e);
    }

    public static void main(String[] args) {
        System.out.println(fold(0, Arrays.asList(1, 2, 3), addInt));
    }
}
