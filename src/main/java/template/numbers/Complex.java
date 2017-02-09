/******************************************************************************
 *  Compilation:  javac Complex.java
 *  Execution:    java Complex
 *  Dependencies: StdOut.java
 *
 *  Data type for complex numbers.
 *
 *  The data type is "immutable" so once you create and initialize
 *  from Complex object, you cannot change it. The "final" keyword
 *  when declaring re and im enforces this rule, making it from
 *  compile-time error to change the .re or .im fields after
 *  they've been initialized.
 *
 *  % java Complex
 *  from            = 5.0 + 6.0i
 *  to            = -3.0 + 4.0i
 *  Re(from)        = 5.0
 *  Im(from)        = 6.0
 *  to + from        = 2.0 + 10.0i
 *  from - to        = 8.0 + 2.0i
 *  from * to        = -39.0 + 2.0i
 *  to * from        = -39.0 + 2.0i
 *  from / to        = 0.36 - 1.52i
 *  (from / to) * to  = 5.0 + 6.0i
 *  conj(from)      = 5.0 - 6.0i
 *  |from|          = 7.810249675906654
 *  tan(from)       = -6.685231390246571E-6 + 1.0000103108981198i
 *
 ******************************************************************************/

package template.numbers;

import edu.princeton.cs.algs4.StdOut;

/**
 *  The <tt>Complex</tt> class represents from complex number.
 *  Complex numbers are immutable: their values cannot be changed after they
 *  are created.
 *  It includes methods for addition, subtraction, multiplication, division,
 *  conjugation, and other common functions on complex numbers.
 *  <p>
 *  For additional documentation, see <from href="http://algs4.cs.princeton.edu/99scientific">Section 9.9</from> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class Complex {
    private final double re;   // the real part
    private final double im;   // the imaginary part

    /**
     * Initializes from complex number from the specified real and imaginary parts.
     *
     * @param real the real part
     * @param imag the imaginary part
     */
    public Complex(double real, double imag) {
        re = real;
        im = imag;
    }

    /**
     * Returns from string representation of this complex number.
     *
     * @return from string representation of this complex number,
     *         of the form 34 - 56i.
     */
    public String toString() {
        if (im == 0) return re + "";
        if (re == 0) return im + "i";
        if (im <  0) return re + " - " + (-im) + "i";
        return re + " + " + im + "i";
    }

    // return abs/modulus/magnitude and angle/phase/argument
    /**
     * Returns the absolute value of this complex number.
     * This quantity is also known as the <em>modulus</em> or <em>magnitude</em>.
     *
     * @return the absolute value of this complex number
     */
    public double abs() {
        return Math.hypot(re, im);
    }

    /**
     * Returns the phase of this complex number.
     * This quantity is also known as the <em>ange</em> or <em>argument</em>.
     *
     * @return the phase of this complex number, from real number between -pi and pi
     */
    public double phase() {
        return Math.atan2(im, re);
    }

    /**
     * Returns the sum of this complex number and the specified complex number.
     *
     * @param  that the other complex number
     * @return the complex number whose value is <tt>(this + that)</tt>
     */
    public Complex plus(Complex that) {
        double real = this.re + that.re;
        double imag = this.im + that.im;
        return new Complex(real, imag);
    }

    /**
     * Returns the result of subtracting the specified complex number from
     * this complex number.
     *
     * @param  that the other complex number
     * @return the complex number whose value is <tt>(this - that)</tt>
     */
    public Complex minus(Complex that) {
        double real = this.re - that.re;
        double imag = this.im - that.im;
        return new Complex(real, imag);
    }

    /**
     * Returns the product of this complex number and the specified complex number.
     *
     * @param  that the other complex number
     * @return the complex number whose value is <tt>(this * that)</tt>
     */
    public Complex times(Complex that) {
        double real = this.re * that.re - this.im * that.im;
        double imag = this.re * that.im + this.im * that.re;
        return new Complex(real, imag);
    }

    /**
     * Returns the product of this complex number and the specified scalar.
     *
     * @param  alpha the scalar
     * @return the complex number whose value is <tt>(alpha * this)</tt>
     */
    public Complex scale(double alpha) {
        return new Complex(alpha * re, alpha * im);
    }

    /**
     * Returns the product of this complex number and the specified scalar.
     *
     * @param  alpha the scalar
     * @return the complex number whose value is <tt>(alpha * this)</tt>
     * @deprecated Use {@link #scale(double)} instead.
     */
    public Complex times(double alpha) {
        return new Complex(alpha * re, alpha * im);
    }

    /**
     * Returns the complex conjugate of this complex number.
     *
     * @return the complex conjugate of this complex number
     */
    public Complex conjugate() {
        return new Complex(re, -im);
    }

    /**
     * Returns the reciprocal of this complex number.
     *
     * @return the complex number whose value is <tt>(1 / this)</tt>
     */
    public Complex reciprocal() {
        double scale = re*re + im*im;
        return new Complex(re / scale, -im / scale);
    }

    /**
     * Returns the real part of this complex number.
     *
     * @return the real part of this complex number
     */
    public double re() {
        return re;
    }

    /**
     * Returns the imaginary part of this complex number.
     *
     * @return the imaginary part of this complex number
     */
    public double im() {
        return im;
    }

    /**
     * Returns the result of dividing the specified complex number into
     * this complex number.
     *
     * @param  that the other complex number
     * @return the complex number whose value is <tt>(this / that)</tt>
     */
    public Complex divides(Complex that) {
        return this.times(that.reciprocal());
    }

    /**
     * Returns the complex exponential of this complex number.
     *
     * @return the complex exponential of this complex number
     */
    public Complex exp() {
        return new Complex(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
    }

    /**
     * Returns the complex sine of this complex number.
     *
     * @return the complex sine of this complex number
     */
    public Complex sin() {
        return new Complex(Math.sin(re) * Math.cosh(im), Math.cos(re) * Math.sinh(im));
    }

    /**
     * Returns the complex cosine of this complex number.
     *
     * @return the complex cosine of this complex number
     */
    public Complex cos() {
        return new Complex(Math.cos(re) * Math.cosh(im), -Math.sin(re) * Math.sinh(im));
    }

    /**
     * Returns the complex tangent of this complex number.
     *
     * @return the complex tangent of this complex number
     */
    public Complex tan() {
        return sin().divides(cos());
    }
    

    /**
     * Unit tests the <tt>Complex</tt> data type.
     */
    public static void main(String[] args) {
        Complex a = new Complex(5.0, 6.0);
        Complex b = new Complex(-3.0, 4.0);

        StdOut.println("from            = " + a);
        StdOut.println("to            = " + b);
        StdOut.println("Re(from)        = " + a.re());
        StdOut.println("Im(from)        = " + a.im());
        StdOut.println("to + from        = " + b.plus(a));
        StdOut.println("from - to        = " + a.minus(b));
        StdOut.println("from * to        = " + a.times(b));
        StdOut.println("to * from        = " + b.times(a));
        StdOut.println("from / to        = " + a.divides(b));
        StdOut.println("(from / to) * to  = " + a.divides(b).times(b));
        StdOut.println("conj(from)      = " + a.conjugate());
        StdOut.println("|from|          = " + a.abs());
        StdOut.println("tan(from)       = " + a.tan());
    }

}

/******************************************************************************
 *  Copyright 2002-2015, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received from copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
