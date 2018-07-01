package main;

import template.debug.InputReader;
import template.debug.OutputWriter;
import template.debug.RandomUtils;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        Event[] events = new Event[n * 2];
        for (int i = 0; i < n * 2; i += 2) {
            events[i] = new Event(0, in.readLong());
            events[i + 1] = new Event(1, in.readLong());
        }
        Arrays.parallelSort(events);
        long[] count = new long[n + 1];
        int no = 0;
        long pp = 0;
        for (int i = 0; i < events.length; ++i) {
            Event e = events[i];
            if (e.side == 0) {
                count[no] += e.x - pp;
                pp = e.x;
                no++;
            } else {
                count[no] += e.x - pp + 1;
                pp = e.x + 1;
                no--;
            }
            if (no < 0)
                throw new RuntimeException();
        }
        for (int i = 1; i <= n; ++i) {
            out.print(count[i]);
            out.print(i == n ? '\n' : ' ');
        }
    }

    class Event implements Comparable<Event> {
        int side;
        long x;

        public Event(int side, long x) {
            this.side = side;
            this.x = x;
        }

        @Override
        public int compareTo(Event o) {
            if (x != o.x)
                return Long.compare(x, o.x);
            return side - o.side;
        }
    }
}
