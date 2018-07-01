package main;

import template.numbers.Complex;
import template.numbers.FFT;

import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;

//
public class Convolution {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; ++i) a[i] = in.nextLong();
        long[] b = new long[n];
        for (int i = 0; i < n; ++i) b[i] = in.nextLong();
        long[] cf = fft(a, b);
        out.println(Arrays.toString(cf));
    }

    public static long[] brute(long[] a, long[] b) {
        int n = a.length + b.length - 1;
        long[] c = new long[n];
        for (int exponent = 0; exponent < n; ++exponent) {
            for (int ia = 0; ia <= Math.min(exponent, a.length - 1); ++ia)
                if (exponent - ia < b.length)
                    c[exponent] += a[ia] * b[exponent - ia];
        }
        return c;
    }


    public static long[] fft(long[] a, long[] b) {
        int cn = a.length + b.length - 1;
        int n = cn;
        if (Integer.bitCount(n) != 1) {
            n = Integer.highestOneBit(n) << 1;
        }
        int pow = (int)(Math.log(n) / Math.log(2));
        if (1L << pow != n)
            throw new RuntimeException();
        //evaluation
        double[][] ax = new double[n][2];
        for (int i = 0; i < a.length; ++i) ax[i][0] = a[i];
        double[][] bx = new double[n][2];
        for (int i = 0; i < b.length; ++i) bx[i][0] = b[i];
        double[][] ay = fft(ax, +1);
        double[][] by = fft(bx, +1);
        double[][] cy = new double[n][2];
        for (int i = 0; i < n; ++i) {
            cy[i][0] = ay[i][0] * by[i][0] - ay[i][1] * by[i][1];
            cy[i][1] = ay[i][0] * by[i][1] + ay[i][1] * by[i][0];
        }
        double[][] cx = fft(cy, -1);
        long[] c = new long[cn];
        for (int i = 0; i < cn; ++i) c[i] = Math.round(cx[i][0]);
        return c;

    }

    private static  double[][] fft(double[][] x, int signum) {
        int n = x.length;
        int pow = (int)(Math.log(n) / Math.log(2));
        double[][][] y = {x, new double[n][2]};
        int current = 0;
        double[][] w = new double[n][2];
        for (int len = 1; len <= pow; ++len) {
            int next = current ^ 1;
            int capacity = 1 << len;
            int buckets = n >> len;
            int half = capacity >> 1;
            for (int ic = 0; ic < capacity; ++ic) {
                double theta = 2 * Math.PI * ic / (1 << len) * signum;
                w[ic][0] = Math.cos(theta);
                w[ic][1] = Math.sin(theta);
            }
            for (int ib = 0; ib < buckets; ++ib) {
                int ieven = ib;
                int iodd = ib | 1 << (pow - len);
                for (int ic = 0; ic < capacity; ++ic) {
                    double[] even = y[current][ieven * half + (ic >= half ? ic - half : ic)];
                    double[] odd = y[current][iodd * half + (ic >= half ? ic - half : ic)];
//                    y[next][ib * capacity + ic] = even.plus(wi.times(odd));
                    y[next][ib * capacity + ic][0] = even[0] + w[ic][0] * odd[0] - w[ic][1] * odd[1];
                    y[next][ib * capacity + ic][1] = even[1] + w[ic][0] * odd[1] + w[ic][1] * odd[0];
                }
            }
            current ^= 1;
        }
        double[][] ans = y[current];
        if (signum == -1) {
            for (int i = 0; i < n; ++i) {
                ans[i][0] /= n;
                ans[i][1] /= n;
            }
        }
        return ans;
    }


}
