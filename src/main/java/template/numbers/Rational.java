package template.numbers;

import java.math.BigInteger;

/**
 * Created by dy on 16-10-8.
 */
public class Rational {
        BigInteger x, y;

        public Rational(int x) {
            this.x = BigInteger.valueOf(x);
            y = BigInteger.ONE;
        }

        public Rational(int x, int y) {
            this.x = BigInteger.valueOf(x);
            this.y = BigInteger.valueOf(y);
            BigInteger d = this.x.gcd(this.y);
            if (d.compareTo(BigInteger.ZERO) != 0) {
                this.x = this.x.divide(d);
                this.y = this.y.divide(d);
            }
        }

        public Rational(BigInteger x, BigInteger y) {
            this.x = x; this.y = y;
            BigInteger d = this.x.gcd(this.y);
            if (d.compareTo(BigInteger.ZERO) != 0) {
                this.x = this.x.divide(d);
                this.y = this.y.divide(d);
            }
        }

        public Rational add(Rational b) {
            //if (x == 0 && b.x == 0) return new Real(0, 1);

            BigInteger ny = y.multiply(b.y).multiply(BigInteger.valueOf(4));
            BigInteger nx = x.multiply(b.y).multiply(BigInteger.valueOf(4)).add(b.x.multiply(y));
            return new Rational(nx, ny);
        }

        @Override
        public String toString() {
            if (y.compareTo(BigInteger.ONE) == 0) return x.longValue() + "";
            return x.longValue() + "/" + y.longValue();
        }
    }