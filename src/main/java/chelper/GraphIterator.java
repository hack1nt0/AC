package chelper;

import java.io.StringReader;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by dy on 16-12-3.
 */
public class GraphIterator implements Iterator<String> {
    Scanner in;
    int mpos;

    public GraphIterator(String input, int mpos) {
        in = new Scanner(new StringReader(input));
        this.mpos = mpos;
    }

    @Override
    public boolean hasNext() {
        return in.hasNext();
    }

    @Override
    public String next() {
        StringBuffer ret = new StringBuffer();
        String firstLine = in.nextLine();
        String[] tmp = firstLine.split("[ ]+");
        long M = Long.valueOf(tmp[mpos]); //# of edges;
        ret.append(firstLine);
        ret.append("\n");
        for (long i = 0; i < M; ++i) {
            ret.append(in.nextLine());
            ret.append("\n");
        }
        ret.append(in.nextLine());
        ret.append("\n");
        return ret.toString();
    }

    @Override
    public void remove() {
        in.nextLine();
    }
}
