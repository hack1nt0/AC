package main.chelper;

import net.egork.chelper.tester.StringInputStream;

import java.io.BufferedInputStream;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by dy on 16-12-3.
 */
public class NLinesIterator implements Iterator<String> {
    Scanner in;
    int nl;

    public NLinesIterator(String input, int nl) {
        in = new Scanner(new BufferedInputStream(new StringInputStream(input)));
        this.nl = nl;
    }

    @Override
    public boolean hasNext() {
        return in.hasNext();
    }

    @Override
    public String next() {
        StringBuffer ret = new StringBuffer();
        for (long i = 0; i < nl; ++i) {
            ret.append(in.nextLine());
        }
        return ret.toString();
    }

    @Override
    public void remove() {

    }
}
