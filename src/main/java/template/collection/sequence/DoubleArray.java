//package template.collection.sequence;
//
///**
// * @author dy[jealousing@gmail.com] on 17-4-29.
// */
//public interface DoubleArray {
//    int length();
//    double get(int i);
//
//    static DoubleArray s(double[] xs, int s, int t) {
//        return new DoubleArray() {
//            @Override
//            public int length() {
//                return t - s;
//            }
//
//            @Override
//            public double get(int i) {
//                return xs[i + s];
//            }
//        };
//    }
//
//    static DoubleArray s(double[] xs) { return s(xs, 0, xs.length); }
//
//    default DoubleArray subArray(int s, int t) {
//        return subArray(this, s, t);
//    }
//
//    default DoubleArray subArray(DoubleArray parent, int s, int t) {
//        return new DoubleArray() {
//            @Override
//            public int length() {
//                return t - s;
//            }
//
//            @Override
//            public double get(int i) {
//                return parent.get(i + s);
//            }
//        };
//    }
//
//}
