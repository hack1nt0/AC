package main;

import template.collection.intervals.ModInterval;
import template.collection.tuple.Tuple2;
import template.numbers.IntUtils;

import java.util.*;
import java.io.PrintWriter;
/*
 ID: hackint1
 LANG: JAVA
 TASK: spin
*/
public class Spin {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int nwheel = 5;
        int[] wheelSpeed = new int[nwheel];
        class Wedge {
            int start, extent;

            public Wedge(int start, int extent) {
                this.start = start;
                this.extent = extent;
            }
        }
        List<Wedge>[] wedges = new ArrayList[nwheel];
        for (int i = 0; i < nwheel; ++i) {
            wheelSpeed[i] = in.nextInt();
            int nwedge = in.nextInt();
            wedges[i] = new ArrayList<Wedge>();
            for (int w = 0; w < nwedge; ++w) wedges[i].add(new Wedge(in.nextInt(), in.nextInt()));
        }


//        int seconds = 9;
//        for (int wheel = 0; wheel < nwheel; ++wheel) {
//            for (Wedge wedge : wedges[wheel]) {
//                int nstart = (wedge.start + wheelSpeed[wheel] * seconds) % 360;
//                int end = (nstart + wedge.extent) % 360;
//                out.println(nstart + " " + end);
//            }
//        }

        long[] A = new long[nwheel];
        for (int i = 0; i < A.length; ++i) A[i] = wheelSpeed[i];
        long[] B = new long[nwheel];
        long[] Mod = new long[nwheel];
        Arrays.fill(Mod, 360);
        Tuple2<Long, Long> tuple2 = IntUtils.linearCongruence(A, B, Mod);
        long maxSeconds = tuple2.getFirst();
        long mod = tuple2.getSecond();
        if (maxSeconds == 0) maxSeconds += mod;
        System.err.println(maxSeconds);


       // int maxSeconds = 100000;
        for (int t = 0; t < maxSeconds; ++t) {
            Set<ModInterval> overlaps = new HashSet<>();
            //int[] visited = new int[360];
            for (int wheel = 0; wheel < nwheel; ++wheel) {
                Set<ModInterval> nOverlaps = new HashSet<>();
                for (Wedge wedge : wedges[wheel]) {
                    int left = (wedge.start + wheelSpeed[wheel] * t) % 360;
                    int right = ((left + wedge.extent) % 360 + 1) % 360;
                    ModInterval nInterval = new ModInterval(left, right, 360);
                    if (wheel == 0) {
                        nOverlaps.add(nInterval);
                    } else {
                        for (ModInterval exist : overlaps) {
                            for (ModInterval nOverlap : ModInterval.intersects(exist, nInterval)) {
                                nOverlaps.add(nOverlap);
                            }
                        }
                    }
//                    if (left < right) {
//                        for (int i = left; i < right; ++i) visited[i]++;
//                    } else {
//                        for (int i = left; i < visited.length; ++i) visited[i]++;
//                        for (int i = 0; i < right; ++i) visited[i]++;
//                    }
                }
                overlaps = nOverlaps;
//                if (t == 357) {
//                    System.err.println(Arrays.toString(overlaps.toArray(new ModInterval[0])));
//                }
            }
            if (overlaps.size() > 0) {
                System.err.println(Arrays.toString(overlaps.toArray(new ModInterval[0])));
                out.println(t);
                return;
            }
//            for (int i = 0; i < visited.length; ++i) if (visited[i] >= nwheel) {
//                out.println(t);
//                return;
//            }
        }

        out.println("none");
    }

}
