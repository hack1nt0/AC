package main;

import template.RationalNumber;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dragons {

    public String snaug(int[] initialFood, int rounds) {
        Dragon front = new Dragon(initialFood[0]);
        Dragon back = new Dragon(initialFood[1]);
        Dragon up = new Dragon(initialFood[2]);
        Dragon down = new Dragon(initialFood[3]);
        Dragon left = new Dragon(initialFood[4]);
        Dragon right = new Dragon(initialFood[5]);
        Dragon[] dragons = new Dragon[]{front, back, up, down, left, right};

        front.neighbors.addAll(Arrays.asList(up, down, left, right));
        back.neighbors.addAll(Arrays.asList(up, down, left, right));
        up.neighbors.addAll(Arrays.asList(left, right, front, back));
        down.neighbors.addAll(Arrays.asList(left, right, front, back));
        left.neighbors.addAll(Arrays.asList(up, down, front, back));
        right.neighbors.addAll(Arrays.asList(up, down, front, back));

        for (int i = 0; i < rounds; ++i) {
            for (Dragon dragon : dragons) {
                dragon.steal();
            }
            for (Dragon dragon : dragons) {
                dragon.upd();
            }
        }


        return up.food.toString();
    }

    static class Dragon {
        RationalNumber food;
        RationalNumber nFood;
        List<Dragon> neighbors = new ArrayList<Dragon>();

        public Dragon(int x) {
            food = new RationalNumber(x, 1);
            nFood = new RationalNumber(0, 1);
        }

        public void upd() {
            food = nFood;
            nFood = new RationalNumber(0, 1);
        }

        public void steal() {
            for (Dragon o : neighbors)
            nFood = nFood.add(o.food);
        }
    }

    

}
