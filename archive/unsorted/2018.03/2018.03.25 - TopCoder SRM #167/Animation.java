package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Animation {
    class Particle {
        int pos;
        int dir;

        Particle(int pos, int dir) {
            this.pos = pos;
            this.dir = dir;
        }
    }

    public String[] animate(int speed, String init) {
        List<Particle> particles = new ArrayList<>();
        for (int i = 0; i < init.length(); ++i)
            if (init.charAt(i) != '.')
                particles.add(new Particle(i, init.charAt(i) == 'L' ? -1 : +1));
        List<String> ans = new ArrayList<>();
        while (true) {
            char[] cur = new char[init.length()];
            Arrays.fill(cur, '.');
            boolean valid = false;
            for (Particle particle : particles)
                if (0 <= particle.pos  && particle.pos < init.length()) {
                    cur[particle.pos] = 'X';
                    valid = true;
                }
            ans.add(new String(cur));
            if (!valid)
                break;
            for (Particle particle : particles)
                particle.pos += speed * particle.dir;
        }
        return ans.toArray(new String[0]);
    }
}
