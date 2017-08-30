package main;

import template.debug.InputReader;
import template.geometry.Point;
import template.geometry.Rectangle;

import java.io.PrintWriter;
import java.util.*;
/*
 ID: hackint1
 LANG: JAVA
 TASK: rect1
*/
public class Rect1 {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int width = in.readInt();
        int height = in.readInt();
        int n = in.readInt() + 1;
        Rectangle[] rects = new Rectangle[n];
        rects[0] = new Rectangle(new Point(0, 0), new Point(width, height));
        int[] colors = new int[n];
        colors[0] = 1;
        for (int i = 1; i < n; ++i) {
            rects[i] = new Rectangle(new Point(in.readInt(), in.readInt()), new Point(in.readInt(), in.readInt()));
            colors[i] = in.readInt();
        }
        TreeMap<Integer, Integer> areas = new TreeMap<>();
        for (int i = 0; i < n; ++i) {
            List<Rectangle> iRect = new ArrayList<>();
            iRect.add(rects[i]);
            for (int j = i + 1; j < n; ++j) {
                if (iRect.isEmpty()) break;
                List<Rectangle> nextIRect = new ArrayList<>();
                //if (colors[j] == colors[i]) nextIRect.add(rects[j]);
                for (Rectangle rectangle : iRect) {
                    nextIRect.addAll(rectangle.remove(rects[j]));
                }
                iRect = nextIRect;
            }
            for (Rectangle rectangle : iRect) areas.put(colors[i], (int)rectangle.area() + areas.getOrDefault(colors[i], 0));
        }
        for (Map.Entry<Integer, Integer> ca : areas.entrySet()) out.println(ca.getKey() + " " + ca.getValue());
    }
}
