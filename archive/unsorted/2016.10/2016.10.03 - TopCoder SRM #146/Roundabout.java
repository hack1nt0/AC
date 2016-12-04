package main;

import javax.management.relation.RoleNotFoundException;
import java.awt.image.RasterOp;
import java.util.*;

public class Roundabout {
    public int clearUpTime(String north, String east, String south, String west) {

        int W = 0, S = 1, E = 2, N = 3;
        int[] id = new int[128];
        id['W'] = W;
        id['S'] = S;
        id['E'] = E;
        id['N'] = N;

        List<Car> WL = new LinkedList<Car>();
        for (int i = 0; i < west.length(); ++i) if (west.charAt(i) != '-') WL.add(new Car(W, id[west.charAt(i)], i));
        for (int i = 0; i < south.length(); ++i) if (south.charAt(i) != '-') WL.add(new Car(S, id[south.charAt(i)], i));
        for (int i = 0; i < east.length(); ++i) if (east.charAt(i) != '-') WL.add(new Car(E, id[east.charAt(i)], i));
        for (int i = 0; i < north.length(); ++i) if (north.charAt(i) != '-') WL.add(new Car(N, id[north.charAt(i)], i));

        List<Car> round = new LinkedList<Car>();
        int t = 0;
        for (;;) {
            if (WL.size() == 0 && round.size() == 0) break;
            ++t;

            if (WL.size() != 0) {
                final int curT = t;
                Collections.sort(WL, new Comparator<Car>() {
                    @Override
                    public int compare(Car o1, Car o2) {
                        if (o1.T < curT && o2.T >= curT) return -1;
                        if (o2.T < curT && o1.T >= curT) return 1;
                        return o1.E - o2.E;
                    }
                });
                boolean[] canEnter = new boolean[4];
                if (WL.get(0).T < curT) canEnter[WL.get(0).E] = true;
                if (WL.get(1).T < curT && !canEnter[(WL.get(1).E - 1 + 4) % 4]) canEnter[WL.get(0).E] = true;
                if (!canEnter[0] && !canEnter[1] && !canEnter[2]) canEnter[3] = true;

                for (Car carOnR : round) canEnter[(carOnR.P + 1) % 4] = false;

                boolean ok = true;


                for (int i = 0; i < 2; ++i) if (canEnter[WL.get(0).E]) {
                    canEnter[WL.get(0).E] = false;
                    updRound(round);
                    round.add(WL.remove(0));
                }

            } else if (round.size() != 0) {
                updRound(round);
            }
            System.out.println(WL.size() + "; " + round.size());
        }
        return t;
    }

    private void updRound(List<Car> Round) {
        for (int i = 0; i < Round.size();) {
            Car carOnR = Round.get(i);
            if (carOnR.D == carOnR.P) {
                Round.remove(i);
            } else {
                carOnR.P = (carOnR.P + 1) % 4;
                ++i;
            }
        }
    }

    static class Car {
        int E, P, D, T;

        public Car(int e, int d, int t) {
            E = e;
            P = e;
            D = d;
            T = t;
        }
    }
}
