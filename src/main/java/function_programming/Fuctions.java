package function_programming;

import java.util.*;

/**
 * Created by dy on 16-12-22.
 *
 * Functional Programming Thing
 */

public class Fuctions {

    public final static Lambda3 addInt = new Lambda3<Integer, Integer, Integer>() {
        @Override
        public Integer apply(Integer a, Integer b) {return a + b;}
    };

    public final static Lambda3 addLong = new Lambda3<Long, Long, Long>() {
        @Override
        public Long apply(Long a, Long b) {return a + b;}
    };

    public final static Lambda3 addFloat = new Lambda3<Float, Float, Float>() {
        @Override
        public Float apply(Float a, Float b) {
            return a + b;
        }
    };

    public final static Lambda3 addDouble = new Lambda3<Double, Double, Double>() {
        @Override
        public Double apply(Double a, Double b) {
            return a + b;
        }
    };
}
