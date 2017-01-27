package template.numbers;

import java.math.BigInteger;

/**
 * Created by dy on 16-10-8.
 */
public class Rational implements Comparable<Rational> {
        long x, y;

        public Rational(long x) {
            this.x = x;
            this.y = 1;
        }

        public Rational(long x, long y) {
            if (y == 0) throw new IllegalArgumentException();
            long d = IntegerUtils.gcd(x, y);
            this.x = x / d;
            this.y = y / d;
        }

        public Rational add(Rational that) {
            //if (x == 0 && b.x == 0) return new Real(0, 1);
            long x = this.x * that.y + that.x * this.y;
            long y = this.y * that.y;
            return new Rational(x, y);
        }

        @Override
        public String toString() {
            if (y < 0) {
                x = -x;
                y = -y;
            }
            return x + "/" + y;
        }

    @Override
    public int compareTo(Rational that) {
            long cmp = this.x * that.y - that.x * this.y;
            if (cmp > 0) return +1;
            if (cmp < 0) return -1;
            return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rational rational = (Rational) o;

        if (x != rational.x) return false;
        return y == rational.y;
    }

    @Override
    public int hashCode() {
        int result = (int) (x ^ (x >>> 32));
        result = 31 * result + (int) (y ^ (y >>> 32));
        return result;
    }
}