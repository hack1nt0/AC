package template;

import java.math.BigInteger;

/**
 * Created by dy on 16-10-8.
 */
public class RationalNumber {
        BigInteger X, Y;

        public RationalNumber(int x) {
            X = BigInteger.valueOf(x);
            Y = BigInteger.ONE;
        }

        public RationalNumber(int x, int y) {
            X = BigInteger.valueOf(x);
            Y = BigInteger.valueOf(y);
            BigInteger d = X.gcd(Y);
            if (d.compareTo(BigInteger.ZERO) != 0) {
                X = X.divide(d);
                Y = Y.divide(d);
            }
        }

        public RationalNumber(BigInteger x, BigInteger y) {
            X = x; Y = y;
            BigInteger d = X.gcd(Y);
            if (d.compareTo(BigInteger.ZERO) != 0) {
                X = X.divide(d);
                Y = Y.divide(d);
            }
        }

        public RationalNumber add(RationalNumber b) {
            //if (X == 0 && b.X == 0) return new Real(0, 1);

            BigInteger ny = Y.multiply(b.Y).multiply(BigInteger.valueOf(4));
            BigInteger nx = X.multiply(b.Y).multiply(BigInteger.valueOf(4)).add(b.X.multiply(Y));
            return new RationalNumber(nx, ny);
        }

        @Override
        public String toString() {
            if (Y.compareTo(BigInteger.ONE) == 0) return X.longValue() + "";
            return X.longValue() + "/" + Y.longValue();
        }
    }