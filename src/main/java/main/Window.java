package main;

import template.collection.sequence.ArrayQueue;
import template.debug.InputReader;
import template.geometry.Point;
import template.geometry.Rectangle;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 ID: hackint1
 LANG: JAVA
 TASK: window
*/

public class Window {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        LinkedList<WD> windows = new LinkedList<>();
        while (true) {
            if (in.isExhausted()) break;
            String op = in.readString();
            if (op.charAt(0) == 'w') {
                String[] tmp = op.split(",");
                char id = tmp[0].charAt(tmp[0].length() - 1);
                int d1 = Integer.valueOf(tmp[1]);
                int d2 = Integer.valueOf(tmp[2]);
                int d3 = Integer.valueOf(tmp[3]);
                int d4 = Integer.valueOf(tmp[4].substring(0, tmp[4].length() - 1));
                int x1 = Math.min(d1, d3);
                int x2 = Math.max(d1, d3);
                int y1 = Math.min(d2, d4);
                int y2 = Math.max(d2, d4);
                Rectangle rectangle = new Rectangle(new Point(x1, y1), new Point(x2, y2));
                windows.addLast(new WD(id, rectangle));
                continue;
            }
            if (op.charAt(0) == 't') {
                char id = op.charAt(2);
                WD ntop = windows.remove(windows.indexOf(new WD(id, null)));
                windows.addLast(ntop);
                continue;
            }
            if (op.charAt(0) == 'b') {
                char id = op.charAt(2);
                WD nbottom = windows.remove(windows.indexOf(new WD(id, null)));
                windows.addFirst(nbottom);
                continue;
            }
            if (op.charAt(0) == 'd') {
                char id = op.charAt(2);
                windows.remove(windows.indexOf(new WD(id, null)));
                continue;
            }
            if (op.charAt(0) == 's') {
                char id = op.charAt(2);
                int targetPos = windows.indexOf(new WD(id, null));
                int i = 0;
                List<Rectangle> que = new ArrayList<>();
                for (WD wd : windows) {
                    if (i == targetPos) que.add(wd.rectangle);
                    else if (i > targetPos) {
                        List<Rectangle> nque = new ArrayList<>();
                        for (Rectangle origin : que) for (Rectangle left : origin.remove(wd.rectangle)) nque.add(left);
                        que = nque;
                        if (que.size() == 0) break;
                    }
                    i++;
                }
                double leftArea = 0;
                for (Rectangle left : que) leftArea += left.area();
                out.printf("%.3f\n", leftArea * 100 / windows.get(targetPos).rectangle.area());
                continue;
            }

        }
    }

    class WD {
        char id;
        Rectangle rectangle;

        public WD(char id, Rectangle rectangle) {
            this.id = id;
            this.rectangle = rectangle;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            WD wd = (WD) o;

            return id == wd.id;

        }

        @Override
        public int hashCode() {
            return (int) id;
        }
    }
}
